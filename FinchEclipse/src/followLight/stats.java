package followLight;

public class stats extends followLight { //this makes the stats class inherit from the main class

	
	static long timeTravelled; //we will use this to calculate the total time travelled.
	static int timeStopped= 0; // we want this and average speed to be reset to zero each time the program runs,so that we get accurate statistics 
	static int avgSpeed = 0; //we want this to reset to zero each time the software runs.
	
	
	public static void main (String args[])
		{
		int i = 0; // this variable will act as a placeholder 
		for (i=0; i < speedList.size(); i++ ); 
		{ 
			//avgSpeed += speedList.get(i); // this adds up all the speeds in the array list
		}
		try
		{
			avgSpeed /= (speedList.size()-1); // this divides the total of the average speed, by the number of entries
		}
		catch (ArithmeticException avgSpeedEquals0) //if the average speed is  equal to zero, instead of throwing an exception, it will print out "average speed = 0" into the console
		{
			avgSpeed = 0;
		}
		for (int j = 0;
				j < stopTimeArray.size(); 
				j++)
		{
			timeStopped += stopTimeArray.get(j);
		}
		timeTravelled = ((endTime - startTime)/1000)-timeStopped; // we divide by a thousand to convert it from milliseconds to seconds
		
	}
	
}
