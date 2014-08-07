package SensorNodes;

import java.util.Scanner;

import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class OpponentLeftRight extends Sensor{

	@Override
	public int evaluate(Robot robot) {
		return robot.getOpponentLR();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("oppLR", scan)){ Parser.fail("Should be oppLR node.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return "oppLR";
	}
}
