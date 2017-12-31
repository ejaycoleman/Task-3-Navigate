package navigate;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Main {
	public static void main(final String[] args) {
		Finch finchInstance = new Finch();

		finchInstance.setLED(255, 255, 255);
		finchInstance.sleep(8000);
		
		// Always end your program with finch.quit()
		quit(finchInstance);
	}
	
	private static void forward(Finch finchInstance) {
		
	}
	
	private static void backward(Finch finchInstance) {
		
	}
	
	private static void rightTurn(Finch finchInstance) {
		
	}
	
	private static void leftTurn(Finch finchInstance) {
		
	}
	
	private static void reTraceMovements(Finch finchInstance) {
		
	}
	
	private static void quit(Finch finchInstance) {
		finchInstance.quit();
		System.exit(0);
	}
}




