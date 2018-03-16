package followLight;
import java.util.Scanner;
public class ui extends followLight {
	
	public static void main(String args[])
	{
		viewStats();
	}
	
	public static void viewStats()
	{
		String viewStats; // initialises the viewStats variables
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to see some statistics of your finches journey? [Type yes or no]");
		viewStats = input.next();
		if (viewStats.equals("yes") || viewStats.equals("y e s") || viewStats.equals("yes"))
		{
			System.out.println("Total travel time " +stats.timeTravelled+" seconds");
			System.out.println("Number of stops "+stopNum);
			System.out.println("Average speed = " +stats.avgSpeed );
			System.out.println("Stop time = "+stats.stopTimer+ " seconds");
			input.close(); // closes the scanner so the user can not input anything else
			myFinch.quit(); // this terminates the finch, stopping it from doing anything else
			
		}
		else
		{
			System.out.println("shutting down");
			input.close();	
			myFinch.quit();
		}
		
	}


}
