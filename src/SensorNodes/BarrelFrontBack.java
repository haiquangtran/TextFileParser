package SensorNodes;

import java.util.Scanner;

import ActionNodes.Move;

import nodes.Expression;
import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class BarrelFrontBack extends Sensor{
	private Expression expr = null;
	
	@Override
	public int evaluate(Robot robot) {
		if (expr != null){
			return robot.getBarrelFB(expr.evaluate(robot));
		}
		return robot.getClosestBarrelFB();
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("barrelFB", scan)){ Parser.fail("Should be barrelFB node.", scan); }
		if (barrelLength(scan));
		return this;
	}
	
	public boolean barrelLength(Scanner scan){
		if (scan.hasNext("\\(")){
			if (!Parser.gobble("\\(", scan)){ Parser.fail("barrelFB should have (.", scan); }
			expr =  new Expression();
			expr.parse(scan);
			if (!Parser.gobble("\\)", scan)){ Parser.fail("barrelFB should have ).", scan); }
			return true;
		}
		return false;
	}
	
	public String toString(){
		if (expr != null){
			return String.format("barrelFb(%s)", expr.toString());
		}
		return "barrelFB";
	}

}
