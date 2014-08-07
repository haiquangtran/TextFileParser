package nodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotSensorNode;

public class Variable extends Expression{
	private String name = "";
	private Expression expr = null;

	@Override
	public int evaluate(Robot robot) {
		if (expr != null){ return expr.evaluate(robot); }
		return 0;	//Need because move and wait start at 0 (the loops)
	}

	@Override
	public RobotSensorNode parse(Scanner scan) {
		if (!scan.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){ Parser.fail("Variables have to be \\$[A-Za-z][A-Za-z0-9]*.", scan); }
		name = scan.next("\\$[A-Za-z][A-Za-z0-9]*");
		//Already in map
		if (Parser.variables.containsKey(name)){
			expr = Parser.variables.get(name);
			//Override
			if (assignVariable(scan));
		} else {	//New map entry
			assignVariable(scan);
		}
		return this;
	}

	/**
	 * Makes new variable and adds it to the map
	 * 
	 * @param scan
	 * @return true or false, true - if assignment, false - if calling the variable
	 */
	public boolean assignVariable(Scanner scan){
		if (scan.hasNext("\\=")){
			if (!Parser.gobble("\\=", scan)){ Parser.fail("Should have = for variables.", scan); }
			expr = new Expression();
			expr.parse(scan);						//Assignment
			if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
			//Add to map
			Parser.variables.put(name, this);
			return true;
		} else if (scan.hasNext("\\;")){	//DECLARED NOT ASSIGNMENT
			if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
			/*			Num number = new Num();
			number.numberNodeZero();
			expr = number;					//sets expr to 0. 
			 */			return true; 					//Shouldnt set it to 0 if there is already variable in map
		}
		return false;
	}

	@Override
	public String toString(){
		return String.format("%s", name.toString());
	}

	public Expression getExpression(){
		return expr;
	}

	public String getName(){
		return name;
	}
}
