package followLight;
import java.awt.Color;
import java.util.ArrayList;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class followLight {
public static Finch myFinch = new Finch(); // here we are initialising the finch
static int stopNum = 0; // calculates the number of stops, gets set to 0 each time the program runs
static ArrayList<Integer> speedList=new ArrayList<Integer>(); // this is initialising the array list where the speeds of the finch will be stored
static long startTime = 0; // this is the time that the finch starts moving, we will use it to calculate the total running time and various other statistics once the program terminates
static long endTime = 0; //this is the time at which the program ends, we will minus the end time from the start time to get the total running time, to get an accurate number we will use the system time, this will save us from having to create a timer using loops
static ArrayList <Double> stopTimeArray = new ArrayList<Double>(); // this array list will used to calculate the ammount of time that hte finch has been stopped for
static long stopTimer; // used to time how long the finch has been stopped for
static boolean endGame = false; // will be used to  determine when to run the end game method.
static String showStats; //will be used to show the user the finches statistics
static boolean obstacleCheck = myFinch.isObstacle();
static int ambiantLight = 80; // this is the light sensor threshold
//static boolean msgGiven = false; //makes sure that nothing gets printed if it doesnt need to be,
	

	
public static void main (String args[])
{
	System.out.println("To begin, shine a light infront of the finch");
	myFinch.sleep(5000); //makes the finch sleep for 5 seconds so that the user has time to shine a light infront of it
	//System.out.println(myFinc.getLeftLightSensor()+myFinch.getRightLightSensor()); 
	startTime = System.currentTimeMillis();
	while (!endGame)
	{
		if (myFinch.getLeftLightSensor() >= ambiantLight || myFinch.getRightLightSensor() >= ambiantLight ) // this is checking if either one of the light sensors can detect light, if one of them does then it begins the movement method		
		{
			
			System.out.println(myFinch.getLeftLightSensor()+myFinch.getRightLightSensor());  // for diagnostic purposes
			movement();
			//msgGiven = false;
		}
		//obstacle / no light- this is checking for an obstacle or lack of light in either light sensor, need to just change this to obstacle, and do light elsewhere 
		if (myFinch.getRightLightSensor()<ambiantLight && myFinch.getLeftLightSensor()<ambiantLight)
			
		{
			// timer goes here
			myFinch.setWheelVelocities(0, 0); //stops movement
			setLEDStop(); //LED = red

		}
		isObstacle(); //runs the method which checks for an obstacle
	}//the end of the while loop
	
	if (endGame) //if the endGame method is running
	{
		//yendTime = System.currentTimeMillis(); // this records the time that the program ends
		endTime = System.currentTimeMillis();
		stats.main(args);
		ui.main(args);
	}
}// end of main method, all other methods for various bits and peices will be below or in seperate classess.
	
	public static void setLEDStop() // Beak LED = red
	{
		myFinch.setLED(Color.red);
		//if(!msgGiven)
		{
		System.out.println("Light lost or object detected, program will terminate in "+stopTimer+"  seconds.");
		//msgGiven = true;
		}
		stopNum += 1; // increases stopNum by 1, i placed it here because the finch will stop when the light is red,
		stopTimer();
	}
	
	public static void isObstacle()
	{
		if (myFinch.isObstacle()==true )
		{
			myFinch.setWheelVelocities(0, 0); // stops movement
			setLEDStop();// LED = red
			//System.out.println("Obstacle detected, please remove the obstruction or reposition the finch within "); +(6 - stopTimeArray.get(i)) " seconds");
			//endGame=true;
			//msgGiven = false;
			//stopTimer();
			
		}
	}
	
	private static void stopTimer() {
		// this is going to time how long the finch has been stopped for
		int i = stopNum - 1;
		stopTimeArray.add(i,(double) 0);
		while((myFinch.getRightLightSensor()<ambiantLight && myFinch.getLeftLightSensor()<ambiantLight) | (myFinch.isObstacle()))
		{
			//stopNum+=1; // adds one to stopnum, 
			myFinch.sleep(1000); // this counts for one second
			stopTimeArray.set(i,stopTimeArray.get(i)+1); // this adds one to the array list each time it runs through
			System.out.println("Light lost or object detected, program will terminate in "+(6 - stopTimeArray.get(i))+"  seconds.");
			if(stopTimeArray.get(i)>=6){
				System.out.println("program ending, ");
				
				endGame=true;
	
				break;
			}
			
		}
		
		
		
		
	}

	public static void setLEDStart() // Beak LED = green
	{
		myFinch.setLED(Color.green); // this sets the finches LEd to green
	}
	
	public static void buzz() // buzzing sound, may change later, could have alternating tones/freqs to make it less irratating.
	{
	myFinch.buzz(200, 5000); // may need to alter 	
	}
	
	
	
public static void movement() // this is the movement method
{
	
	//Reading right sensor
	if (myFinch.getRightLightSensor() > myFinch.getLeftLightSensor())
	{
		
		setLEDStart(); // switches the finches beak LED to Green.
		buzz(); // plays buzzing tone, 
		Right();
		System.out.println("Moving Right-Following Light");
	}
	//Reading left sensor
	if (myFinch.getRightLightSensor() < myFinch.getLeftLightSensor())
	{
		setLEDStart(); // switches the finches beak LED to Green.
		buzz(); // plays buzzing tone, 
		Left();
		System.out.println("Moving Left-Following Light");
	}
	//Reading both sensors
	if (myFinch.getRightLightSensor()>ambiantLight && myFinch.getLeftLightSensor()>ambiantLight)
	{
		setLEDStart(); // switches the finches beak LED to Green.
		buzz(); // plays buzzing tone, 
		Forwards();
		System.out.println("Moving forward-Following Light");
	}
}
	


	
public static void Forwards()
{
	myFinch.setWheelVelocities(150, 150); // turns on both motors to the same speed to make the finch go straight
	speedList.add(150); // adds the speed (150) to the array list, this will be used to calculate the average speed
}

public static void Right()
{
	myFinch.setWheelVelocities(150, 0); //turns on the left motor to make the finch turn right
	speedList.add(150);
}
	
public static void Left()
{
	myFinch.setWheelVelocities(0, 150); // turns on the right motor to make the finch turn left
	speedList.add(150);
}	
	
public static void obstacleChecker(){ // checks for obstacles
	obstacleCheck=true;
}
	
	
}
