package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Plugin_Attaque_Longue_Portee_Aleatoire implements Plugin_Attaque, Serializable {
	
	private static final long serialVersionUID = 29294895951442903L;
	/**
	 * Nombre d'energie depense suite a cette attaque
	 */
	private static int ENERGIE = 2;
	/**
	 * Nombre de PV perdus par le robot victime
	 */
	private static int PUISSANCE = 2;

	/**
	 * Attaque de longue portee qui vise quatre cases aleatoires (robot ennemi ou non) 
	 * exceptee la case de l'attaquant sur l'ensemble du terrain
	 * 
	 * @see plugins.Plugin_Attaque#attaque(java.awt.Point, int, int)
	 */
	public HashMap<String, Object> attaque(Point positionAttaquant, 
			int longueurArene, int largeurArene) {
		
		HashMap<String, Object> dicAttaque = new HashMap<String, Object>();
		
		int maxRandomX = longueurArene;
		int minRandomX = 0;
		
		int maxRandomY = largeurArene;
		int minRandomY = 0;
		
		ArrayList<Point> listeLieux = new ArrayList<Point>();
		
		while (listeLieux.size() < 4) {
			
			int lieuX = (int) (minRandomX + (Math.random() * (maxRandomX - minRandomX)));
			int lieuY = (int) (minRandomY + (Math.random() * (maxRandomY - minRandomY)));
			Point lieu = new Point(lieuX, lieuY);
			
			// Ne pas viser deux fois au même endroit et ne pas s'auto-attaquer
			if (! listeLieux.contains(lieu) && ! lieu.equals(positionAttaquant)) {
				listeLieux.add(new Point(lieuX, lieuY));
			}
		}
		
		dicAttaque.put("LIEU", listeLieux);
		dicAttaque.put("ENERGIE", ENERGIE);
		dicAttaque.put("PUISSANCE", PUISSANCE);
		
		return dicAttaque;
	}

	/**
	 * Affiche un rond de couleur noir 10x10 sur le robot attaque
	 * 
	 * @see plugins.Plugin_Attaque#animationAttaque(java.awt.Graphics, java.awt.Point)
	 */
	public void animationAttaque(Graphics g, Point position) {
		g.setColor(Color.BLACK);
		g.fillOval(position.x, position.y, 10, 10);		
	}

}
