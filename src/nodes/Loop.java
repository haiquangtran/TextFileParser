package nodes;

import java.util.Scanner;

import ActionNodes.TurnLeft;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class Loop extends Statement{
	private Block block;

	public Loop(Block block) {	//takes in a block node
		this.block = block;
	}

	@Override
	public void execute(Robot robot) {
		block.execute(robot);
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("loop", scan)){ Parser.fail("Should be loop node.", scan);} 
		//Add statements to the block
		block.parse(scan);
		return new Loop(block);
	}

	@Override
	public String toString(){
		return "loop "+ block.toString() + "\n";
	}

}
