package moteur;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Test_SystemeSauvegarde {

	private static SystemeSauvegarde systemeSauvegardeTest;
	
	@Before
	public void initialize() {
		MoteurDeJeu mdj = new MoteurDeJeu(2, 10, 10);
		for (int i = 0; i < mdj.getListeRobot().size(); i++) {
			mdj.getListeRobot().get(i).setIdentifiant(i+"");
		}
		systemeSauvegardeTest = new SystemeSauvegarde(mdj);
		
	}
	
	@Test
	public void test_sauvegarder() {
		HashMap<String, Object> savePartie = systemeSauvegardeTest.sauvegarder(new File("test_robotwarplay.ser"));
		
		ArrayList<HashMap<String, Object>> dataRobots = (ArrayList<HashMap<String, Object>>) savePartie.get("Robot");
		//System.out.println("dataRobots : " + dataRobots);
		
		HashMap<String, Object> dataRobot1 = ((HashMap<String, Object>) dataRobots.get(0));
		//System.out.println("dataRobot : " + dataRobot1);
		
		Point point = (Point) dataRobot1.get("position");
		int cordX = point.x;
		// Voir si les informations de chaque type sont recuperees correctement
		assertTrue((Integer)dataRobot1.get("energie") == 10
				&& dataRobot1.get("pluginCouleur").toString().contains("plugins.Plugin_Graphique_Couleur")
				&& (Color) dataRobot1.get("couleur") != null
				&& cordX >= 0
				);
		
		HashMap<String, Object> dataRobot2 = ((HashMap<String, Object>) dataRobots.get(1));
		//System.out.println("dataRobot2 : " + dataRobot2);
		
		// ... S'il y en a plusieurs aussi
		Point point2 = (Point) dataRobot2.get("position");
		int cordX2 = point2.x;
		assertTrue((Integer)dataRobot2.get("energie") == 10
				&& dataRobot2.get("pluginCouleur").toString().contains("plugins.Plugin_Graphique_Couleur")
				&& (Color) dataRobot2.get("couleur") != null
				&& cordX2 >= 0
				);
	}
	
	@Test
	public void test_chargerSauvegarde() {
		HashMap<String, Object> savePartie = systemeSauvegardeTest.chargerSauvegarde(new File("test_robotwarplay.ser"));
		
		ArrayList<HashMap<String, Object>> dataRobots = (ArrayList<HashMap<String, Object>>) savePartie.get("Robot");
		//System.out.println("dataRobots : " + dataRobots);
		
		HashMap<String, Object> dataRobot1 = ((HashMap<String, Object>) dataRobots.get(0));
		//System.out.println("dataRobot : " + dataRobot1);
		
		Point point = (Point) dataRobot1.get("position");
		int cordX = point.x;
		// Voir si les informations de chaque type sont recuperees correctement
		assertTrue((Integer)dataRobot1.get("energie") == 10
				&& dataRobot1.get("pluginCouleur").toString().contains("plugins.Plugin_Graphique_Couleur")
				&& (Color) dataRobot1.get("couleur") != null
				&& cordX >= 0
				);
		
		HashMap<String, Object> dataRobot2 = ((HashMap<String, Object>) dataRobots.get(1));
		//System.out.println("dataRobot2 : " + dataRobot2);
		
		// ... S'il y en a plusieurs aussi
		Point point2 = (Point) dataRobot2.get("position");
		int cordX2 = point2.x;
		assertTrue((Integer)dataRobot2.get("energie") == 10
				&& dataRobot2.get("pluginCouleur").toString().contains("plugins.Plugin_Graphique_Couleur")
				&& (Color) dataRobot2.get("couleur") != null
				&& cordX2 >= 0
				);
	}
	
}