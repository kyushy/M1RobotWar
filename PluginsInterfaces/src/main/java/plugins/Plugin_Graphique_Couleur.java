package plugins;

import java.awt.Color;

public interface Plugin_Graphique_Couleur {
	
	/**
	 * Generer une couleur selon l'etat du robot (ou non)
	 * 
	 * @param c Color Couleur actuelle du robot (peut être null)
	 * @param pv int Nombre de PV actuel du robot
	 * @param pvMax int Nombre de PV max du robot
	 * @return Color Nouvelle couleur du robot
	 */
	public Color getColor(Color c, int pv, int pvMax);

}
