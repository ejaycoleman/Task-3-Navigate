package drawShape;
import java.util.ArrayList;

public class Triangle extends Shape {
	
	double angleA, angleB, angleC;
	int sideA, sideB, sideC;
	static final int minCm = 30, maxCm = 90;
	static ArrayList<String> shapesDrawn = new ArrayList<String>();

	public Triangle(int sideA, int sideB, int sideC) throws ShapeException {
		//super(sideA, sideB);
		if (sideA >= minCm && sideA <= maxCm) {
			this.sideA = sideA;
		} else {
			throw new ShapeException("Side A must be between " + minCm + " and " + maxCm + ". You entered the value " + sideA);
		}
		
		if (sideB >= minCm && sideB <= maxCm) {
			this.sideB = sideB;
		} else {
			throw new ShapeException("Side B must be between " + minCm + " and " + maxCm + ". You entered the value " + sideB);
		}
		
		if (sideC >= minCm && sideC <= maxCm) {
			this.sideC = sideC;
		} else {
			throw new ShapeException("Side B must be between " + minCm + " and " + maxCm + ". You entered the value " + sideC);
		}
		
		checkValidity();
	}
	
	public void draw() {
		timeToDraw = (multipler * sideA) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw + 1000);
		drawShapeMain.myFinch.setWheelVelocities(255, 0, 1000);
		timeToDraw = (multipler * sideB) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw + 1000);
		drawShapeMain.myFinch.setWheelVelocities(150, -75, 1000);
		timeToDraw = (multipler * sideC) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw + 1000);
	}
	
	// Overriding the Shape method
	public static int[] GetMeasurements() {
		int[] measurements = new int[3];
		
		System.out.print("Side A: ");
		int sideA = drawShapeMain.scan.nextInt();
		System.out.print("Side B: ");
		int sideB = drawShapeMain.scan.nextInt();
		System.out.print("Side C: ");
		int sideC = drawShapeMain.scan.nextInt();
		
		measurements[0] = sideA;
		measurements[1] = sideB;
		measurements[2] = sideC;
		
		return measurements;
	}
	
	void checkValidity() throws ShapeException {
		angleC = Math.toDegrees(Math.acos((Math.pow(sideA, 2) + Math.pow(sideB, 2) - Math.pow(sideC, 2)) / (2 * sideA * sideB)));
		angleA = Math.toDegrees(Math.acos((Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2)) / (2 * sideB * sideC)));
		angleB = Math.toDegrees(Math.acos((Math.pow(sideA, 2) + Math.pow(sideC, 2) - Math.pow(sideB, 2)) / (2 * sideA * sideC)));		
		
		angleC = (int) Math.round(angleC);
		angleA = (int) Math.round(angleA);
		angleB = (int) Math.round(angleB);
		
		if ((angleC + angleA + angleB) != 180) {
			throw new ShapeException("Not a valid triangle! Angles only go up to " + String.valueOf(angleA + angleB + angleC) + " according to the cosine rule.");
		}
	}

}
