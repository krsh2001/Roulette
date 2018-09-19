// The "Mainmenu" class.
import java.awt.image.*;
import java.awt.*;
import hsa.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Mainmenu
{
    static Console c;           // The output console
    int count = 0;
    public void mainMenu ()
    {
	char input;
	while (true)
	{
	    try
	    {
		BufferedImage backPic = ImageIO.read (new File ("mainmenu.jpg"));
		c.drawImage (backPic, 0, 0, null);
	    }
	    catch (IOException e)
	    {
	    }
	    c.setColor (Color.yellow);

	    if (count == 0)
		c.drawRect (11, 85, 325, 231);
	    else if (count == 1)
		c.drawRect (342, 85, 288, 231);
	    else if (count == 2)
		c.drawRect (11, 330, 618, 51);
	    else if (count == 3)
		c.drawRect (11, 390, 618, 51);

	    input = c.getChar ();
	    if (input == 'a' || input == 'A')
	    {
		count--;
		if (count < 0)
		    count = 3;
	    }
	    else if (input == 'd' || input == 'd')
	    {
		count++;
		if (count > 3)
		    count = 0;
	    }
	    if (input == 10)
		break;
	}
    }


    public static void main (String[] args)
    {
	c = new Console ();
	Mainmenu m = new Mainmenu ();
	m.mainMenu ();
    } // main method
} // Mainmenu class
