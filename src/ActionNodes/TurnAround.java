package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class TurnAround extends Action{

	@Override
	public void execute(Robot robot) {
		robot.turnAround();
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("turnAround", scan)){ Parser.fail("Should be turnAround node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new TurnAround();
	}
	
	@Override
	public String toString(){
		return "turnAround;";
	}

}
