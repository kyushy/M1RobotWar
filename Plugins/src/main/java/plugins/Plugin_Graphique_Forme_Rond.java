package plugins;

import java.awt.Graphics;
import java.io.Serializable;

public class Plugin_Graphique_Forme_Rond implements Plugin_Graphique_Forme, Serializable {

	public void dessinerForme(Graphics g, int x, int y, int width, int height) {
		g.drawOval(x, y, width, height);
	}

}
