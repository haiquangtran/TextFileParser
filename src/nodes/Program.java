package nodes;

import java.util.ArrayList;
import java.util.Scanner;

import ActionNodes.*;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;


public class Program implements RobotProgramNode{
	private ArrayList<Statement> statements;

	@Override
	public void execute(Robot robot) {
		for (Statement cmds: statements){	//execute all commands.
			cmds.execute(robot);
		}
	}


	@Override
	public RobotProgramNode parse(Scanner scan) {
		statements = new ArrayList<Statement>();

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
			} else if (scan.hasNext("loop")){
				RobotProgramNode node = new Loop(new Block()).parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("if")){
				RobotProgramNode node = new If().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("while")){
				RobotProgramNode node = new While().parse(scan);
				statements.add((Statement) node);
			} else if (scan.hasNext("\\$[A-Za-z][A-Za-z0-9]*")){
				RobotProgramNode node = new Assignment().parse(scan);
				statements.add((Statement) node);
			} 
		}
	
		return this;
	}
	
	public String toString(){
		String total = "";
		for (Statement cmds: statements){	//execute all commands.
			total += " "+cmds.toString();
		}
		return total;
	}

}
