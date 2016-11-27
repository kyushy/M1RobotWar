package robot;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import plugins.Plugin_Attaque;
import plugins.Plugin_Deplacement;

/**
 * Class representant un robot
 */

public class Robot {

	private String identifiant;
	private Point position;
	private int nombrePDV = 10;
	private int energie = 10;
	
	//PLUGIN FIX : On utilisera ça après
	private Plugin_Attaque pluginAttaque;
	private Plugin_Deplacement pluginDeplacement;
	
	public Robot(String identifiant, Point pPosition){
		this.position = pPosition;
		this.identifiant = identifiant;
	}
	
	public void getActionDeplacement(){

		//PLUGIN FIX
		Point newPosition = this.pluginDeplacement.getNouvellePosition(this.position, 10, 10);
		System.out.println("Je me deplace de " + position + " a " + newPosition);
		this.position = newPosition;
		
		// Passage par URLClassLoader pour plugin de déplacement aléatoire
		/* File customElementsDir = new File("../Plugins/target/classes");
		URL[] classLoaderUrls = null;
		try {
			URL url = customElementsDir.toURI().toURL();
			classLoaderUrls = new URL[]{ url };
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
		Class<?> beanClass = null;
		try {
			beanClass = urlClassLoader.loadClass("plugins.Plugin_Deplacement_Aleatoire_Une_Case");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Method method = null;
		try {
			Class[] cArg = new Class[3];
	        cArg[0] = java.awt.Point.class;
	        cArg[1] = int.class;
	        cArg[2] = int.class;
			method = beanClass.getDeclaredMethod("getNouvellePosition", cArg);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Constructor<?> cons = null;
		try {
			cons = beanClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Object o = null;
		try {
			o = cons.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Point nouvellePosition = (Point) method.invoke(o, position, 10, 10);
			System.out.println("Je me deplace de " + position + " a " + nouvellePosition);
			this.position = nouvellePosition;
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			urlClassLoader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */

	}
	
	public HashMap<String, Object> getActionAttaque(){
		
		//PLUGIN FIX
		return this.pluginAttaque.attaque(this.position, 10, 10);



		/*File customElementsDir = new File("../Plugins/target/classes");
		URL[] classLoaderUrls = null;
		try {
			URL url = customElementsDir.toURI().toURL();
			classLoaderUrls = new URL[]{ url };
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
		Class<?> beanClass = null;
		try {
			beanClass = urlClassLoader.loadClass("plugins.Plugin_Attaque_Case_Aleatoire");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Method method = null;
		try {
			Class[] cArg = new Class[2];
	        cArg[0] = int.class;
	        cArg[1] = int.class;
			method = beanClass.getDeclaredMethod("attaque", cArg);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Constructor<?> cons = null;
		try {
			cons = beanClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Object o = null;
		try {
			o = cons.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HashMap<String, Object> retourDicAttaque = null;
		try {
			//System.out.println("J'attaque a " + method.invoke(o, 10, 10));
			retourDicAttaque = (HashMap<String, Object>) method.invoke(o, 10, 10);
			System.out.println("Mon attaque : " + retourDicAttaque);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			urlClassLoader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retourDicAttaque;
		*/
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

	public void setPluginAttaque(Plugin_Attaque pluginAttaque) {
		this.pluginAttaque = pluginAttaque;
	}

	public void setPluginDeplacement(Plugin_Deplacement pluginDeplacement) {
		this.pluginDeplacement = pluginDeplacement;
	}

	@Override
	public String toString() {
		return "Robot [identifiant=" + identifiant + ", position=" + position
				+ ", nombrePDV=" + nombrePDV + ", energie=" + energie + "]";
	}

	public String getIdentifiant() {
		return identifiant;
	}

}
