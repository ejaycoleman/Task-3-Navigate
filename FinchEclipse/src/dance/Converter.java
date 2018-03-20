package dance;


public class Converter {
	static String Conv = "0123456789ABCDEF";
	static String[] Bins = new String[]{"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
	public String Binary;
	public int Decimal;
	public Converter(String Hex)
	{
		Decimal += 16 * Conv.indexOf(Hex.toUpperCase().charAt(0));
		Decimal += Conv.indexOf(Hex.toUpperCase().charAt(1));
		Binary += Bins[Conv.indexOf(Hex.toUpperCase().charAt(0))];
		Binary += Bins[Conv.indexOf(Hex.toUpperCase().charAt(1))];
	}
}
