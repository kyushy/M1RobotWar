package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Test_Plugin_Attaque_Courte_Portee {
	
	private static Plugin_Attaque_Courte_Portee pluginTest;
	private static int longueurArene = 10;
	private static int largueurArene = 5;
	
	@Before
	public void initialize() {
		pluginTest = new Plugin_Attaque_Courte_Portee();
	}
	
	@Test
	public void test_attaqueResCoherent() { // Cas portee 1, TODO
		
		// OK
		Point point1 = new Point(1,0);
		Point point2 = new Point(1,1);
		Point point3 = new Point(0,1);
		
		// Pas 0,0 car auto-attaque
		Point point4 = new Point(0,0);
		// Pas de -1 sinon hors borne
		Point point5 = new Point(-1,0);
		Point point6 = new Point(-1,-1);
		Point point7 = new Point(-1,0);
		
		HashMap<String, Object> dicAttaque = pluginTest.attaque(
					new Point(0,0), longueurArene, largueurArene);
		ArrayList<Point> listeLieux = (ArrayList<Point>) dicAttaque.get("LIEU");
		assertTrue(listeLieux.contains(point1) &&
				listeLieux.contains(point2) &&
				listeLieux.contains(point3) &&
				! listeLieux.contains(point4) &&
				! listeLieux.contains(point5) &&
				! listeLieux.contains(point6) &&
				! listeLieux.contains(point7)
				);
		
	}

}
