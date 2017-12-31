package navigate;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Main {
	public static void main(final String[] args) {
		Finch myFinch = new Finch();

		myFinch.setLED(255, 255, 255);
		myFinch.sleep(8000);
		
		// Always end your program with finch.quit()
		myFinch.quit();
		System.exit(0);
	}
}




