package zigzag;

import java.awt.Color;
import java.util.Scanner; 
import edu.cmu.ri.createlab.terk.robot.finch.Finch; 
//imports everything required for the finch
public class Finch_ZigZag { // declares the class 
	private static int cmPerSecond = 10; //finch moves 10 cm per second
	private static int timeForRightAngle = 1625; // finch turns 90 degrees at speed of 100 
	// this amount of time for the angle 
	private static int sectionNbs; // int number of sections numbers
	private static int sectionSize; // int length of the sections size
	private static int holdFinch; //  finch moving
	private static int tune = 1000; //tune 1
	private static Color color; //colour 

	static Finch finch = new Finch(); // this creates a  new finch object

	//calculate path
	public static double travelLengthStraight (){
		double travpath = sectionSize * Math.sqrt(2) * sectionNbs; //calculates path
		double distance = Math.round(travpath*100.0)/100.0; // the length travelled is shown to the second decimal point
		return distance; //returns the output for distance travelled 
	}
	//calculate path 
	public static double travelLengthZigZag (){
		double travpath = sectionNbs * sectionSize; // times section number by the size
		double distance = Math.round(travpath*100.0)/100.0; // the length is shown to the second decimal point
		return distance; //returns the output for distance travelled 
	}

	//this part allows the user to pick the length of the zigzag 
	private static void input() { //input 
		Scanner sc= new Scanner(System.in); // that enables us to write an input in the console
		boolean exita =false; 
		System.out.println("Length of the sections (input must be between 30cm and 80cm): ");
		while (exita == false) { // if boolean is false it means that the length was valid allows us to continue to next step
			int choose = sc.nextInt(); // so we can go to next message
			if(choose>=30 && choose<=80){ //choose between 30 and 80
				sectionSize = choose; 
				exita = true;  
			}
			else{
				System.out.println("Error Invalid Input Try Again"); // prints out an error message appears
			}
		}

		//this part of code is for the selecting how many sections the user wants
		boolean exitb =false;
		System.out.println("Number of ZigZag sections (Pick either 2 4 6 or 8)"); //pick number of sections
		while (exitb == false) {
			int choose = sc.nextInt();
			if(choose==2){ //2 section number 
				sectionNbs = choose;
				exitb = true;
			}
			else if(choose==4){ //4 section number 
				sectionNbs = choose;
				exitb = true;
			}
			else if(choose==6){ //6 section number 
				sectionNbs = choose;
				exitb = true;
			}
			else if(choose==8){ //8 section number 
				sectionNbs = choose;
				exitb = true;
			}
			else{
				System.out.println("Error Invalid Input Try Again"); //prints out error message 
			}
		}

		sc.close(); //closes the scanner 
	}



	private static void move(boolean direction) { // if the direction = true means that finch is going forward and any other direction
		if(direction)color = Color.GREEN; //set colour green
		else color = Color.RED; // sets colour red 
		holdFinch = (sectionSize  / cmPerSecond) * 1000;  // this is the time of going through one section of the zigzag


		//for loop  recreates the number of sections that the finch needs to go back
		finch.setLED(color); //set finch colour 
		finch.buzz(tune, holdFinch); // makes a noise while moving throughout the section
		finch.setWheelVelocities(100, 100, holdFinch); //speed is set to 100 holdFinch allows moving to complete a section


		//allows the change of tune and LED colour due to section of zigzag 
		for (int i = 0; i <sectionNbs - 1; i++) { //goes through the number of sections

			if(direction) {
				if(i % 2 == 0) { // every even number set 
					tune=2000; //tune 2 
					finch.setLED(Color.RED); // sets finch LED red colour
					finch.setWheelVelocities(0, 100, timeForRightAngle); // left wheel is 0 right wheel is 100 = turning left to 90 degrees
				} else { // every odd number of zigzags set
					tune=1000; //tune 1
					finch.setLED(Color.GREEN); // sets finch LED green colour
					finch.setWheelVelocities(100, 0, timeForRightAngle); // turning right 90 degrees 
				} 
			} else { // that's the way back so goes back to trace pattern
				if(i % 2 != 0) { // every odd number set  
					tune=2000; // tune 2 
					finch.setLED(Color.RED); // sets finch LED colour red 
					finch.setWheelVelocities(0, 100, timeForRightAngle); // left wheel is 0 and right wheel is 100 = turning left 90 degrees
				} else { // every even number set
					tune=1000; // tune 1
					finch.setLED(Color.GREEN); // sets finch LED colour green
					finch.setWheelVelocities(100, 0, timeForRightAngle);
				} 
			}

			finch.buzz(tune, holdFinch); // plays a tune while the finch moves 
			finch.setWheelVelocities(100, 100, holdFinch); //set speed of 100 and holdFinch = moving to complete a section
		}
	}
	public static void main(String args[]) { //main body
		input(); //input 
		move(true); //move if input true so valid 
		finch.setWheelVelocities(0, 100, timeForRightAngle * 2); // turning of 90 degrees *2 so it goes back to where it came from
		move(false);
		//calculate the path output
		System.out.println("Straight length of your travel pattern is : " + travelLengthStraight()); //outputs straight length of travel
		System.out.println("Length of your travel pattern is : " +travelLengthZigZag()); //outputs length of your travel  

		finch.quit(); //quit program
	}
}
