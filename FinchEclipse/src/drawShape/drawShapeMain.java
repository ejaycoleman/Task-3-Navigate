package drawShape;
import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class drawShapeMain {	
	static String[][] mainMenuOptions = new String[][]{
		{"R","Rectangle"},
		{"T","Triangle"},
		{"Q","Quit"}
	};

	static Scanner scan = new Scanner(System.in);;
	public static Finch drawShapeFinch = new Finch();

	//public static void main(String[] args) throws ShapeException {
	public void start() throws ShapeException {
		// TODO Auto-generated method stub
		String optionSelected = null;
		while (optionSelected != "Q") {
			optionSelected = DisplayOptions();
			boolean inputValid = CheckInput(optionSelected);

			if (inputValid)  { // IF its True
				switch (optionSelected) {
				case "R":
					try {
						int[] measurements = Shape.GetMeasurements();
						Rectangle myRect = new Rectangle(measurements[0], measurements[1]);
						myRect.draw();
						drawShapeFinch.buzz(500, 2000);
						Rectangle.shapesDrawn.add(String.valueOf(measurements[0]) + ";" + String.valueOf(measurements[1]));
					} catch (ShapeException e) {
						System.out.println(e);
					}
					break;

				case "T":
					try {
						int[] measurements = Triangle.GetMeasurements();
						Triangle myTri = new Triangle(measurements[0], measurements[1], measurements[2]);
						myTri.draw();
						drawShapeFinch.buzz(500, 2000);
						Triangle.shapesDrawn.add(String.valueOf(measurements[0]) + ";" + String.valueOf(measurements[1]) + ";" + String.valueOf(measurements[2]));
					} catch (ShapeException e) {
						System.out.println(e);
					}
					break;

				case "Q":
					drawShapeFinch.buzz(500, 2000);
					drawShapeFinch.buzz(500, 2000);
					parseShapesDrawn();
					System.exit(0);
				}
			}
		}
	}

	static void parseShapesDrawn() {
		clearConsole();

		int rectangleDrawnCount = Rectangle.shapesDrawn.size();
		int triangleDrawnCount = Triangle.shapesDrawn.size();
		System.out.println("Rectangle\n");
		for (String shapeString : Rectangle.shapesDrawn) {
			int width = Integer.parseInt(shapeString.substring(0, shapeString.indexOf(";")));
			int height = Integer.parseInt(shapeString.substring(shapeString.lastIndexOf(";") + 1));
			System.out.println("Width: " + String.valueOf(width) + "	" + "Height: " + String.valueOf(height));
		}
		System.out.println();
		System.out.println("Rectangle was drawn " + String.valueOf(Rectangle.shapesDrawn.size()) + " time(s)");
		System.out.println();

		System.out.println("Triangle\n");
		for (String shapeString : Triangle.shapesDrawn) {
			int sideA = Integer.parseInt(shapeString.substring(0, shapeString.indexOf(";")));
			int sideB = Integer.parseInt(shapeString.substring(shapeString.indexOf(";") + 1, shapeString.lastIndexOf(";")));
			int sideC = Integer.parseInt(shapeString.substring(shapeString.lastIndexOf(";") + 1));

			System.out.println("Side A: " + String.valueOf(sideA) + "	" + "Side B: " + String.valueOf(sideB) + "	" + "Side C: " + String.valueOf(sideC));
		}
		System.out.println();
		System.out.println("Triangle was drawn " + String.valueOf(Triangle.shapesDrawn.size()) + " time(s)");
		System.out.println();

		calculateMostFrequent(rectangleDrawnCount, triangleDrawnCount);
	}

	static void calculateMostFrequent(int rectCount, int triCount) {
		if (rectCount > triCount) {
			System.out.println("Rectangle was drawn more frequently.");
		} else if (triCount > rectCount) {
			System.out.println("Triangle was drawn more frequently.");
		} else {
			System.out.println("Rectangle and Triangle have the same frequency drawn.");
		}
	}

	public static String DisplayOptions() {
		System.out.println("Welcome, please select one of the options below:");
		for (int i = 0; i < mainMenuOptions.length; i++) {
			String textToDisplay = mainMenuOptions[i][0] + " - ";
			// Quit is exempt from having "Draw" be added to its text display
			// Have both statements, in case "Quit" becomes "Exit", or another term
			// with the same meaning
			if (mainMenuOptions[i][1] == "Quit" || mainMenuOptions[i][0] == "Q") {
				textToDisplay += mainMenuOptions[i][1];
			}

			else {
				textToDisplay += "Draw " + mainMenuOptions[i][1];
			}

			System.out.println(textToDisplay);
		}
		System.out.println();
		System.out.print("Option: ");
		String optionSelected = scan.next();
		optionSelected = optionSelected.toUpperCase();

		return optionSelected;
	}

	public static boolean CheckInput(String text) {

		boolean validInput = false;

		for (int i = 0; i < mainMenuOptions.length; i++) {
			if (!mainMenuOptions[i][0].equals(text)) {
				validInput = false;
			}

			else {
				validInput = true;
				return validInput;
			}
		}

		if (validInput == false) {
			clearConsole();
			System.out.println("Dear user, the option (" + text + ") "
					+ "is not a valid option. Could you please try again?\n");
		}

		return validInput;

	}

	public final static void clearConsole()
	{
		try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				Runtime.getRuntime().exec("cls");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{
			//  Handle any exceptions.
		}
	}

}
