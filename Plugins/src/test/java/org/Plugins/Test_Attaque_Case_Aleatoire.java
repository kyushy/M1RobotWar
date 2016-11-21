package org.Plugins;

import static org.junit.Assert.*;

import java.awt.Point;
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
		HashMap<String, Object> dictAttaque = pluginTest.attaque(longueurArene, largueurArene);
		assertTrue(dictAttaque.get("ENERGIE").getClass() == Integer.class
			&& dictAttaque.get("PUISSANCE").getClass() == Integer.class
			&& dictAttaque.get("LIEU").getClass() == java.awt.Point.class);
	}
	
	@Test
	public void test_attaqueLieuCoherent() {
	
		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> dictAttaque = pluginTest.attaque(longueurArene, largueurArene);
			Point lieu = (Point) dictAttaque.get("LIEU");
			assertTrue(lieu.x >= 0 && lieu.x < longueurArene 
					&& lieu.y >= 0 && lieu.y < largueurArene);
		}
		
	}
	
	@Test
	public void test_attaqueLieuBorneZero() {
		boolean borneZeroXTrouvee = false;
		boolean borneZeroYTrouvee = false;

		for (int i = 0; i < 1000; i++) {
			HashMap<String, Object> dictAttaque = pluginTest.attaque(longueurArene, largueurArene);
			Point lieu = (Point) dictAttaque.get("LIEU");
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
			HashMap<String, Object> dictAttaque = pluginTest.attaque(longueurArene, largueurArene);
			Point lieu = (Point) dictAttaque.get("LIEU");
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
