package ConditionNodes;

import java.util.Scanner;

import nodes.Condition;
import nodes.Expression;
import nodes.Num;
import nodes.Sensor;
import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class GreaterThan extends Condition{
	private Expression expr1 = new Expression();
	private Expression expr2 = new Expression();

	@Override
	public boolean evaluate(Robot robot) {
		if (expr1.evaluate(robot) > expr2.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (!Parser.gobble("gt", scan)){ Parser.fail("Should have gt.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after gt.", scan);}
		expr1.parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("Should have , in gt.", scan); }
		expr2.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) at the end of gt.", scan); }
		return this;
	}

	@Override
	public String toString(){
		return String.format("gt (%s , %s)", expr1.toString(), expr2.toString());
	}

}
