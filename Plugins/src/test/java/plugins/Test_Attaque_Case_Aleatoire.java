package plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import plugins.Plugin_Attaque_Case_Aleatoire;

public class Test_Attaque_Case_Aleatoire {
	
	private static Plugin_Attaque_Case_Aleatoire pluginTest;
	private static int longueurArene = 10;
	private static int largueurArene = 5;
	
	@Before
	public void initialize() {
		pluginTest = new Plugin_Attaque_Case_Aleatoire();
	}
	
	@Test
	public void test_attaqueListeRemplie() {
		HashMap<String, Object> dictAttaque = pluginTest.attaque(new Point(0,0), longueurArene, largueurArene);
		ArrayList<Point> listeLieux = (ArrayList<Point>) dictAttaque.get("LIEU");
	
		assertTrue(dictAttaque.get("ENERGIE").getClass() == Integer.class
			&& dictAttaque.get("PUISSANCE").getClass() == Integer.class
			&& listeLieux.get(0).getClass() == java.awt.Point.class);
	}
	
	@Test
	public void test_attaqueLieuCoherent() {
	
		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> dictAttaque = pluginTest.attaque(new Point(0,0), longueurArene, largueurArene);
			ArrayList<Point> listeLieux = (ArrayList<Point>) dictAttaque.get("LIEU");
			Point lieu = (Point) listeLieux.get(0);
			assertTrue(lieu.x >= 0 && lieu.x < longueurArene 
					&& lieu.y >= 0 && lieu.y < largueurArene);
		}
		
	}
	
	@Test
	public void test_attaqueLieuBorneZero() {
		boolean borneZeroXTrouvee = false;
		boolean borneZeroYTrouvee = false;

		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> dictAttaque = pluginTest.attaque(new Point(0,0), longueurArene, largueurArene);
			
			ArrayList<Point> listeLieux = (ArrayList<Point>) dictAttaque.get("LIEU");
			Point lieu = (Point) listeLieux.get(0);

			if (lieu.x == 0) {
				borneZeroXTrouvee = true;
			}
			if (lieu.y == 0) {
				borneZeroYTrouvee = true;
			}
		}
		
		assertTrue(borneZeroXTrouvee && borneZeroYTrouvee);
	}
	
	@Test
	public void test_attaqueLieuBorneArene() {
		boolean borneZeroLongueurTrouvee = false;
		boolean borneZeroLargueurTrouvee = false;

		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> dictAttaque = pluginTest.attaque(new Point(0,0), longueurArene, largueurArene);
			ArrayList<Point> listeLieux = (ArrayList<Point>) dictAttaque.get("LIEU");
			Point lieu = (Point) listeLieux.get(0);
			
			if (lieu.x == longueurArene-1) {
				borneZeroLongueurTrouvee = true;
			}
			if (lieu.y == largueurArene-1) {
				borneZeroLargueurTrouvee = true;
			}
		}
		
		assertTrue(borneZeroLongueurTrouvee && borneZeroLargueurTrouvee);
	}
}
