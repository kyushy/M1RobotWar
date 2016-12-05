package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Plugin_Graphique_Forme_Rond implements Plugin_Graphique_Forme, Serializable {

	private static final long serialVersionUID = -2382639448685266908L;

	/**
	 * Dessine un cercle
	 * 
	 * @see plugins.Plugin_Graphique_Forme#dessinerForme(java.awt.Color, java.awt.Graphics, int, int, int, int)
	 */
	public void dessinerForme(Color c, Graphics g, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}

}
