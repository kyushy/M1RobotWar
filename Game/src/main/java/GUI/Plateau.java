package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import moteur.MoteurDeJeu;
import moteur.SystemeSauvegarde;
import plugins.Plugin_Attaque;
import plugins.Plugin_Deplacement;
import plugins.PluginsLoader;
import robot.Robot;

/**
 *	Classe principale qui va contenir tous les elements graphiques du jeu
 *	TODO : d�finir un layout pour disposer les divers elements
 */
public class Plateau extends JFrame implements ActionListener, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Arene arene;

	/**
	 * Attributs des menus
	 */
	private JMenuBar menuBar;

	private JMenu fileMenu;
	private JMenuItem loadMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem runPluginsMenuItem;

	private JMenu partieMenu;
	private JMenuItem loadSave;
	private JMenuItem saveSave;

	private MoteurDeJeu mdj;

	private ArrayList<File> listePlugins = new ArrayList<>();
	
	public Plateau(Arene arene){
		this.setSize(300, 300);
		this.setLayout(new BorderLayout());


		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu();
		this.partieMenu = new JMenu();

		//MenuBar
		this.menuBar.add(this.fileMenu);
		this.menuBar.add(this.partieMenu);

		//On definit les élements du menu fichier
		//le télechargement des plugins
		this.loadMenuItem = new JMenuItem();
		//le lancement du jeu
		this.runPluginsMenuItem = new JMenuItem();
		//la fonction pour quitter le jeu
		this.exitMenuItem = new JMenuItem();

		//On ajoute les items au menu
		this.fileMenu.setText("Fichier");
		this.fileMenu.add(this.loadMenuItem);
		this.fileMenu.add(this.runPluginsMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitMenuItem);

		//On definit les éléments du menu de parti
		//La save
		this.loadSave = new JMenuItem();
		//Le chargement
		this.saveSave = new JMenuItem();

		//On ajoute les items au menu
		this.partieMenu.setText("Partie");
		this.partieMenu.add(this.saveSave);
		this.partieMenu.add(this.loadSave);
		

		//exitMenuItem
		this.exitMenuItem.setText("Fermer");
		this.exitMenuItem.addActionListener(this);

		//loadMenuItem
		this.loadMenuItem.setText("Charger un plugins");
		this.loadMenuItem.addActionListener(this);

		//runPluginsMenuItem
		this.runPluginsMenuItem.setText("Lancer le jeu");
		this.runPluginsMenuItem.addActionListener(this);
		
		
		this.loadSave.setText("Charger sauvegarde");
		this.loadSave.addActionListener(this);
		this.saveSave.setText("Sauvegarder partie");
		this.saveSave.addActionListener(this);
		
		
		this.setJMenuBar(this.menuBar);		

		//ajout de l'arene au centre de la fenetre
		this.arene = arene;
		this.add(arene, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0){
		//Si on clique sur fermer dans le menu
		if(arg0.getSource() == this.exitMenuItem ){
			this.setVisible(false);
		}
		
		//Si on clique sur charger un plugin
		if( arg0.getSource() == this.loadMenuItem ){
			JFileChooser f = new JFileChooser();
			f.setCurrentDirectory(new File("../Plugins" + File.separator + "target" + File.separator + "classes")); 
			f.changeToParentDirectory(); 
			if(f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				try {
					String chaine = f.getSelectedFile().getName();
					chaine = chaine.replaceAll(".class", "");
					Class cl = PluginsLoader.getInstance().loadPlugin("plugins."+chaine);
					Class clInterface[] = cl.getInterfaces();
					
					for (int i = 0; i < clInterface.length; i++) {
						//Si c'est un plugin d'attaque
						if (clInterface[i].getName().equals("plugins.Plugin_Attaque")){
							ArrayList<Robot> listeRobot = this.mdj.getListeRobot();
							for (Robot robot : listeRobot) {
								try {
									robot.setPluginAttaque((Plugin_Attaque) cl.newInstance());
								} catch (InstantiationException | IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}
						
						//Si c'est un plugin de déplacement
						if (clInterface[i].getName().equals("plugins.Plugin_Deplacement")){
							ArrayList<Robot> listeRobot = this.mdj.getListeRobot();
							for (Robot robot : listeRobot) {
								try {
									robot.setPluginDeplacement((Plugin_Deplacement) cl.newInstance());
								} catch (InstantiationException | IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}						
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//Si on clique sur Lancer le jeu 
		if(arg0.getSource() == this.runPluginsMenuItem){
			new Thread(this.mdj).start();
		}
		
		//Si on clique sur sauvegarder partie
		if(arg0.getSource() == this.saveSave){
			SystemeSauvegarde sysSave = new SystemeSauvegarde(this.mdj);
			
			//On affiche la fenetre pour creer le  fichier
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");
			
			int userSelection = fileChooser.showSaveDialog(this);
			File fileToSave = null;
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				//On creer le fichier
				fileToSave = fileChooser.getSelectedFile();
				//On lui ajoute l'extension
				fileToSave = new File(fileToSave.getAbsolutePath()+".ser");
				//System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			}
			//On appel la méthode de save
			if (fileToSave != null){
				sysSave.sauvegarder(fileToSave);
			}
		}
		
		//Si on clique sur charger partie
		if(arg0.getSource() == this.loadSave){
			SystemeSauvegarde sysSave = new SystemeSauvegarde(this.mdj);
			
			JFileChooser f = new JFileChooser();
			File fileACharger = null;
			if(f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				fileACharger = f.getSelectedFile();
			}
			if (fileACharger != null){
				sysSave.chargerSauvegarde(fileACharger);
			}
		}
	}
	
	public Arene getArene() {
		return arene;
	}
	
	public void setMoteurDeJeu(MoteurDeJeu mdj){
		this.mdj = mdj;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		for(Component c : this.getArene().getComponents()){
			c.setBackground(Color.WHITE);
		}
		
		
		for(Robot r : mdj.getListeRobot()){
			this.getArene().paintPanel(r, r.getPosition().x, r.getPosition().y);
		}	
	}
}
