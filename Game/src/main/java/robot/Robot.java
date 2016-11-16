package robot;

import java.awt.Point;

/**
 * Class representant un robot
 */

public class Robot {

	private Point position;
	private int nombrePDV = 10;
	private int energie = 10;
	
	public Robot(Point pPosition){
		this.position = pPosition;
	}
	
	public void getAction(){
		try {
			Class.forName("Plugins.Plugin_Deplacement");
		} catch (ClassNotFoundException e) {
			System.out.println("Plugin non trouve");
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcul de la distance entre deux robots
	 * @param robot1
	 * @param robot2
	 * @return double
	 */
	public static double distanceEntreRobots(Robot robot1, Robot robot2) {
		int x1 = robot1.getPosition().x;
		int y1 = robot1.getPosition().y;
		int x2 = robot2.getPosition().x;
		int y2 = robot2.getPosition().y;

		return (double) Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	}
	
	
	public Point getPosition() {
		return this.position;
	}

	public int getNombrePDV() {
		return nombrePDV;
	}


	public void setNombrePDV(int nombrePDV) {
		this.nombrePDV = nombrePDV;
	}


	public int getEnergie() {
		return energie;
	}


	public void setEnergie(int energie) {
		this.energie = energie;
	}


	@Override
	public String toString() {
		return "Robot [nombrePDV=" + nombrePDV + ", energie=" + energie + "]";
	}

	
}
