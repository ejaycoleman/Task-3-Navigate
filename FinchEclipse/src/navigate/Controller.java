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
	private ArrayList<String> stack;
	
	private void setUpFinch() {
		this.finchInstance = new Finch();
	}
	
	public void start() {
		String inputString;
		inputString = "";
		
		this.stack = new ArrayList<String>();
		
		
		while(inputString != "Q") {
			inputString = getUserInput();
			
			if (inputString == null) {
				break;
			}
			
			
			System.out.println(inputString);
			String[] inputArray = inputString.split(" ",-1); 
			
		
			
			String instructionOption = inputArray[0];
			int[] testedParameters;
			

			switch (instructionOption) {
		    		case "F": 
		    		case "B":
		    		case "L":
		    		case "R":
		    			int time = Integer.parseInt(inputArray[1]);
					int speed = Integer.parseInt(inputArray[2]);
					
				
					if (callTwoParameter(inputArray, inputString)) {
						switch (instructionOption) {
			            		case "F": 
			            			forward(time, speed);
			            			break;
			            		case "B":
			            			backward(time, speed);
			            			break;
			            		case "L":
			            			leftTurn(time, speed);
			            			break;
			            		case "R":
			            			rightTurn(time, speed);
			            			break;
			            		default: 
				            		System.out.println("unknown input!");
				                break;
						}        			
					} else {
						break;
					}
		    			break;
		    		case "T":
		    			testedParameters = testParameters(inputArray, 2);
		            	
		        		if (testedParameters != null) {
		
		            		if (testedParameters[0] > stack.size()) {
		            			infoBox("Stack cannot exceed: "+ stack.size(), "Stack Overflow!");
		            			break;
		            		} else {
				            	reTraceMovements(testedParameters[0], stack);
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

		quit();
	}
	
	private int[] testParameters(String[] inputArray, int expected) {
		int speed;
		int time;
		int amount;
		if (inputArray.length == expected) {
			
			try {
				if (expected == 3) {
					speed = Integer.parseInt(inputArray[1]);
	        			time = Integer.parseInt(inputArray[2]);
	        			int[] returnArray = {speed, time};
	        			return returnArray;
				} else {
					amount = Integer.parseInt(inputArray[1]);
					int[] returnArray = {amount};
					return returnArray;
				}
			} catch (NumberFormatException e) {
				return null;
			}
			
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
	
	
	private boolean callTwoParameter(String[] inputArray, String inputString) {
		int[] testedParameters = testParameters(inputArray, 3);

		if (testedParameters != null) {
			if (checkInRange(testedParameters[0], testedParameters[1])) {
	    			this.stack.add(inputString);
	    			//forward(testedParameters[0], testedParameters[1]);
	        		return true;
	    		} else {
	    			return false;
	    		}
		} else {
			infoBox("The "+ inputArray[0] +" command requires two parameters only", "Unexpected Input");
			return false;
		}
	}
}
