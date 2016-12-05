package plugins;

import java.awt.Color;
import java.awt.Graphics;

public interface Plugin_Graphique_Forme {
	
	/**
	 * Dessiner le robot sous une certaine forme, en prenant plusieurs parametres
	 * 
	 * @param c Color Couleur du robot
	 * @param g Graphics Objet sur lequel dessiner
	 * @param x int Position coordonnnee x du robot
	 * @param y int Position coordonnnee y du robot
	 * @param width int Largueur de la forme a dessiner
	 * @param height int Hauteur de la forme a dessiner
	 */
	public void dessinerForme(Color c, Graphics g, int x, int y, int width, int height);

}
