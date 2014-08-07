package OperatorNodes;

import java.util.Scanner;

import nodes.Expression;

import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class Subtraction extends Expression{
	private RobotSensorNode number1;
	private RobotSensorNode number2;
	
	@Override
	public int evaluate(Robot robot) {
		return (number1.evaluate(robot) - number2.evaluate(robot));
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("sub", scan)){ Parser.fail("missing sub.",scan);}
		if (!Parser.gobble("\\(", scan)){ Parser.fail("missing ( for sub.",scan);}
		number1 = new Expression().parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("missing , for sub.",scan);}
		number2 = new Expression().parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("missing ( for sub.",scan);}
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("sub(%s,%s)",number1.toString(), number2.toString());
	}

}
