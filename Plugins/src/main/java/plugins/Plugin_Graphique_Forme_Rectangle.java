package plugins;

import java.awt.Graphics;
import java.io.Serializable;

public class Plugin_Graphique_Forme_Rectangle implements Plugin_Graphique_Forme, Serializable {

	public void dessinerForme(Graphics g, int x, int y, int width, int height) {
		g.fillRect(x, y, 20, 20);
	}

}