package SensorNodes;

import java.util.Scanner;

import nodes.Expression;
import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class BarrelLeftRight extends Sensor{
	private Expression expr = null;

	@Override
	public int evaluate(Robot robot) {
		if (expr != null){
			return robot.getBarrelLR(expr.evaluate(robot));
		}
		return robot.getClosestBarrelLR();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("barrelLR", scan)){ Parser.fail("Should be barrelLR node.", scan); }
		if (barrelLength(scan));
		return this;
	}

	public boolean barrelLength(Scanner scan){
		if (scan.hasNext("\\(")){
			if (!Parser.gobble("\\(", scan)){ Parser.fail("barrelLR should have (.", scan); }
			expr =  new Expression();
			expr.parse(scan);
			if (!Parser.gobble("\\)", scan)){ Parser.fail("barrelLR should have ).", scan); }
			return true;
		}
		return false;
	}
	
	public String toString(){
		if (expr != null){
			return String.format("barrel(%s)",expr.toString());
		}
		return "barrelLR";
	}

}
