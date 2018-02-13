package navigate;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Controller {
	public Controller() {
		this.setUpFinch();
		this.finchInstance.setLED(0, 255, 0);
	}
	
	
	private Finch finchInstance;
	
	private void setUpFinch() {
		this.finchInstance = new Finch();
	}
	
	public void start() {
		
		String inputString;
		inputString = "";
		
		int speed;
		int time;
		int quantity;
		ArrayList<String> stack = new ArrayList<String>();
		
		
		while(inputString != "Q") {
			inputString = getUserInput();
			
			// TODO check input gonna be valid
			
			// TODO multiply time input by 1000 so its in milliseconds 
			
			
			if (inputString == null) {
				//this.quit();
				break;
			}
			
			
			System.out.println(inputString);
			String[] inputArray = inputString.split(" ",-1); 
			
			
			System.out.println("Enter command as command, time, speed");
			
			System.out.println("the stack: "+stack);
			
			String instructionOption = inputArray[0];
			int[] testedParameters;
			
//			if (instructionOption == "F") {
//				
//			}
			

	        switch (instructionOption) {
	            case "F":  
	            	
	            		
	            	
//	            		speed = Integer.parseInt(inputArray[1]);
//	            		time = Integer.parseInt(inputArray[2]);
	            	
	            		testedParameters = testParameters(inputArray, 3);

	            		if (testedParameters != null) {
	            			if (checkInRange(testedParameters[0], testedParameters[1])) {
			        			stack.add(inputString);
			        			forward(testedParameters[0], testedParameters[1]);
			            		break;
			        		} else {
			        			break;
			        		}
	            		} else {
	            			infoBox("The forward command requires two parameters only", "Unexpected Input");
	            			break;
	            		}
	                
	            		
	            		
	            case "B":
//		            	speed = Integer.parseInt(inputArray[1]);
//		        		time = Integer.parseInt(inputArray[2]);
//	            		if (checkInRange(speed, time)) {
//		        			stack.add(inputString);
//		        			backward(speed, time);
//		            		break;
//		        		} else {
//		        			break;
//		        		}
		            	testedParameters = testParameters(inputArray, 3);
	
	            		if (testedParameters != null) {
	            			if (checkInRange(testedParameters[0], testedParameters[1])) {
			        			stack.add(inputString);
			        			backward(testedParameters[0], testedParameters[1]);
			            		break;
			        		} else {
			        			break;
			        		}
	            		} else {
	            			infoBox("The back command requires two parameters only", "Unexpected Input");
	            			break;
	            		}
	            case "R":
//		            	speed = Integer.parseInt(inputArray[1]);
//		        		time = Integer.parseInt(inputArray[2]);
//	            		
//	            		if (checkInRange(speed, time)) {
//		        			stack.add(inputString);
//		            		rightTurn(speed, time);
//		            		break;
//		        		} else {
//		        			break;
//		        		}
	            	
		            	testedParameters = testParameters(inputArray, 3);
		            	
	            		if (testedParameters != null) {
	            			if (checkInRange(testedParameters[0], testedParameters[1])) {
			        			stack.add(inputString);
			        			rightTurn(testedParameters[0], testedParameters[1]);
			            		break;
			        		} else {
			        			break;
			        		}
	            		} else {
	            			infoBox("The right command requires two parameters only", "Unexpected Input");
	            			break;
	            		}
	            		
	            case "L":
	            		
//		            	speed = Integer.parseInt(inputArray[1]);
//		        		time = Integer.parseInt(inputArray[2]);
//		        		if (checkInRange(speed, time)) {
//		        			stack.add(inputString);
//		            		leftTurn(speed, time);
//		            		break;
//		        		} else {
//		        			break;
//		        		}
	            	
		            	testedParameters = testParameters(inputArray, 3);
		            	
	            		if (testedParameters != null) {
	            			if (checkInRange(testedParameters[0], testedParameters[1])) {
			        			stack.add(inputString);
			        			leftTurn(testedParameters[0], testedParameters[1]);
			            		break;
			        		} else {
			        			break;
			        		}
	            		} else {
	            			infoBox("The left command requires two parameters only", "Unexpected Input");
	            			break;
	            		}
	            		
	            case "T":
	            	
		            	testedParameters = testParameters(inputArray, 2);
		            	
	            		if (testedParameters != null) {

		            		if (testedParameters[1] > stack.size()) {
		            			infoBox("Stack cannot exceed: "+ stack.size(), "Stack Overflow!");
		            			break;
		            		} else {
				            	reTraceMovements(testedParameters[1], stack);
				            	break;
		            		}
	            		} else {
	            			infoBox("The trace command requires one parameter only", "Unexpected Input");
	            			break;
	            		}
	            		
	            case "Q":
	            		quit();
	            		break;
	            default: 
	            		System.out.println("unknown input!");
	                break;
	        }
	        
		}
		
		// Always end your program with finch.quit()
		quit();
	}
	
	private int[] testParameters(String[] inputArray, int expected) {
		int speed;
		int time;
		if (inputArray.length == expected) {
			
			try {
				speed = Integer.parseInt(inputArray[1]);
        			time = Integer.parseInt(inputArray[2]);
			} catch (NumberFormatException e) {
				return null;
			}
			int[] returnArray = {speed, time};
			return returnArray;
		} else {
			return null;
		}
	}
	
	private boolean checkInRange(int time, int speed) {
		if (speed > 200 || speed < 0) {
			infoBox("Speed must be from 0 to 200", "Out Of Range");
			System.out.println(speed);
			return false;
		} else if (time > 6 || time < 0) {
			infoBox("Time must be from 0 to 6 seconds", "Out Of Range");
			return false;
		} else {
			return true;
		}
	}
	
	private void forward(int time, int speed) {
		finchInstance.setWheelVelocities(speed, speed, time*1000);
	}
	
	private void backward(int time, int speed) {
		finchInstance.setWheelVelocities(-speed, -speed, time*1000);
	}
	
	private void rightTurn(int time, int speed) {
		finchInstance.setWheelVelocities(150, 0, 1000);
		finchInstance.setWheelVelocities(speed, speed, time*1000);
	}
	
	private void leftTurn(int time, int speed) {
		finchInstance.setWheelVelocities(0, 150, 1000);
		finchInstance.setWheelVelocities(speed, speed, time*1000);
	}
	
	private void reTraceMovements(int quantity, ArrayList<String> stack) {
		for (int i = 0; i < quantity; i++) {
			System.out.print("index: "+i);
			System.out.println(stack.get(stack.size() - i - 1));
			
			String[] inputArray = stack.get(stack.size() - i - 1).split(" ",-1); 
			
			
			this.executeCommand(inputArray[0], Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]), finchInstance);
		}
	}
	
	private void quit() {
		System.out.println("quiting...");
		finchInstance.quit();
		System.exit(0);
	}
	
	
	private void executeCommand(String instructionOption, int speed, int time, Finch finchInstance) {

        switch (instructionOption) {
            case "F":  
            		forward(speed, time);
                break;
            case "B":
            		backward(speed, time);
            		break;
            case "R":
            		rightTurn(speed, time);
            		break;
            case "L":
            		leftTurn(speed, time);
            		break;
            default: 
            		System.out.println("unknown input!");
                break;
        }
	}
	
	private String getUserInput() {
		return JOptionPane.showInputDialog("Enter your instruction as [command], [time], [speed]");
		
	}
	
	private void infoBox(String content, String title)
    {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
