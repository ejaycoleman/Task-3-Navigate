package dance;

import java.util.Scanner;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class DanceMain {
	static Finch Fin = new Finch();
	static Scanner s = new Scanner(System.in);
	static ActualCode AC = new ActualCode();

	public static void start() 
	{
		while(true)
		{
			AC.Run(s.nextLine(), Fin);
		}
	}
}
