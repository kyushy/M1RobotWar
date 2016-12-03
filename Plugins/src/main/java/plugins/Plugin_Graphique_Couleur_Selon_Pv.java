package plugins;

import java.awt.Color;
import java.io.Serializable;

//TODO : changer getColor ? Nouvelle interface ?
public class Plugin_Graphique_Couleur_Selon_Pv implements Plugin_Graphique_Couleur, Serializable {

	public Color getColor(int pv, int pvMax) {
		
		int pourcentageRestant = pv*pvMax/pvMax;
		
		if (pourcentageRestant >= 50) {
			return Color.GREEN;
		}
		if (pourcentageRestant >= 25) {
			return Color.ORANGE;
		}
		return Color.RED;
		
	}

}
