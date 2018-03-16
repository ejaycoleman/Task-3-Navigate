package drawShape;
import java.util.ArrayList;

public class Rectangle extends Shape {
	
	static ArrayList<String> shapesDrawn = new ArrayList<String>();

	Rectangle(int widthCm, int heightCm) throws ShapeException {
		if (widthCm >= minWidthCm && widthCm <= maxWidthCm) {
			this.width = widthCm;
		} else {
			throw new ShapeException("The width must be between " + minWidthCm + " and " + maxWidthCm + ". You entered the value " + widthCm);
		}
		
		if (heightCm >= minHeightCm && heightCm <= maxHeightCm) {
			this.height = heightCm;
		} else {
			throw new ShapeException("The height must be between " + minHeightCm + " and " + maxHeightCm + ". You entered the value " + heightCm);
		}
	}
	
	public void draw() {
		timeToDraw = (multipler * height) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw + 1000);
		turnRight();
		
		// Draw width
		timeToDraw = (multipler * width) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw);
		turnRight();
		
		// Draw height again
		timeToDraw = (multipler * height) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw + 1000);
		turnRight();
		
		// Draw width
		timeToDraw = (multipler * width) * 1000;
		drawShapeMain.myFinch.setWheelVelocities(drawSpeed, drawSpeed, (int)timeToDraw);
	}
	
	public void turnRight() {
		drawShapeMain.myFinch.setWheelVelocities(150, -75, 1000);
	}
	
	public void turnLeft() {
		drawShapeMain.myFinch.setWheelVelocities(-75, 150, 1000);
	}
}
