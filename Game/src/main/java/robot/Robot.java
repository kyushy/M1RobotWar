package robot;

import java.awt.Color;

/**
 * Class representant un robot
 */

public class Robot {

	/*
	 *Position en X et en Y sur la grille du robot 
	 */
	private int x;
	private int y;
	private Color color;
	
	private int nombrePDV = 10;
	private int energie = 10;
	
	public Robot(int x, int y){
		this.x = x;
		this.y = y;
		this.SetCouleur();
	}
	
	
	//The Color constructor will also take integers in the range of 0 to 255. Like 0.0 to 1.0, 0 is as little of a color as possible and 255 is 100% of a color. So we can also create 3 random ints in that range for our colors.

	public void SetCouleur(){
		int r = (int) Math.round(Math.random() * 255);
		int g = (int) Math.round(Math.random() * 255);
		int b = (int) Math.round(Math.random() * 255);
		 
		this.color = new Color(r, g, b);
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
		int x1 = robot1.getX();
		int y1 = robot1.getY();
		int x2 = robot2.getX();
		int y2 = robot2.getY();

		return (double) Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (energie != other.energie)
			return false;
		if (nombrePDV != other.nombrePDV)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Robot [color=" + color + ", nombrePDV=" + nombrePDV + ", energie=" + energie + "]";
	}

	
}
