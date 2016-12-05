package plugins;

import java.awt.Point;

public interface Plugin_Deplacement {
	
	/**
	 * Determine une nouvelle position au robot (peut etre la meme)
	 * 
	 * @param positionActuelle Point Position actuelle du robot
	 * @param longueurArene int Pour ne pas sortir des bornes
	 * @param largeurArene int Pour ne pas sortir des bornes 
	 * @return Point nouvelle position proposee pour le robot
	 */
	public Point getNouvellePosition(Point positionActuelle, int longueurArene, int largeurArene);

}
