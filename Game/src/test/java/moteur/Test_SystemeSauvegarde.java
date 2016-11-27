package moteur;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Test_SystemeSauvegarde {

	private static SystemeSauvegarde systemeSauvegardeTest;
	
	@Before
	public void initialize() {
		systemeSauvegardeTest = new SystemeSauvegarde(new MoteurDeJeu(2, 10, 10));
	}
	
	@Test
	public void test_sauvegarder() {
		HashMap<String, Object> savePartie = systemeSauvegardeTest.sauvegarder();
		
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
	}
	
}