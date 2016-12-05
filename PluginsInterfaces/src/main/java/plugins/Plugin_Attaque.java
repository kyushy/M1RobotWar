package plugins;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

public interface Plugin_Attaque {
	
	/**
	 * Determine le(s) lieu(x) a attaquer, en prenant en compte les bornes de l'arene 
	 * et la position actuelle du robot attaquant
	 * 
	 * @param positionAttaquant Point Position du robot qui attaque
	 * @param longueurArene int Pour ne pas sortir des bornes
	 * @param largeurArene int Pour ne pas sortir des bornes
	 * @return HashMap<String, Object> dicAttaque avec "LIEU"=>ArrayList<Point>, "ENERGIE"=>int et "PUISSANCE"=>int
	 */
	public HashMap<String, Object> attaque(Point positionAttaquant, 
			int longueurArene, int largeurArene);
	
	/**
	 * Representation graphique de l'attaque
	 *
	 * @param g Objet Graphics sur lequel dessiner
	 * @param position Lieu attaque
	 */
	public void animationAttaque(Graphics g, Point position);

}
