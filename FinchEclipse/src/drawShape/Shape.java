package drawShape;
import java.util.ArrayList;

public class Shape {
	static final int minWidthCm = 30;
	static final int maxWidthCm = 90;
	
	static final int minHeightCm = 30;
	static final int maxHeightCm = 90;
	
	int width;
	int height;
	double timeToDraw;
	static final int drawSpeed = 40;
	
	static final double multipler = 0.2; // Used to determine how long to draw.
	static ArrayList<String> shapesDrawn = new ArrayList<String>();
	
	Shape() throws ShapeException {

	}
	
	public static int[] GetMeasurements() {
		int[] measurements = new int[2];
		
		System.out.print("Width: ");
		int width = drawShapeMain.scan.nextInt();
		System.out.print("Height: ");
		int height = drawShapeMain.scan.nextInt();
		
		measurements[0] = width;
		measurements[1] = height;
		
		return measurements;
	}
}
