package SensorNodes;

import java.util.Scanner;

import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class FuelLeft extends Sensor{

	@Override
	public int evaluate(Robot robot) {
		return robot.getFuel();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("fuelLeft", scan)){ Parser.fail("Should be fuelLeft node.", scan); }
		return this;
	}
	
	public String toString(){
		return "fuelLeft";
	}

}
