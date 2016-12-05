package plugins;

import java.awt.Color;
import java.io.Serializable;


public class Plugin_Graphique_Couleur_Selon_Pv implements Plugin_Graphique_Couleur, Serializable {

	private static final long serialVersionUID = 1340117472387388298L;

	/**
	 * Diminue l'opacite de la couleur du robot si les PV baissent (pourcentage sur les PV max)
	 * 
	 * @see plugins.Plugin_Graphique_Couleur#getColor(java.awt.Color, int, int)
	 */
	public Color getColor(Color c, int pv, int pvMax) {
		
		if(c == null){
			int r = (int) Math.round(Math.random() * 255);
			int g = (int) Math.round(Math.random() * 255);
			int b = (int) Math.round(Math.random() * 255);
			
			return new Color(r, g, b, 255);
		}
		
		int pourcentageRestant = (pv*pvMax/pvMax)*10;
		
		if (pourcentageRestant <= 25) {
			return new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
		}
		if (pourcentageRestant <= 50) {
			return new Color(c.getRed(), c.getGreen(), c.getBlue(), 175);
		}
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
		
	}

}
