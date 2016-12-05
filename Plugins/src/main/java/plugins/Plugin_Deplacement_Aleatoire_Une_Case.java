package plugins;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

public class Plugin_Deplacement_Aleatoire_Une_Case implements Plugin_Deplacement, Serializable {

	private static final long serialVersionUID = -2187265944145739198L;

	/**
	 * Nouvelles coordonnees aleatoires avec deplacement d'une case (inclus diagonale)
	 * 
	 * @see plugins.Plugin_Deplacement#getNouvellePosition(java.awt.Point, int, int)
	 */
	public Point getNouvellePosition(Point positionActuelle, int longueurArene,
			int largeurArene) {
		
		int nouveauX = positionActuelle.x;
		int nouveauY = positionActuelle.y;
		
		boolean avancerX = new Random().nextBoolean();
		boolean avancerY = new Random().nextBoolean();
		
		if (avancerX && positionActuelle.x + 1 < longueurArene) {
			nouveauX = positionActuelle.x + 1;
		}
		else if (! avancerX && positionActuelle.x - 1 >= 0 ) {
			nouveauX = positionActuelle.x - 1;
		}
		
		if (avancerY && positionActuelle.y + 1 < largeurArene) {
			nouveauY = positionActuelle.y + 1;
		}
		else if (! avancerY && positionActuelle.y - 1 >= 0) {
			nouveauY = positionActuelle.y - 1;
		}
		
		return new Point(nouveauX, nouveauY);
		
	}

}
