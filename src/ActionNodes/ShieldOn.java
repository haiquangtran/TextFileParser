package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class ShieldOn extends Action{
	
	@Override
	public void execute(Robot robot) {
		robot.setShield(true);
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("shieldOn", scan)){ Parser.fail("Should be shieldOn node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new ShieldOn();
	}
	
	@Override
	public String toString(){
		return "shieldOn;";
	}
}
