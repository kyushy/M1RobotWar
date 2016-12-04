package plugins;

import java.awt.Color;
import java.io.Serializable;

public class Plugin_Graphique_Couleur_Aleatoire implements Plugin_Graphique_Couleur, Serializable {

	public Color getColor(int pv, int pvMax) {
			int r = (int) Math.round(Math.random() * 255);
			int g = (int) Math.round(Math.random() * 255);
			int b = (int) Math.round(Math.random() * 255);
			 
			return new Color(r, g, b);
	}

}
