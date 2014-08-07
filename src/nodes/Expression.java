package nodes;

import java.util.Scanner;

import SensorNodes.BarrelFrontBack;
import SensorNodes.BarrelLeftRight;
import SensorNodes.FuelLeft;
import SensorNodes.NumberOfBarrels;
import SensorNodes.OpponentFrontBack;
import SensorNodes.OpponentLeftRight;
import SensorNodes.WallDistance;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotSensorNode;

public class Expression implements RobotSensorNode{
	private RobotSensorNode node;
	
	@Override
	public int evaluate(Robot robot) {
		return node.evaluate(robot);
	}
	
	@Override
	public RobotSensorNode parse(Scanner scan) {
		//OPERATOR
		if (scan.hasNext("add") || scan.hasNext("mul") || scan.hasNext("div") || scan.hasNext("sub")){ 
			node = new Operator().parse(scan);
			return node;
		} 
		//SENSOR
		else if (scan.hasNext("barrelFB") || scan.hasNext("barrelLR") || scan.hasNext("fuelLeft") 
				|| scan.hasNext("numBarrels") || scan.hasNext("oppFB") || scan.hasNext("oppLR")
				|| scan.hasNext("wallDist")){
			node = new Sensor().parse(scan);
			return node;
		}  
		//NUMBER
		else if (scan.hasNext("-?[1-9][0-9]*|0")){
			node = new Num().parse(scan);
			return node;
		}
		//VARIABLE
		else if (scan.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
			node = new Variable().parse(scan);
			return node;
		}
		Parser.fail("Expression node needs to be Operator, Sensor or number.", scan);
		return null;
	}
	
	
	@Override
	public String toString(){
		return node.toString();
	}

}
