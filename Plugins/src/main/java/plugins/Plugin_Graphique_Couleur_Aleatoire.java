package plugins;

import java.awt.Color;
import java.io.Serializable;

public class Plugin_Graphique_Couleur_Aleatoire implements Plugin_Graphique_Couleur, Serializable {

	private static final long serialVersionUID = -8857975326264624689L;

	/**
	 * Genere une nouvelle couleur aleatoire si le robot n'en a pas deja une
	 * @see plugins.Plugin_Graphique_Couleur#getColor(java.awt.Color, int, int)
	 */
	public Color getColor(Color c, int pv, int pvMax) {
		
		if(c == null){
			int r = (int) Math.round(Math.random() * 255);
			int g = (int) Math.round(Math.random() * 255);
			int b = (int) Math.round(Math.random() * 255);
			 
			return new Color(r, g, b);
		}
		return c;
	}

}
