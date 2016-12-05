package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Plugin_Attaque_Longue_Portee_Aleatoire implements Plugin_Attaque, Serializable {
	
	private static int ENERGIE = 2;
	private static int PUISSANCE = 2;
	// TODO : graphisme > image d'un cible arc aux endroits vises ?

	/*
	 * Attaque de longue portee qui vise quatre cases aleatoires (robot ennemi ou non) 
	 * exceptee la case de l'attaquant sur l'ensemble du terrain
	 * @see plugins.Plugin_Attaque#attaque(java.awt.Point, int, int)
	 * @return HashMap<String, Object> dicAttaque avec "LIEU"=>ArrayList<Point>, "ENERGIE"=>int et "PUISSANCE"=>int
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

	public void atkGrapgique(Graphics g, Point position) {
		g.setColor(Color.BLACK);
		g.fillOval(position.x, position.y, 10, 10);		
	}

}
