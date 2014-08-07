package SensorNodes;

import java.util.Scanner;

import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class WallDistance extends Sensor{

	@Override
	public int evaluate(Robot robot) {
		return robot.getDistanceToWall();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("wallDist", scan)){ Parser.fail("Should be wallDist node.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return "wallDist";
	}
}
