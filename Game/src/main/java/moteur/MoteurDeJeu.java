package moteur;

import java.awt.Point;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JFrame;

import plugins.Plugin_Attaque;
import plugins.Plugin_Deplacement;
import plugins.Plugin_Graphique_Couleur;
import plugins.Plugin_Graphique_Forme;
import plugins.PluginsLoader;
import robot.Robot;
import GUI.Arene;
import GUI.Plateau;

public class MoteurDeJeu extends Observable implements Runnable {

	private ArrayList<Robot> listeRobot = new ArrayList<>();
	private Plateau plateauDeJeu;
	private volatile boolean pause = false;
	
	/**
	 * Constructeur du Moteur de jeu prenant en param�tre le nombre de robot dans le jeu, la longueur du terrain et la largeur 
	 * @param nbRobot
	 * @param longeur
	 * @param largeur
	 */

	public MoteurDeJeu(int nbRobot, int longueur, int largeur){

		this.plateauDeJeu = new Plateau(new Arene(longueur, largeur));
		this.plateauDeJeu.setMoteurDeJeu(this);
		this.addObserver(plateauDeJeu);
		
		//Creation du nombre de robots
		for(int i=0; i<nbRobot; i++){
			Robot robot = creationRobot();
			this.listeRobot.add(robot);
		}
		this.plateauDeJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//start(); deja dans le main ?
	}

	public static int nombreAleaLongueur(int max){
		return (int) (Math.random()*(max));
	}

	public static int nombreAleaLargeur(int max){
		return (int) (Math.random()*(max));
	}


	/**
	 * Methode pour verifier qu'un robot ne se trouve pas au meme coordonnee qu'un autre
	 * @param robot
	 * @return boolean
	 */
	public boolean robotExist(Robot robot){
		if (this.listeRobot.isEmpty()){
			return false;
		}else{
			boolean exist = false;
			for (int i = 0; i < this.listeRobot.size(); i++) {
				if((this.listeRobot.get(i).getPosition().x == robot.getPosition().x) && 
						(this.listeRobot.get(i).getPosition().y == robot.getPosition().y)){
					exist = true;
				}
			}
			return exist;
		}
	}

	/**
	 * Creation du robot a des coordonnees aleatoire
	 * @return Robot
	 */
	public Robot creationRobot() {
		Robot robot = new Robot("" + this.listeRobot.size(), new Point(MoteurDeJeu.nombreAleaLongueur(this.plateauDeJeu.getArene().getLongueur()),
				MoteurDeJeu.nombreAleaLargeur(this.plateauDeJeu.getArene().getLargeur())));
		while(robotExist(robot)){
			robot = new Robot("" + this.listeRobot.size(), new Point(MoteurDeJeu.nombreAleaLongueur(this.plateauDeJeu.getArene().getLongueur()),
					MoteurDeJeu.nombreAleaLargeur(this.plateauDeJeu.getArene().getLargeur())));
		}

        try {
            Class cDep = PluginsLoader.getInstance().loadPlugin("plugins.Plugin_Deplacement_Aleatoire_Une_Case");
            Class cAtk = PluginsLoader.getInstance().loadPlugin("plugins.Plugin_Attaque_Courte_Portee");
            Class cColor = PluginsLoader.getInstance().loadPlugin("plugins.Plugin_Graphique_Couleur_Aleatoire");
            Class cForme = PluginsLoader.getInstance().loadPlugin("plugins.Plugin_Graphique_Forme_Rectangle");

            robot.setPluginDeplacement((Plugin_Deplacement) cDep.newInstance());
            robot.setPluginAttaque((Plugin_Attaque) cAtk.newInstance());
            robot.setPluginCouleur((Plugin_Graphique_Couleur)cColor.newInstance());
            robot.setPluginForme((Plugin_Graphique_Forme)cForme.newInstance());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | MalformedURLException e) {
            e.printStackTrace();
        }

        return robot;
	}

