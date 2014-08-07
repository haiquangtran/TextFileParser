package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class TurnLeft extends Action{


	@Override
	public void execute(Robot robot) {
		robot.turnLeft();
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("turnL", scan)){ Parser.fail("Should be turnL node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new TurnLeft();
	}

	@Override
	public String toString(){
		return "turnL;";
	}
}
