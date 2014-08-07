package main;

import java.util.Scanner;

public interface RobotSensorNode {
	public int evaluate(Robot robot);
	public RobotSensorNode parse(Scanner scan);
}
