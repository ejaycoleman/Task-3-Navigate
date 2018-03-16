package selectTask;

//package navigate;
import navigate.*;

import followLightTask.*;

import drawShape.*;
//import followLightTask.*;

public class Main {
	public static void main(final String[] args) {
		String instructionOption = "C";
		
		switch (instructionOption) {
			case "A":
				Controller finch = new Controller();
				finch.start();//new navigate.run();
			case "B":
				drawShapeMain finchTwo = new drawShapeMain();
				try {
					finchTwo.start();
				} catch (ShapeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("ok");
			case "C":
				System.out.println("ok");
				//startFollowLight finchThree = new startFollowLight();
				followLight finchThree = new followLight();
				finchThree.startFollowLight();
				
//			default:
//				System.out.println("unknown");
		}
		
		//new drawShape.Main();
		
		//new followLightTask.followLight();
		
	}
}
