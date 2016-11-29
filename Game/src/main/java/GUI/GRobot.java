package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import plugins.Plugin_Graphique_Couleur;
import plugins.Plugin_Graphique_Forme;
import robot.Robot;

public class GRobot extends Component {

	private Robot data;
	private Plugin_Graphique_Couleur pluginGraphiqueCouleur;
	private Plugin_Graphique_Forme pluginGraphiqueForme;
	
	public GRobot(Robot data, Plugin_Graphique_Couleur graphiqueCouleur, 
			Plugin_Graphique_Forme graphiqueForme){
		this.data = data;
		this.pluginGraphiqueCouleur = graphiqueCouleur;
		this.pluginGraphiqueForme = graphiqueForme;
	}
	
	public Robot getData(){
		return this.data;
	}
	
	@Override
	public void paint(Graphics g){
		//g.setColor(pluginGraphiqueCouleur.getColor());
		g.setColor(Color.BLUE);
		//this.pluginGraphiqueForme.dessinerForme(g, data.getPosition().x, data.getPosition().y, 20, 20);
		g.fillRect(data.getPosition().x, data.getPosition().y, 20, 20);
	}
}
