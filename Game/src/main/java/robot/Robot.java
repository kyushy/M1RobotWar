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
	
	public Robot(){
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
	
	/**
	 * Getter Position
	 * @return Point
	 */
	public Point getPosition() {
		return this.position;
	}

	/**
	 * Getter Nombre de PDV
	 * @return int nombrePDV
	 */
	public int getNombrePDV() {
		return nombrePDV;
	}

	/**
	 * Setter nombre de PDV
	 * @param int nombrePDV
	 */
	public void setNombrePDV(int nombrePDV) {
		this.nombrePDV = nombrePDV;
	}

	/**
	 * Getter energie
	 * @return int energie
	 */
	public int getEnergie() {
		return energie;
	}

	/**
	 * Setter energie 
	 * @param int energie
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}
	
	/**
	 * methode qui soustrait l'energie passe en parametre à celle en attribut
	 * @param energie
	 */
	public void retirerEnergie(int energie){
		this.energie -= energie;
	}
	
	/**
	 * Methode pour rendre de l'energie
	 */
	public void donnerEnergie(){
		this.energie++;
	}
	
	/**
	 * Getter Plugin_Attaque
	 * @return Plugin_Attaque
	 */
	public Plugin_Attaque getPluginAttaque(){
		return this.pluginAttaque;
	}
	
	/**
	 * Setter Plugin_Attaque
	 * @param Plugin_Attaque pluginAttaque
	 */
	public void setPluginAttaque(Plugin_Attaque pluginAttaque) {
		this.pluginAttaque = pluginAttaque;
	}

	/**
	 * Setter Plugin_Deplacement
	 * @param Plugin_Deplacement pluginDeplacement
	 */
	public void setPluginDeplacement(Plugin_Deplacement pluginDeplacement) {
		this.pluginDeplacement = pluginDeplacement;
	}

	/**
	 * Méthode pour afficher en claire dans la console 
	 */
	@Override
	public String toString() {
		return "Robot [identifiant=" + identifiant + ", position=" + position
				+ ", nombrePDV=" + nombrePDV + ", energie=" + energie + "]";
	}
	
	/**
	 * Setter de l'identifiant du robot
	 * @param String identifiant
	 */
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Getter de l'identifiant du robot
	 * @return String identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * Getter de la couleur du robot
	 * @return Color
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * Getter du plugin graphique couleur
	 * @return Plugin_Graphique_Couleur
	 */
	public Plugin_Graphique_Couleur getPluginCouleur(){
		return this.pluginCouleur;
	}
	
	/**
	 * Setter plugin couleur
	 * @param Plugin_Graphique_Couleur
	 */
	public void setPluginCouleur(Plugin_Graphique_Couleur pluginCouleur) {
		this.pluginCouleur = pluginCouleur;
		this.couleur = pluginCouleur.getColor(this.couleur ,this.nombrePDV, this.nombrePDV); 
	}

	/**
	 * Getter Plugin_Graphique_Forme
	 * @return Plugin_Graphique_Forme
	 */
	public Plugin_Graphique_Forme getPluginForme() {
		return pluginForme;
	}

	
	/**
	 * Setter Plugin_Graphique_Forme
	 * @param Plugin_Graphique_Forme pluginForme
	 */
	public void setPluginForme(Plugin_Graphique_Forme pluginForme) {
		this.pluginForme = pluginForme;
	}

	/**
	 * Setter Position
	 * @param Point position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Setter couleur
	 * @param Color couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	
}
