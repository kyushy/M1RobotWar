package GUI;

import java.awt.Graphics;

import javax.swing.JComponent;

import robot.Robot;

/**
 * Classe qui sert de container graphique pour les robots
 * @author Frederic
 *
 */
public class GRobot extends JComponent {

	private static final long serialVersionUID = 1L;
	private Robot r;
	
	/**
	 * Constructeur
	 * @param r modele du robot
	 */
	public GRobot(Robot r){
		this.r = r;
	}
	
	
	@Override
	public void paintComponent(Graphics g){	
		r.getPluginForme().dessinerForme(r.getPluginCouleur().getColor(r.getCouleur(), r.getNombrePDV(), 10), g, 0, 0,
				this.getParent().getWidth(), this.getParent().getHeight());
	}
}
