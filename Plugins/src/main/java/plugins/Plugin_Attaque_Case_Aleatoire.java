package plugins;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Plugin_Attaque_Case_Aleatoire implements Plugin_Attaque {
	
	private static int ENERGIE = 1;
	private static int PUISSANCE = 1;

	public HashMap<String, Object> attaque(Point positionAttaquant, 
			int longueurArene, int largeurArene) {
		HashMap<String, Object> dicAttaque = new HashMap<String, Object>();
		
		int maxRandomX = longueurArene;
		int minRandomX = 0;
		int lieuX = (int) (minRandomX + (Math.random() * (maxRandomX - minRandomX)));
		
		int maxRandomY = largeurArene;
		int minRandomY = 0;
		int lieuY = (int) (minRandomY + (Math.random() * (maxRandomY - minRandomY)));
		
		dicAttaque.put("ENERGIE", ENERGIE);
		dicAttaque.put("PUISSANCE", PUISSANCE);
		
		ArrayList<Point> listeLieux = new ArrayList<Point>();
		listeLieux.add(new Point(lieuX, lieuY));
		dicAttaque.put("LIEU", listeLieux);
		
		return dicAttaque;
	}

}
