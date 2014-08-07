package ActionNodes;
import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.*;

public class Move extends Action{
	private Expression expr = null;

	@Override
	public void execute(Robot robot) {
		if (expr != null){
			for (int i =0; i < expr.evaluate(robot); i++){
				robot.move();
			}
		} else {
			robot.move();
		}
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("move", scan)){ Parser.fail("Should be Move node.", scan); }
		if (moveLength(scan));
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return this;
	}

	public boolean moveLength(Scanner scan){
		if (scan.hasNext("\\(")){
			if (!Parser.gobble("\\(", scan)){ Parser.fail("Move should have (.", scan); }
			expr =  new Expression();
			expr.parse(scan);
			if (!Parser.gobble("\\)", scan)){ Parser.fail("Move should have ).", scan); }
			return true;
		}
		return false;
	}


	@Override
	public String toString(){
		if (expr == null){
			return "move;";
		}
		return String.format("move(%s);",expr.toString());
	}
}
