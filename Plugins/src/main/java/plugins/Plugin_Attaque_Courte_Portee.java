package plugins;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Plugin_Attaque_Courte_Portee implements Plugin_Attaque, Serializable {
	
	private static int ENERGIE = 1;
	private static int PUISSANCE = 1;
	private static int PORTEE = 1;
	// TODO : graphisme > image d'un poing aux endroits vises ?

	/*
	 * Attaque faible de courte portee qui vise les cases autour (diagonales comprises) 
	 * sur le terrain
	 * @see plugins.Plugin_Attaque#attaque(java.awt.Point, int, int)
	 * @return HashMap<String, Object> dicAttaque avec "LIEU"=>ArrayList<Point>, "ENERGIE"=>int et "PUISSANCE"=>int
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
		
		/*System.out.println("champs action :");
		for (int i = 0; i < chgX.length; i++) {
			System.out.print(chgX[i] + ", ");
		}
		System.out.println();*/
		
		for (int i = 0; i < chgX.length; i++) {
			for (int j = 0; j < chgY.length; j++) {
				// Cas auto-attaque
				if (chgX[i] == 0 && chgY[j] == 0) { continue; }
				
				//System.out.println("chgX[i]=" + chgX[i] + ", chgY=" + chgY[j]);
				
				int xAttaque = positionAttaquant.x + chgX[i];
				int yAttaque = positionAttaquant.y + chgY[j];
				//System.out.println("essai (" + xAttaque + ", " + yAttaque + ")");
				
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
}
