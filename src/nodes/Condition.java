package nodes;

import java.util.Scanner;

import ConditionNodes.*;

import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class Condition implements RobotConditionNode{
	private RobotConditionNode node;

	@Override
	public boolean evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (scan.hasNext("eq")){
			node = new Equals().parse(scan);
			return node;
		} else if (scan.hasNext("lt")){ 
			node = new LessThan().parse(scan);
			return node;
		} else if (scan.hasNext("gt")){
			node = new GreaterThan().parse(scan);
			return node;
		} else if (scan.hasNext("and")){
			node = new And().parse(scan);
			return node;
		} else if (scan.hasNext("or")){
			node = new Or().parse(scan);
			return node;
		} else if (scan.hasNext("not")){
			node = new Not().parse(scan);
			return node;
		}
		Parser.fail("Condition node needs to be eq, lt, rt, and, or, not.", scan);
		return null;
	}

	@Override 
	public String toString(){
		return node.toString();
	}

}
