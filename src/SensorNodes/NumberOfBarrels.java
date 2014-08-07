package SensorNodes;

import java.util.Scanner;

import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;


public class NumberOfBarrels extends Sensor{

	@Override
	public int evaluate(Robot robot) {
		return robot.numBarrels();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("numBarrels", scan)){ Parser.fail("Should be numBarrels node.", scan); }
		return this;
	}
	
	public String toString(){
		return "numBarrels";
	}
}
