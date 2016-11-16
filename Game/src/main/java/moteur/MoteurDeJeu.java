package moteur;

import java.util.ArrayList;

import javax.swing.JFrame;

import robot.Robot;
import GUI.Arene;
import GUI.Plateau;

public class MoteurDeJeu {

	private ArrayList<Robot> listeRobot = new ArrayList<>();
	private Plateau plateauDeJeu;

	/**
	 * Constructeur du Moteur de jeu prenant en param�tre le nombre de robot dans le jeu, la longueur du terrain et la largeur 
	 * @param nbRobot
	 * @param longeur
	 * @param largeur
	 */

	public MoteurDeJeu(int nbRobot, int longueur, int largeur){

		this.plateauDeJeu = new Plateau(new Arene(longueur, largeur));
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
				if((this.listeRobot.get(i).getX() == robot.getX()) && (this.listeRobot.get(i).getY() == robot.getY())){
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
	public Robot creationRobot(){
		Robot robot = new Robot(MoteurDeJeu.nombreAleaLongueur(this.plateauDeJeu.getArene().getLongueur()),
				MoteurDeJeu.nombreAleaLargeur(this.plateauDeJeu.getArene().getLargeur()));
		while(robotExist(robot)){
			robot = new Robot(MoteurDeJeu.nombreAleaLongueur(this.plateauDeJeu.getArene().getLongueur()),
					MoteurDeJeu.nombreAleaLargeur(this.plateauDeJeu.getArene().getLargeur()));
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
	 * Methode pour lancer le jeu
	 */
	public void start(){

		//Tant qu'il reste plus d'un robot en jeu
		while(this.listeRobot.size()>1){
			//On parcours la liste des robots et on leurs demande leurs actions 
			for (int i = 0; i < this.listeRobot.size(); i++) {
				this.listeRobot.get(i).getAction();
			}
		}

		System.out.println("Le gagnant est : " + this.listeRobot.get(0));

	}

	public ArrayList<Robot> getListeRobot() {
		return listeRobot;
	}
	

	public void setListeRobot(ArrayList<Robot> listeRobot) {
		this.listeRobot = listeRobot;
	}

	/**
	 * Methode main
	 * @param args
	 */

	public static void main(String[] args) {
		MoteurDeJeu mdj = new MoteurDeJeu(2, 10, 10);
		mdj.start();
	
	}
}
