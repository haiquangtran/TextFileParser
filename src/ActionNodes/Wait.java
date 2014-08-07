package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;
import nodes.Expression;

public class Wait extends Action{
	private Expression expr = null;
	
	@Override
	public void execute(Robot robot) {		//Wait
		if (expr != null){
			for (int i =0; i < expr.evaluate(robot); i++){
				robot.idleWait();
			}
		} else {
			robot.idleWait();
		}
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("wait", scan)){ Parser.fail("Should be wait node.", scan); }
		if (waitLength(scan));
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return this;
	}

	public boolean waitLength(Scanner scan){
		if (scan.hasNext("\\(")){
			if (!Parser.gobble("\\(", scan)){ Parser.fail("Wait should have (.", scan); }
			expr =  new Expression();
			expr.parse(scan);
			if (!Parser.gobble("\\)", scan)){ Parser.fail("Wait should have ).", scan); }
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		if (expr == null){
			return "wait;";
		}
		return String.format("wait(%s);",expr.toString());
	}
}
