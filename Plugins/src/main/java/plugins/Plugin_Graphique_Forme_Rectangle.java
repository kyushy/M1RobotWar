package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Plugin_Graphique_Forme_Rectangle implements Plugin_Graphique_Forme, Serializable {

	private static final long serialVersionUID = -5398649745230632518L;

	/**
	 * Dessine un rectangle
	 * 
	 * @see plugins.Plugin_Graphique_Forme#dessinerForme(java.awt.Color, java.awt.Graphics, int, int, int, int)
	 */
	public void dessinerForme(Color c, Graphics g, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

}