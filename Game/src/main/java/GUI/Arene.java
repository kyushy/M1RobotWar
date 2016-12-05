package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import robot.Robot;

/**
 *	Classe qui represente la zone graphique ou les robots s'affrontent. 
 *	Author : Frederic
 */


public class Arene extends JPanel{

	private int longueur;
	private int largeur;
	

	/**
	 * Constructeur
	 * @param longueur nombre de case en longueur
	 * @param largeur nombre de case en largeur
	 */
	public Arene (int longueur, int largeur){
		
		this.longueur = longueur;
		this.largeur = largeur;

		GridLayout layout = new GridLayout(largeur, longueur);
		this.setLayout(layout);
		this.setBackground(Color.WHITE);

		 
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int i =0; i<(largeur*longueur); i++){
		    JPanel panel = new JPanel(new BorderLayout());
		    this.add(panel);
		}
			
	} 
	

	
	public int getLongueur() {
		return longueur;
	}

	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Dessine le robot dans le panel correspondant à sa position
	 * @param r robot à dessiner
	 * @param x position en X
	 * @param y position en Y
	 */
	public void paintPanel(Robot r, int x, int y){
		JPanel panel = (JPanel) this.getComponent(y*10 + x);

		GRobot gr = new GRobot(r);
		panel.add(gr);
		gr.paintComponent(panel.getGraphics());
	}
	
	/**
	 * Applique l'animation de l'attaque sur la cible
	 * @param r le robot ciblé
	 */
	public void applyAnimation(Robot r){
		JPanel panel = (JPanel) this.getComponent(r.getPosition().y*10 + r.getPosition().x);
		r.getPluginAttaque().animationAttaque(panel.getGraphics(), new Point(0,0));		
	}
	
}