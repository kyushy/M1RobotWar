package moteur;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import robot.Robot;

public class Test_Moteur {

	private static MoteurDeJeu moteurDeJeuTest;
	
	@Before
	public void initialize() {
		moteurDeJeuTest = new MoteurDeJeu(0, 10, 10);
	}
	
	@Test
	public void test_robotExistListeVide() {
		Robot robotTest = new Robot("TEST", new Point(0,0));
		assertEquals(moteurDeJeuTest.robotExist(robotTest), false);
	}
	
	@Test
	public void test_robotExistListeNonVidePlusieursRobots() {
		ArrayList<Robot> robots = new ArrayList<Robot>();
		robots.add(new Robot("TEST", new Point(0,4)));
		robots.add(new Robot("TEST", new Point(0,5)));
		robots.add(new Robot("TEST", new Point(6,0)));
		moteurDeJeuTest.setListeRobot(robots);
		assertEquals(moteurDeJeuTest.robotExist(new Robot("TEST", new Point(0,5))), true);
	}
	
	@Test
	public void test_robotExistListeNonVideUnRobot() {
		ArrayList<Robot> robots = new ArrayList<Robot>();
		robots.add(new Robot("TEST", new Point(0,0)));
		moteurDeJeuTest.setListeRobot(robots);
		Robot robotUnique = moteurDeJeuTest.getListeRobot().get(0);
		Point nouvellePosition = new Point(robotUnique.getPosition().x+1, robotUnique.getPosition().y+1);
		Robot robotDifferent = new Robot("TEST", nouvellePosition);
		assertEquals(moteurDeJeuTest.robotExist(robotDifferent), false);
	}
	
	@Test
	public void test_creationRobotDifferents() {
		Robot robotCree = moteurDeJeuTest.creationRobot();
		boolean nonIdentiqueTrouve = false;
		for (int i = 0; i < 100; i++) {
			if (! moteurDeJeuTest.creationRobot().equals(robotCree)) {
				nonIdentiqueTrouve = true;
				break;
			}
		}
		assertEquals(nonIdentiqueTrouve, true);
	}
	
	@Test
	public void test_robotLePlusProchePlusieurs() {
		Robot robotReference = new Robot("TEST", new Point(0,0));
		ArrayList<Robot> robots = new ArrayList<Robot>();
		robots.add(new Robot("TEST", new Point(0,4)));
		robots.add(new Robot("TEST", new Point(0,5)));
		robots.add(new Robot("TEST", new Point(6,0)));
		moteurDeJeuTest.setListeRobot(robots);
		Robot robotPlusProche = moteurDeJeuTest.robotLePlusProche(robotReference);
		
		assertEquals(robotPlusProche.getPosition().x, 0);
		assertEquals(robotPlusProche.getPosition().y, 4);
	}
	
	@Test
	public void test_robotLePlusProcheVide() {
		Robot robotReference = new Robot("TEST", new Point(0,0));
		Robot robotPlusProche = moteurDeJeuTest.robotLePlusProche(robotReference);
		
		assertEquals(robotPlusProche, null);
	}
	
	@Test
	public void test_robotLePlusProcheJusteLui() {
		Robot robotReference = new Robot("TEST", new Point(0,0));
		ArrayList<Robot> robots = new ArrayList<Robot>();
		robots.add(robotReference);
		moteurDeJeuTest.setListeRobot(robots);
		Robot robotPlusProche = moteurDeJeuTest.robotLePlusProche(robotReference);
		
		assertEquals(robotPlusProche, null);
	}
	
	
	
}