package nodes;

import java.util.ArrayList;
import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotConditionNode;
import main.RobotProgramNode;

public class If extends Statement{
	private Condition condition = new Condition();
	private Block block = new Block();
	private Block elseBlock = null;
	private ArrayList<Elseif> elseifStatements = new ArrayList<Elseif>();

	@Override
	public void execute(Robot robot) {
		if (condition.evaluate(robot)){
			block.execute(robot);
		} else if (!elseifStatements.isEmpty()){	//executes ONE of the elseif statements if condition is true
			for (Elseif elsesif: elseifStatements){
				if (elsesif.evaluate(robot)){		
					elsesif.execute(robot);
					break;
				} else if (elsesif.getElseBlock(robot) != null){	//Else statement in Elseif Class 
					elsesif.getElseBlock(robot).execute(robot);
					break;
				}
			}
		} else if (elseBlock!=null){				//if can have an else as well
			elseBlock.execute(robot);
		}
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("if", scan)){ Parser.fail("Should have if node.", scan); }
		if (!Parser.gobble("\\(", scan)){ Parser.fail("Should have ( after if.", scan);}
		condition.parse(scan);
		if (!Parser.gobble("\\)", scan)){ Parser.fail("Should have ) in if.", scan);}
		block.parse(scan);
		if (elseMethod(scan));						//if statements can have optional else 
		while(elifMethod(scan));					//Elseif class contains a else statement inside, so no need to worry about it.
		return this;
	}

	public boolean elifMethod(Scanner scan){
		if (scan.hasNext("elif")){
			Elseif elseifNode = new Elseif();
			elseifNode.parse(scan);
			elseifStatements.add(elseifNode);
			return true;
		}
		return false;
	}

	public boolean elseMethod(Scanner scan){
		if (scan.hasNext("else")){
			if (!Parser.gobble("else", scan)){ Parser.fail("Should have else node.", scan); }
			elseBlock =  new Block();
			elseBlock.parse(scan);
			if (Parser.gobble("elif", scan)){ Parser.fail("You cannot have a elif statement after an else statement.", scan); } 
			if (Parser.gobble("else", scan)){ Parser.fail("You cannot have a else statement after an else statement.", scan); }
			return true;
		}		
		return false;
	}


	@Override
	public String toString(){
		if (elseBlock != null){
			return "\nif ("+condition.toString() + ") " + block.toString() + "\nelse " + elseBlock.toString() + "\n";
		} else if (!elseifStatements.isEmpty()){
			String total = "\n";
			for (Elseif elsesif: elseifStatements){
				total += elsesif.toString();
			}

			return "\nif ("+condition.toString() + ") " + block.toString() + total ;
		}
		return "\nif ("+condition.toString() + ") " + block.toString() + "\n";
	}

}
