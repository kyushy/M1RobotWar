package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import plugins.Plugin_Graphique_Couleur;
import plugins.Plugin_Graphique_Forme;
import plugins.PluginsLoader;
import robot.Robot;



/**
 *	Classe principale qui va contenir tous les elements graphiques du jeu
 *	@author Frederic
 */
public class Plateau extends JFrame implements ActionListener, Observer, MouseListener {

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
	private Thread game;

	/**
	 * Constructeur du plateau mettant en place l'arene et le menu
	 * @param Arene arene
	 */
	public Plateau(Arene arene){
		this.setTitle("ROBOTWAR");
		this.setSize(600, 600);
		this.setLayout(new BorderLayout());


		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu();
		this.fileMenu.addMouseListener(this);
		this.partieMenu = new JMenu();
		this.partieMenu.addMouseListener(this);

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


	/**
	 * Méthode  qui gere les actions effectuer sur les differents items des menus
	 * @param ActionEvent arg0
	 */
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
					Class<?> cl = PluginsLoader.getInstance().loadPlugin("plugins."+chaine);
					Class<?> clInterface[] = cl.getInterfaces();
					
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
						
						//Chargement dynamique plugin graphique couleur
						if (clInterface[i].getName().equals("plugins.Plugin_Graphique_Couleur")){
							ArrayList<Robot> listeRobot = this.mdj.getListeRobot();
							for (Robot robot : listeRobot) {
								try {
									robot.setPluginCouleur((Plugin_Graphique_Couleur) cl.newInstance());
								} catch (InstantiationException | IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}
						
						//Chargement dynamique plugin graphique forme
						if (clInterface[i].getName().equals("plugins.Plugin_Graphique_Forme")){
							ArrayList<Robot> listeRobot = this.mdj.getListeRobot();
							for (Robot robot : listeRobot) {
								try {
									robot.setPluginForme((Plugin_Graphique_Forme) cl.newInstance());
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
			if(game == null) {
				new Thread(this.mdj).start();
			}
			mdj.setPause(false);
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
				HashMap<String, Object> dataPartie = sysSave.chargerSauvegarde(fileACharger);
				this.mdj.restaurerPartie(dataPartie);
			}
		}
	}
	
	/**
	 * Getter Arene
	 * @return Arene
	 */
	public Arene getArene() {
		return arene;
	}
	
	/**
	 * Setter du moteurDeJeu
	 * @param MoteurDeJeu mdj
	 */
	public void setMoteurDeJeu(MoteurDeJeu mdj){
		this.mdj = mdj;
	}

	/**
	 * Méthode de mise à jour de l'interface graphique
	 * Pattern Observer/Observable
	 * @param Observable o 
	 * @param Object arg
	 */
	@Override
	public void update(Observable o, Object arg) {

		//si arg != null alors il s'agit d'une attaque
		if(arg != null){
			Robot r = (Robot) arg;
			this.getArene().applyAnimation(r);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.getArene().getGraphics().clearRect(0, 0, this.getArene().getWidth(), this.getArene().getHeight());
		
		for(Robot r : mdj.getListeRobot()){
			this.getArene().paintPanel(r, r.getPosition().x, r.getPosition().y);
		}
		
	}

	/**
	 * Methode hérité du MouseListener
	 * Utiliser pour mettre en pause le jeu lors d'un clic dans le menu
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.mdj.setPause(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
