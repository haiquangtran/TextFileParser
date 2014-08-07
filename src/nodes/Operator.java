package nodes;

import java.util.Scanner;

import OperatorNodes.*;
import SensorNodes.OpponentFrontBack;
import SensorNodes.OpponentLeftRight;
import SensorNodes.WallDistance;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotSensorNode;

public class Operator extends Expression{
	private RobotSensorNode node;

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (scan.hasNext("add")){ 
			node = new Add().parse(scan);
			return node;
		} else if (scan.hasNext("sub")){ 
			node = new Subtraction().parse(scan);
			return node;
		} else if (scan.hasNext("div")){ 
			node = new Division().parse(scan);
			return node;
		} else if (scan.hasNext("mul")){ 
			node = new Multiply().parse(scan);
			return node;
		}
		Parser.fail("Operator node needs to be add, sub, div, mul.", scan);
		return null;
	}
	
}
