package nodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotSensorNode;

public class Num extends Expression{
	private int number;


	@Override
	public int evaluate(Robot robot) {
		return number;
	}


	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (scan.hasNext("-?[1-9][0-9]*|0")){  
			this.number = Integer.parseInt(scan.next());
		}
		else {
			Parser.fail("Number Node should have value -?[1-9][0-9]*|0.", scan);
		}
		return this;	//return number node
	}
	
	public RobotSensorNode numberNodeZero(){
		this.number = 0;
		return this;
	}
	
	public String toString(){
		return number+"";
	}

}
