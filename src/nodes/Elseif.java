package nodes;
import java.util.Scanner;

import main.*;

public class Elseif implements RobotProgramNode{
	private Condition condition = new Condition();
	private Block block = new Block();
	private Block elseBlock = null;

	@Override
	public void execute(Robot robot) {
		block.execute(robot);
		if (elseBlock != null){
			elseBlock.execute(robot);
		}
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("elif", scan)){ Parser.fail("should have elif node.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after elif.", scan);}
		condition.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) in elif.", scan);}
		block.parse(scan);
		if (elseMethod(scan));	//else 
		return this;
	}

	public boolean elseMethod(Scanner scan){
		if (scan.hasNext("else")){
			if (!Parser.gobble("else", scan)){ Parser.fail("Should have else node.", scan); }
			elseBlock =  new Block();	//set elseblock
			elseBlock.parse(scan);
			if (Parser.gobble("elif", scan)){ Parser.fail("You cannot have a elif statement after an else statement.", scan); } 
			if (Parser.gobble("else", scan)){ Parser.fail("You cannot have a else statement after an else statement.", scan); } 
			return true;
		}
		return false;
	}

	public boolean evaluate(Robot robot){
		return condition.evaluate(robot);
	}
	
	public Block getElseBlock(Robot robot){
		return elseBlock;
	}
	
	@Override
	public String toString(){
		if (elseBlock != null){
			return String.format("elif(%s)%s\nelse%s\n",condition.toString(), block.toString(), elseBlock.toString());
		}
		return String.format("elif(%s)%s\n",condition.toString(), block.toString());
	}

}
