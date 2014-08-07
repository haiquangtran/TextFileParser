package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class ShieldOff extends Action{

	@Override
	public void execute(Robot robot) {
		robot.setShield(false);
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("shieldOff", scan)){ Parser.fail("Should be shieldOff node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new ShieldOff();
	}
	
	@Override
	public String toString(){
		return "shieldOff;";
	}

}
