package OperatorNodes;

import java.util.Scanner;

import nodes.Expression;

import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class Division extends Expression{
	private RobotSensorNode number1;
	private RobotSensorNode number2;
	
	@Override
	public int evaluate(Robot robot) {
		return (number1.evaluate(robot)/number2.evaluate(robot));
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!Parser.gobble("div", scan)){ Parser.fail("missing div.",scan);}
		if (!Parser.gobble("\\(", scan)){ Parser.fail("missing ( for div.",scan);}
		number1 = new Expression().parse(scan);
		if (!Parser.gobble("\\,", scan)){ Parser.fail("missing , for div.",scan);}
		number2 = new Expression().parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("missing ( for div.",scan);}
		return this;
	}
	
	@Override
	public String toString(){
		return String.format("div(%s,%s)",number1.toString(), number2.toString());
	}

}
