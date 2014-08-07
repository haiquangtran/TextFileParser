package nodes;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotConditionNode;
import main.RobotProgramNode;

public class While extends Statement{
	private Condition condition = new Condition();
	private Block block = new Block();
	
	@Override
	public void execute(Robot robot) {
		if (condition.evaluate(robot)){
			block.execute(robot);
		}	
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("while", scan)){ Parser.fail("Should have while node.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after while.", scan);}
		this.condition.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) in while.", scan);}
		this.block.parse(scan);
		return this;
	}
	
	@Override
	public String toString(){
		return "while ("+condition.toString() + ") " + block.toString() + "\n";
	}

}
