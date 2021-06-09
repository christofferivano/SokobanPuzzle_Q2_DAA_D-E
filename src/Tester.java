import java.io.IOException;

public class Tester
{
	public static void main (String[] args)
	{
		try
		{
			MainFrame frame = new MainFrame();
		}
		catch (IOException e)
		{
			System.out.println("IO Exception Occured");
			e.printStackTrace();
		}
		
	}
}
