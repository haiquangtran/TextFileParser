package ConditionNodes;

import java.util.Scanner;

import nodes.Condition;
import nodes.Expression;
import nodes.Num;
import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class LessThan extends Condition{
	private Expression expr1 = new Expression();
	private Expression expr2 = new Expression();

	@Override
	public boolean evaluate(Robot robot) {
		if (expr1.evaluate(robot) < expr2.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (!Parser.gobble("lt", scan)){ Parser.fail("Should have lt.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after lt.", scan);}
		expr1.parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("Should have , in lt.", scan); }
		expr2.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) at the end of lt.", scan); }
		return this;
	}

	@Override
	public String toString(){
		return String.format("lt (%s , %s)", expr1.toString(), expr2.toString());
	}
}
