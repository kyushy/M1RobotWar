package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import plugins.Plugin_Graphisme;
import robot.Robot;

public class GRobot extends Component {

	private Robot data;
	private Plugin_Graphisme pluginGraphique;
	
	public GRobot(Robot data, Plugin_Graphisme graphique){
		this.data = data;
		this.pluginGraphique = graphique;
	}
	
	public Robot getData(){
		return this.data;
	}
	
	@Override
	public void paint(Graphics g){
		//g.setColor(pluginGraphique.getColor());
		g.setColor(Color.BLUE);
		g.fillRect(data.getPosition().x, data.getPosition().y, 20, 20);
	}
}
