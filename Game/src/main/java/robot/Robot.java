package robot;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

import plugins.Plugin_Attaque;
import plugins.Plugin_Deplacement;
import plugins.Plugin_Graphique_Couleur;
import plugins.Plugin_Graphique_Forme;

/**
 * Class representant un robot
 */

public class Robot {

	private String identifiant;
	private Point position;
	private int nombrePDV = 10;
	private int energie = 10;
	private Color couleur;
	
	//PLUGIN FIX : On utilisera ça après
	private Plugin_Attaque pluginAttaque;
	private Plugin_Deplacement pluginDeplacement;
	private Plugin_Graphique_Couleur pluginCouleur;
	private Plugin_Graphique_Forme pluginForme;
	
	public Robot(String identifiant, Point pPosition){
		this.position = pPosition;
		this.identifiant = identifiant;
	}
	
	public Point getActionDeplacement(){

		//PLUGIN FIX
		Point newPosition = this.pluginDeplacement.getNouvellePosition(this.position, 10, 10);
		System.out.println("Je me deplace de " + position + " a " + newPosition);
		return newPosition;
		//this.position = newPosition;
		
	}
	
	public HashMap<String, Object> getActionAttaque(){
		
		//PLUGIN FIX
		return this.pluginAttaque.attaque(this.position, 10, 10);

	}
	
	/**
	 * Calcul de la distance entre deux robots
	 * @param robot1
	 * @param robot2
	 * @return double
	 */
	public static double distanceEntreRobots(Robot robot1, Robot robot2) {
		int x1 = robot1.getPosition().x;
		int y1 = robot1.getPosition().y;
		int x2 = robot2.getPosition().x;
		int y2 = robot2.getPosition().y;

		return (double) Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	}
	
	public void setPosition(Point p){
		this.position = p;
	}
	
	public Point getPosition() {
		return this.position;
	}

	public int getNombrePDV() {
		return nombrePDV;
	}

	public void setNombrePDV(int nombrePDV) {
		this.nombrePDV = nombrePDV;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}
	

	public void retirerEnergie(int energie){
		this.energie -= energie;
	}
	
	public void donnerEnergie(){
		this.energie++;
	}

	public void setPluginAttaque(Plugin_Attaque pluginAttaque) {
		this.pluginAttaque = pluginAttaque;
	}

	public void setPluginDeplacement(Plugin_Deplacement pluginDeplacement) {
		this.pluginDeplacement = pluginDeplacement;
	}

	@Override
	public String toString() {
		return "Robot [identifiant=" + identifiant + ", position=" + position
				+ ", nombrePDV=" + nombrePDV + ", energie=" + energie + "]";
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public void setPluginCouleur(Plugin_Graphique_Couleur pluginCouleur) {
		this.pluginCouleur = pluginCouleur;
		this.couleur = pluginCouleur.getColor(); 
	}

	public Plugin_Graphique_Forme getPluginForme() {
		return pluginForme;
	}

	public void setPluginForme(Plugin_Graphique_Forme pluginForme) {
		this.pluginForme = pluginForme;
	}
}
