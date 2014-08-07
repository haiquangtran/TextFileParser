package nodes;

import java.util.Scanner;

import SensorNodes.*;

import ConditionNodes.Equals;

import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class Sensor extends Expression{
	private RobotSensorNode node;

	@Override
	public int evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (scan.hasNext("barrelFB")){
			node = new BarrelFrontBack().parse(scan);
			return node;
		} else if (scan.hasNext("barrelLR")){ 
			node = new BarrelLeftRight().parse(scan);
			return node;
		} else if (scan.hasNext("fuelLeft")){
			node = new FuelLeft().parse(scan);
			return node;
		} else if (scan.hasNext("numBarrels")){ 
			node = new NumberOfBarrels().parse(scan);
			return node;
		} else if (scan.hasNext("oppFB")){ 
			node = new OpponentFrontBack().parse(scan);
			return node;
		} else if (scan.hasNext("oppLR")){ 
			node = new OpponentLeftRight().parse(scan);
			return node;
		} else if (scan.hasNext("wallDist")){ 
			node = new WallDistance().parse(scan);
			return node;
		}
		Parser.fail("Sensor node needs to be barrelFB, barrelLR, fuelLeft, numBarrels, oppFB, oppLR or wallDist.", scan);
		return null;
	}

	@Override
	public String toString(){
		return node.toString();
	}

}
