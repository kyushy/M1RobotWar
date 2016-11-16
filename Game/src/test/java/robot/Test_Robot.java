package robot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test_Robot {

	private static Robot robotTest;
	
	@Before
	public void initialize() {
		robotTest = new Robot(0,0);
	}
	
	@Test
	public void test_distanceEntreRobots() {
		Robot robotTest2 = new Robot(1,0);
		double distanceCalculee = Robot.distanceEntreRobots(robotTest, robotTest2);
		
		Robot robotTest3 = new Robot(0,3);
		double distanceCalculee2 = Robot.distanceEntreRobots(robotTest, robotTest3);
		
		Robot robotTest4 = new Robot(1,3);
		double distanceCalculee3 = Robot.distanceEntreRobots(robotTest, robotTest4);
		
		assertTrue(distanceCalculee2 > distanceCalculee 
				&& distanceCalculee3 > distanceCalculee2
				&& distanceCalculee3 > distanceCalculee);
	}
	
}