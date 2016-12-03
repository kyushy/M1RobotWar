package moteur;

import static org.junit.Assert.*;

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
		System.out.println("dataRobot : " + dataRobot1);
		
		/*System.out.println("identifiant : " + dataRobot1.get("identifiant"));
		System.out.println("energie : " + dataRobot1.get("energie"));
		System.out.println("position : " + dataRobot1.get("position"));*/
		
		/*assertTrue(dataRobot1.get("identifiant") == "0"
				&& (Integer)dataRobot1.get("energie") == 10
				&& (Integer)dataRobot1.get("x") >= 0 
				&& (Integer)dataRobot1.get("y") >= 0
				&& (Integer)dataRobot1.get("nombrePDV") == 10
				);*/
		
		HashMap<String, Object> dataRobot2 = ((HashMap<String, Object>) dataRobots.get(1));
		System.out.println("dataRobot2 : " + dataRobot2);
		
		assertTrue((Integer)dataRobot2.get("energie") == 10
				&& (Integer)dataRobot2.get("x") >= 0 
				&& (Integer)dataRobot2.get("y") >= 0
				&& (Integer)dataRobot2.get("nombrePDV") == 10
				);
	}
	
	/*@Test
	public void test_chargerSauvegarde() {
		HashMap<String, Object> savePartie = systemeSauvegardeTest.chargerSauvegarde(new File("test_robotwarplay.ser"));
		
		ArrayList<HashMap<String, Object>> dataRobots = (ArrayList<HashMap<String, Object>>) savePartie.get("Robot");
		//System.out.println("dataRobots : " + dataRobots);

		HashMap<String, Object> dataRobot1 = ((HashMap<String, Object>) dataRobots.get(0));
		//System.out.println("dataRobot : " + dataRobot1);
		
		assertTrue((Integer)dataRobot1.get("energie") == 10
				&& (Integer)dataRobot1.get("x") >= 0 
				&& (Integer)dataRobot1.get("y") >= 0
				&& (Integer)dataRobot1.get("nombrePDV") == 10
				);
		
		HashMap<String, Object> dataRobot2 = ((HashMap<String, Object>) dataRobots.get(1));
		//System.out.println("dataRobot : " + dataRobot2);
		
		assertTrue((Integer)dataRobot2.get("energie") == 10
				&& (Integer)dataRobot2.get("x") >= 0 
				&& (Integer)dataRobot2.get("y") >= 0
				&& (Integer)dataRobot2.get("nombrePDV") == 10
				);
	}*/
	
}