package nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class Assignment extends Statement{
	Variable var = new Variable();

	@Override
	public void execute(Robot robot) {
		// TODO Auto-generated method stub
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		var.parse(scan);
		return this;
	}

	@Override
	public String toString(){
		if (var.getExpression() == null){
			return var.toString()+";";
		} else if (var.getExpression() instanceof Variable && var.getName() != ""){
			return var.getExpression().toString() + ";";
		}
		return String.format(var.toString() + " = " + var.getExpression().toString() + ";");
	}
}
