package ConditionNodes;

import java.util.Scanner;

import nodes.Condition;

import main.Parser;
import main.Robot;
import main.RobotConditionNode;

public class Not extends Condition{
	private Condition cond1 = new Condition();
	
	@Override
	public boolean evaluate(Robot robot) {
		if (!cond1.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner scan) {
		if (!Parser.gobble("not", scan)){ Parser.fail("Should have not.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after not.", scan);}
		cond1.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) at the end of not.", scan); }
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("not (%s)", cond1.toString());
	}
	
}
