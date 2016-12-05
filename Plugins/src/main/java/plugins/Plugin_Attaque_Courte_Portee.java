package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Plugin_Attaque_Courte_Portee implements Plugin_Attaque, Serializable {
	
	private static final long serialVersionUID = -4554029999891306493L;
	/**
	 * Nombre d'energie depense suite a cette attaque
	 */
	private static int ENERGIE = 1;
	/**
	 * Nombre de PV perdus par le robot victime
	 */
	private static int PUISSANCE = 1;
	/** 
	 * Portee de l'attaque (nombre de case autour visee)
	 */
	private static int PORTEE = 1;

	/**
	 * Attaque faible de courte portee qui vise les cases autour (diagonales comprises) 
	 * sur le terrain
	 * @see plugins.Plugin_Attaque#attaque(java.awt.Point, int, int)
	 */
	public HashMap<String, Object> attaque(Point positionAttaquant, 
			int longueurArene, int largeurArene) {
		
		HashMap<String, Object> dicAttaque = new HashMap<String, Object>();
		
		ArrayList<Point> listeLieux = new ArrayList<Point>();
		
		int[] chgX = new int[PORTEE*2+1], chgY = new int[PORTEE*2+1];
		int iterator = 0;
		for (int i = -PORTEE; i <= PORTEE; i++) {
			chgX[iterator] = i;
			chgY[iterator] = i;
			iterator++;
		}
		
		for (int i = 0; i < chgX.length; i++) {
			for (int j = 0; j < chgY.length; j++) {
				// Cas auto-attaque
				if (chgX[i] == 0 && chgY[j] == 0) { continue; }
				
				int xAttaque = positionAttaquant.x + chgX[i];
				int yAttaque = positionAttaquant.y + chgY[j];

				// Ne pas sortir des bornes
				if (xAttaque < 0 || xAttaque > longueurArene-1 
						|| yAttaque < 0 || yAttaque > largeurArene-1 ) {
					continue;
				}
						
				listeLieux.add(new Point(xAttaque, yAttaque));
			}
		}
		
		dicAttaque.put("ENERGIE", ENERGIE);
		dicAttaque.put("PUISSANCE", PUISSANCE);
		dicAttaque.put("LIEU", listeLieux);
		return dicAttaque;
	}

	/**
	 * Affiche un rond de couleur rouge 40x40 sur le robot attaque
	 * @see plugins.Plugin_Attaque#animationAttaque(java.awt.Graphics, java.awt.Point)
	 */
	public void animationAttaque(Graphics g, Point position) {
		g.setColor(Color.RED);
		g.fillOval(position.x, position.y, 40, 40);	
	}
}
