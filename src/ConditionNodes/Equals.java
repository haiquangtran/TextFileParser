package ConditionNodes;

import java.util.Scanner;

import nodes.Condition;
import nodes.Expression;
import nodes.Num;
import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class Equals extends Condition{
	private Expression expr1 = new Expression();
	private Expression expr2 = new Expression();
	
	@Override
	public boolean evaluate(Robot robot) {
		if (expr1.evaluate(robot) == expr2.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (!Parser.gobble("eq", scan)){ Parser.fail("Should have eq.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after eq.", scan);}
		expr1.parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("Should have , in eq.", scan); }
		expr2.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) at the end of eq.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("eq (%s , %s)", expr1.toString(), expr2.toString());
	}

}
