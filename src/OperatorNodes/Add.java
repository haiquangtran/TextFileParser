package OperatorNodes;

import java.util.Scanner;

import nodes.Expression;

import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class Add extends Expression{
	private RobotSensorNode number1;
	private RobotSensorNode number2;
	
	@Override
	public int evaluate(Robot robot) {
		return (number1.evaluate(robot) + number2.evaluate(robot));
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("add", scan)){ Parser.fail("missing add.",scan);}
		if (!Parser.gobble("\\(", scan)){ Parser.fail("missing ( for add.",scan);}
		number1 = new Expression().parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("missing , for add.",scan);}
		number2 = new Expression().parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("missing ( for add.",scan);}
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("add(%s,%s)",number1.toString(), number2.toString());
	}

}
