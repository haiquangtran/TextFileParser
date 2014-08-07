package SensorNodes;

import java.util.Scanner;

import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class OpponentFrontBack extends Sensor{

	@Override
	public int evaluate(Robot robot) {
		return robot.getOpponentFB();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("oppFB", scan)){ Parser.fail("Should be oppFB node.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return "oppFB";
	}

}