	/**
	 * Retourne le robot le plus proche (premier trouve)
	 * @param robotReference
	 * @return Robot
	 */
	public Robot robotLePlusProche(Robot robotReference) {

		if (this.listeRobot.size() < 2) { return null; } // Pas de robot ou que lui-meme

		Robot robotProche = this.listeRobot.get(0);
		double distanceSauvegardee = Robot.distanceEntreRobots(robotReference, this.listeRobot.get(0));

		for (int i = 1; i < this.listeRobot.size(); i++) {

			Robot robotCourant = this.listeRobot.get(i);
			double distanceCourante = Robot.distanceEntreRobots(robotReference, robotCourant);

			if (distanceCourante < distanceSauvegardee) {
				distanceSauvegardee = distanceCourante;
				robotProche = robotCourant;
			}
		}

		return robotProche;

	}

	/**
	 * Methode pour verifier si un robot se trouve a la position indiquee
	 * @param Point
	 * @return Robot
	 */
	public Robot robotACettePosition(Point position) {

		for (int i = 0; i < this.listeRobot.size(); i++) {
			if ((this.listeRobot.get(i).getPosition().x == position.x) && 
					(this.listeRobot.get(i).getPosition().y == position.y)) {
				return this.listeRobot.get(i);
			}
		}
		return null;
	}


	/**
	 * Methode pour lancer le jeu
	 */
	public void start() {
		
		int nbManches = 0;

		this.setChanged();
		this.notifyObservers();
				
		// Tant qu'il reste plus d'un robot et que le jeu n'est pas en pause
		while (this.listeRobot.size() > 1) {
				
			System.out.println("-- MANCHE " + nbManches + "--");

			// On parcourt la liste des robots et on leur demande leurs actions
			for (int i = 0; i < this.listeRobot.size(); i++) {
				System.out.println("-> Au tour de : " + this.listeRobot.get(i));
				
				this.listeRobot.get(i).getActionDeplacement();
				this.setChanged();
				this.notifyObservers();
				HashMap<String, Object> dicAttaque = listeRobot.get(i).getActionAttaque();
				
				//Si on dispose d'assez d'energie pour attaquer
				if((Integer) dicAttaque.get("ENERGIE")< listeRobot.get(i).getEnergie()){
					//On retire l'energie de l'action
					this.listeRobot.get(i).retirerEnergie((Integer) dicAttaque.get("ENERGIE"));
					this.phaseAttaque(dicAttaque);
					//listeRobot.get(i).donnerEnergie();
				}
				
				
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//on donne l'energie de fin de tour
				
			}
			
			for (int i = 0; i < this.listeRobot.size(); i++) {
				listeRobot.get(i).donnerEnergie();
			}
			
			nbManches++;
			
			System.out.println();
			
			
		}
		
		this.setChanged();
		this.notifyObservers();
		System.out.println("Le gagnant est : " + this.listeRobot.get(0));


	}

	public void phaseAttaque(HashMap<String, Object> dicAttaque) {

		ArrayList<Point> listeLieux = (ArrayList<Point>) dicAttaque.get("LIEU");
		System.out.println("Lieux vises : " + listeLieux);
		
		
		for (Point p : listeLieux) {
			Robot robotVise = this.robotACettePosition(p);
			
			if (robotVise != null) {
				System.out.println("Un robot touche !");
				robotVise.setNombrePDV(robotVise.getNombrePDV() - (Integer) dicAttaque.get("PUISSANCE"));
	
				if (robotVise.getNombrePDV() <= 0) {
					System.out.println("Un robot élimine");
					this.listeRobot.remove(robotVise);
				}
			}
		}
	}

	
	@Override
	public void run() {
		this.start();
	}

	
	public ArrayList<Robot> getListeRobot() {
		return listeRobot;
	}

	//Modification de la liste des robots
	public void setListeRobot(ArrayList<Robot> listeRobot) {
		this.listeRobot = listeRobot;
	}

	
	/**
	 * Methode main
	 * @param args
	 */

	public static void main(String[] args) {
		MoteurDeJeu mdj = new MoteurDeJeu(10, 10, 10);
		//mdj.start();
	}

}
