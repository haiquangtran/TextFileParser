package ConditionNodes;

import java.util.Scanner;

import nodes.Condition;

import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class And extends Condition{
	private Condition cond1 = new Condition();
	private Condition cond2 = new Condition();
	
	@Override
	public boolean evaluate(Robot robot) {
		if (cond1.evaluate(robot) && cond2.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (!Parser.gobble("and", scan)){ Parser.fail("Should have and.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after and.", scan);}
		cond1.parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("Should have , in and.", scan); }
		cond2.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) at the end of and.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("and (%s , %s)", cond1.toString(), cond2.toString());
	}
	
}
