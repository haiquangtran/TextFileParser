package nodes;

import java.util.ArrayList;
import java.util.Scanner;

import ActionNodes.Move;
import ActionNodes.ShieldOff;
import ActionNodes.ShieldOn;
import ActionNodes.TakeFuel;
import ActionNodes.TurnAround;
import ActionNodes.TurnLeft;
import ActionNodes.TurnRight;
import ActionNodes.Wait;

import main.Parser;
import main.ParserFailureException;
import main.Robot;
import main.RobotProgramNode;

public class Block implements RobotProgramNode{
	ArrayList<Statement> statements = new ArrayList<Statement>();

	@Override
	public void execute(Robot robot) {
		for (Statement cmds: statements){
			cmds.execute(robot);
		}
	}

	@Override
	public RobotProgramNode parse(Scanner scan) {
		if (!Parser.gobble("\\{", scan)){ Parser.fail("should have { for block node.", scan); }
		while(scan.hasNext()){
			if (scan.hasNext("move")){
				RobotProgramNode node = new Move().parse(scan);
				statements.add((Statement)node);
			} else if (scan.hasNext("takeFuel")){
				RobotProgramNode node = new TakeFuel().parse(scan);
				statements.add((Statement) node );
			} else if (scan.hasNext("turnL")){
				RobotProgramNode node = new TurnLeft().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("turnR")){
				RobotProgramNode node = new TurnRight().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("wait")){
				RobotProgramNode node = new Wait().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("shieldOff")){
				RobotProgramNode node = new ShieldOff().parse(scan);
				statements.add((Statement) node );
			} else if (scan.hasNext("shieldOn")){
				RobotProgramNode node = new ShieldOn().parse(scan);
				statements.add((Statement) node );
			} else if (scan.hasNext("turnAround")){
				RobotProgramNode node = new TurnAround().parse(scan);
				statements.add((Statement) node );
			} else if (scan.hasNext("while")){
				RobotProgramNode node = new While().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("if")){
				RobotProgramNode node = new If().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
				RobotProgramNode node = new Assignment().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("\\}")){
				scan.next();
				//Close the Loop 
				break;
			}
		}
		if (statements.isEmpty()){		//has to have 1 or more elements in it
			throw new ParserFailureException("Should have 1 or more statements in a block.");
		}
		return this;
	}

	@Override
	public String toString(){
		String total = "{";
		for (Statement cmds: statements){
			total += " " + cmds.toString();
		}
		total += " }";
		return total;
	}
}
