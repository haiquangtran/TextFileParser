package ActionNodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import nodes.Action;

public class TakeFuel extends Action{

	@Override
	public void execute(Robot robot) {	//take fuel
		robot.takeFuel();
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("takeFuel", scan)){ Parser.fail("Should be takeFuel node.", scan); }
		if (!Parser.gobble("\\;", scan)){ Parser.fail("Should have ; for variables.", scan); }
		return new TakeFuel();
	}

	@Override
	public String toString(){
		return "takeFuel;";
	}
	
}
