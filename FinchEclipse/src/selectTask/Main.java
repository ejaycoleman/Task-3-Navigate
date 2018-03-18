package selectTask;

//package navigate;
import navigate.*;

import followLightTask.*;

import javax.swing.JOptionPane;

import drawShape.*;

import zigzag.*;
//import followLightTask.*;

public class Main {
	public static void main(final String[] args) {
		String instructionOption = getUserInput();

		switch (instructionOption) {
		case "navigate":
			System.out.println("Navigate");
			Controller finch = new Controller();
			finch.start();//new navigate.run();
			break;
			
		case "drawShape":
			System.out.println("Draw Shape");
			drawShapeMain finchTwo = new drawShapeMain();
			try {
				finchTwo.start();
			} catch (ShapeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("ok");
			break;
			
		case "followLight":
			System.out.println("Follow Light");
			//startFollowLight finchThree = new startFollowLight();
			followLight finchThree = new followLight();
			finchThree.startFollowLight();
			break;
			
		case "zigzag":
			System.out.println("Zig Zag");
			Finch_ZigZag finchFour = new Finch_ZigZag();
			finchFour.main();
			break;
		}

		//new drawShape.Main();

		//new followLightTask.followLight();

	}
	
	private static String getUserInput() {
		return JOptionPane.showInputDialog("drawShape, followLight, navigate, zigzag");
	}
}
