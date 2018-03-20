package dance;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class ActualCode {
	
	
	
	public void Run(String Text,Finch Fin)
	{
		if(Text.matches("[0-9a-fA-F]{2}"))
		{
			
			Converter Conv = new Converter(Text);
			
			int Speed = (Conv.Decimal < 80) ? Conv.Decimal + 30 : Conv.Decimal;

			int Red = Conv.Decimal;
			int Green = (Conv.Decimal%80)+60;
			int Blue = (Red+Green)/2;

			System.out.println(Text +","+Conv.Decimal+","+Conv.Binary+",Speed = "+Speed+",R:"+Red+",G:"+Green+",B:"+Blue);

			Fin.setLED(Red, Green, Blue);
			for(int i = 0; i < Conv.Binary.length();i++)
			{
				int Movement = Speed * ((Conv.Binary.charAt(i) == '1')? 1:-1);
				Fin.setWheelVelocities(Movement, Movement, 2000);
			}
			Fin.setLED(0, 0, 0);
		}
		else
		{
			if(Text.length() > 2) System.out.println("Hex too long");
			if(Text.length() < 2) System.out.println("Hex too short");
			if(Text.matches(".{2}")) System.out.println("Hex contains invalid characters");
		}
	}
}
