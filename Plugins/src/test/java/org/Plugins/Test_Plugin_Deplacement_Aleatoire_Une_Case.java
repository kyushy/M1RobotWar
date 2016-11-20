package org.Plugins;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import plugins.Plugin_Deplacement_Aleatoire_Une_Case;

public class Test_Plugin_Deplacement_Aleatoire_Une_Case {

	private static Plugin_Deplacement_Aleatoire_Une_Case pluginTest;
	private static int longueurArene = 10;
	private static int largueurArene = 5;
	
	@Before
	public void initialize() {
		pluginTest = new Plugin_Deplacement_Aleatoire_Une_Case();
	}
	
	@Test
	public void test_getNouvellePositionNeSortPasEnZero() {
		for (int i = 0; i < 100; i++) {
			Point nouvellePosition = pluginTest.getNouvellePosition(
					new Point(0,0), longueurArene, largueurArene);
			assertTrue(nouvellePosition.x >= 0 && nouvellePosition.y >= 0);
		}
	}
	
	@Test
	public void test_getNouvellePositionNeSortPasArene() {
		for (int i = 0; i < 100; i++) {
			Point nouvellePosition = pluginTest.getNouvellePosition(
					new Point(longueurArene-1,largueurArene-1), longueurArene, largueurArene);
			assertTrue(nouvellePosition.x <= longueurArene && nouvellePosition.y <= largueurArene);
		}
	}
	
	@Test
	public void test_getNouvellePositionDifferentesMilieu() {
		ArrayList<Point> listePositions = new ArrayList<Point>();
		boolean differenteTrouvee = false;
		listePositions.add(pluginTest.getNouvellePosition(new Point((int) longueurArene/2, (int) largueurArene/2), longueurArene, largueurArene));
		
		for (int i = 0; i < 100; i++) {
			Point nouvellePosition = pluginTest.getNouvellePosition(
					new Point((int) longueurArene/2, (int) largueurArene/2), longueurArene, largueurArene);
			
			if (listePositions.indexOf(nouvellePosition) == -1) {
				differenteTrouvee = true;
				break;
			}
		}
		
		assertTrue(differenteTrouvee);
	}
	
	@Test
	public void test_getNouvellePositionDifferentesBorneZero() {
		ArrayList<Point> listePositions = new ArrayList<Point>();
		boolean differenteTrouvee = false;
		listePositions.add(pluginTest.getNouvellePosition(new Point(0,0), longueurArene, largueurArene));
		
		for (int i = 0; i < 100; i++) {
			Point nouvellePosition = pluginTest.getNouvellePosition(
					new Point(0,0), longueurArene, largueurArene);
			
			if (listePositions.indexOf(nouvellePosition) == -1) {
				differenteTrouvee = true;
				break;
			}
		}
		
		assertTrue(differenteTrouvee);
	}
	
	@Test
	public void test_getNouvellePositionDifferentesBorneArene() {
		ArrayList<Point> listePositions = new ArrayList<Point>();
		boolean differenteTrouvee = false;
		listePositions.add(pluginTest.getNouvellePosition(new Point(longueurArene-1,largueurArene-1), longueurArene, largueurArene));
		
		for (int i = 0; i < 100; i++) {
			Point nouvellePosition = pluginTest.getNouvellePosition(
					new Point(longueurArene-1,largueurArene-1), longueurArene, largueurArene);
			
			if (listePositions.indexOf(nouvellePosition) == -1) {
				differenteTrouvee = true;
				break;
			}
		}
		
		assertTrue(differenteTrouvee);
	}

}
