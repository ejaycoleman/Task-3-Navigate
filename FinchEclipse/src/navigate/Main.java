package navigate;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Main {
	public static void main(final String[] args) {
		Finch finchInstance = new Finch();

		finchInstance.setLED(255, 255, 255);
		//finchInstance.sleep(8000);
		
		
				
		String inputString = "L 4 100";
		
		String[] inputArray = inputString.split(" ",-1); 
		
		String instructionOption = inputArray[0];
		
		int speed;
		int time;
		int quantity;

        switch (instructionOption) {
            case "F":  
            		speed = Integer.parseInt(inputArray[1]);
            		time = Integer.parseInt(inputArray[2]);
            		forward(finchInstance, speed, time);
                break;
            case "B":
	            	speed = Integer.parseInt(inputArray[1]);
	        		time = Integer.parseInt(inputArray[2]);
            		backward(finchInstance, speed, time);
            		break;
            case "R":
	            	speed = Integer.parseInt(inputArray[1]);
	        		time = Integer.parseInt(inputArray[2]);
            		rightTurn(finchInstance, speed, time);
            		break;
            case "L":
	            	speed = Integer.parseInt(inputArray[1]);
	        		time = Integer.parseInt(inputArray[2]);
            		leftTurn(finchInstance, speed, time);
            		break;
            case "T":
            		quantity = Integer.parseInt(inputArray[1]);
	            	reTraceMovements(finchInstance, quantity);
	            	break;
            case "Q":
            		quit(finchInstance);
            		break;
            default: 
            		System.out.println("unknown input!");
                break;
        }
		
		// Always end your program with finch.quit()
		quit(finchInstance);
	}
	
	private static void forward(Finch finchInstance, int time, int speed) {
		finchInstance.setWheelVelocities(speed, speed, time);
	}
	
	private static void backward(Finch finchInstance, int time, int speed) {
		finchInstance.setWheelVelocities(-speed, -speed, time);
	}
	
	private static void rightTurn(Finch finchInstance, int time, int speed) {
		finchInstance.setWheelVelocities(255, -255, 500);
		finchInstance.setWheelVelocities(speed, speed, time);
	}
	
	private static void leftTurn(Finch finchInstance, int time, int speed) {
		finchInstance.setWheelVelocities(-255, 255, 500);
		finchInstance.setWheelVelocities(speed, speed, time);
	}
	
	private static void reTraceMovements(Finch finchInstance, int quantity) {
		
	}
	
	private static void quit(Finch finchInstance) {
		finchInstance.quit();
		System.exit(0);
	}
}




