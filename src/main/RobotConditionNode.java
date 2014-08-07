package main;

import java.util.Scanner;

public interface RobotConditionNode {
	public boolean evaluate(Robot robot);
	public RobotConditionNode parse(Scanner scan);
}
