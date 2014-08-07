package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class TurnRight extends Action{

	@Override
	public void execute(Robot robot) {		//turn right
		robot.turnRight();
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("turnR", scan)){ Parser.fail("Should be turnR node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new TurnRight();
	}
	
	@Override
	public String toString(){
		return "turnR;";
	}

}
