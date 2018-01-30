package navigate;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Controller {
	public Controller() {
		System.out.println("yes");
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
			
			
			if (inputString == null) {
				this.quit();
			}
			
			
			System.out.println(inputString);
			String[] inputArray = inputString.split(" ",-1); 
			
			
			System.out.println("Enter command as command, time, speed");
			
			System.out.println("the stack: "+stack);
			
			String instructionOption = inputArray[0];
			
			
			
			

	        switch (instructionOption) {
	            case "F":  
	            		speed = Integer.parseInt(inputArray[1]);
	            		time = Integer.parseInt(inputArray[2]);

	                
	            		if (checkInRange(speed, time)) {
		        			stack.add(inputString);
		        			forward(speed, time);
		            		break;
		        		} else {
		        			break;
		        		}
	            		
	            case "B":
		            	speed = Integer.parseInt(inputArray[1]);
		        		time = Integer.parseInt(inputArray[2]);
	            		if (checkInRange(speed, time)) {
		        			stack.add(inputString);
		        			backward(speed, time);
		            		break;
		        		} else {
		        			break;
		        		}
	            case "R":
		            	speed = Integer.parseInt(inputArray[1]);
		        		time = Integer.parseInt(inputArray[2]);
	            		
	            		if (checkInRange(speed, time)) {
		        			stack.add(inputString);
		            		rightTurn(speed, time);
		            		break;
		        		} else {
		        			break;
		        		}
	            		
	            case "L":
	            		
		            	speed = Integer.parseInt(inputArray[1]);
		        		time = Integer.parseInt(inputArray[2]);
		        		if (checkInRange(speed, time)) {
		        			stack.add(inputString);
		            		leftTurn(speed, time);
		            		break;
		        		} else {
		        			break;
		        		}
	            case "T":
	            		quantity = Integer.parseInt(inputArray[1]);
	            		if (quantity > stack.size()) {
	            			infoBox("Stack cannot exceed: "+ stack.size(), "Stack Overflow!");
	            			break;
	            		} else {
			            	reTraceMovements(quantity, stack);
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
	
	private boolean checkInRange(int time, int speed) {
		if (speed <= 200 || speed < 0) {
			infoBox("Speed must be from 0 to 200", "Out Of Range");
			return false;
		} else if (time <= 200 || time < 0) {
			infoBox("Time must be from 0 to 200", "Out Of Range");
			return false;
		} else {
			return true;
		}
	}
	
	private void forward(int time, int speed) {
		finchInstance.setWheelVelocities(speed, speed, time);
	}
	
	private void backward(int time, int speed) {
		finchInstance.setWheelVelocities(-speed, -speed, time);
	}
	
	private void rightTurn(int time, int speed) {
		finchInstance.setWheelVelocities(255, -255, 500);
		finchInstance.setWheelVelocities(speed, speed, time);
	}
	
	private void leftTurn(int time, int speed) {
		finchInstance.setWheelVelocities(-255, 255, 500);
		finchInstance.setWheelVelocities(speed, speed, time);
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
