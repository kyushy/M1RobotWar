package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import moteur.MoteurDeJeu;
import moteur.SystemeSauvegarde;

/**
 *	Classe principale qui va contenir tous les elements graphiques du jeu
 *	TODO : d�finir un layout pour disposer les divers elements
 */
public class Plateau extends JFrame implements ActionListener{

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
			if(f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				//this.listePlugins.add(f.getSelectedFile().getAbsolutePath());
				this.listePlugins.add(f.getSelectedFile());
			}
		}
		
		//Si on clique sur Lancer le jeu
		if(arg0.getSource() == this.runPluginsMenuItem){
			this.mdj.start();
		}
		
		//Si on clique sur sauvegarder partie
		if(arg0.getSource() == this.loadSave){
			SystemeSauvegarde sysSave = new SystemeSauvegarde(this.mdj);
			sysSave.sauvegarder();
		}
		//Si on clique sur charger partie
		if(arg0.getSource() == this.saveSave){
			SystemeSauvegarde sysSave = new SystemeSauvegarde(this.mdj);
			sysSave.chargerSauvegarde();
		}
	}
	
	public Arene getArene() {
		return arene;
	}
	
	public void setMoteurDeJeu(MoteurDeJeu mdj){
		this.mdj = mdj;
	}

}
