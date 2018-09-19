/*  Surya Krishnan
    Ms. Krasteva
    December 12, 2016
    Chance Roulette
*/
import java.awt.*;
import hsa.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Roulette
{
    Console c;
    int count;
    boolean found = false;
    boolean turnEnd = false;
    int scores[] = new int [10];
    String[] user1Vals = new String [3];
    String[] user2Vals = new String [3];
    String[] [] values = {{"3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36"}, {"2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35"}, {"1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34"}, {"1st 12", "2nd 12", "3rd 12"}, {"1-18", "Even", "Red", "Black", "Odd", "19-36"}, {"0", "00"}};
    public Color red = new Color (163, 35, 31);
    public Color green = new Color (10, 108, 3);
    public Color drawGrey = new Color (75, 81, 81);
    public Color lightGrey = new Color (175, 172, 172);
    Font instFont = new Font ("Myanmar Text", 1, 18);
    Font chanceFont = new Font ("Magneto", 1, 42);
    Font titleFont = new Font ("Myanmar Text", 1, 36);
    Font menuFont = new Font ("Myanmar Text", 1, 28);
    Font numberFont = new Font ("Myanmar Text", 1, 120);
    String userName1 = "";
    String userName2 = "";
    int player1Money = 100;
    int player2Money = 100;

    public Roulette ()
    {
	c = new Console ();
    }


    //Asks for user input
    private void pauseProgram (int x, int y, int size)
    {
	Font pauseFont = new Font ("Myanmar Text", 1, size);
	c.setFont (pauseFont);
	c.setColor (green);
	c.fillRect (x - 10, y - 30, 350, 50);
	c.setColor (Color.black);
	c.drawString ("Press any key to continue...", x, y);
	c.getChar ();
    }


    //Splash screen used before game launches
    public void splashScreen ()
    {
	c.setColor (green);
	c.fillRect (0, 0, 640, 500);
	for (int i = 0 ; i < 150 ; i++)
	{
	    c.setColor (green);
	    c.fillRect (0 + i, 160, 200, 200);
	    c.fillRect (480 - i, 144, 250, 201);
	    c.setColor (red);
	    c.setFont (chanceFont);
	    c.drawString ("Chance", 0 + i, 200);
	    c.setColor (Color.black);
	    c.setFont (titleFont);
	    c.drawString ("Roulette", 0 + i, 250);
	    c.fillRect (500 - i, 145, 200, 200);
	    c.setColor (red);
	    c.fillRect (510 - i, 155, 180, 180);
	    c.setColor (Color.black);
	    c.setFont (numberFont);
	    c.drawString ("5", 560 - i, 285);
	    try
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
	pauseProgram (10, 456, 28);
    }


    //Erases and draws new screen with title
    private void drawTitle ()
    {
	c.clear ();
	try
	{
	    BufferedImage background = ImageIO.read (new File ("mainback.jpg"));
	    c.drawImage (background, 0, 0, null);
	}
	catch (IOException e)
	{
	}
    }


    //Displays a main menu where users can choose what they want to do in the program
    public void mainMenu ()
    {
	c.clear ();
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


    //Finds if a new high score is made and then reads and writes to the file
    private void highScoreChecker (boolean newScore, int score, String name)
    {
	//PrintWriter output = new PrintWriter (new FileWriter ("highScores.txt"));
	String read = "";
	String userName = "";
	int temporary;
	try
	{
	    BufferedReader input = new BufferedReader (new FileReader ("highScores.txt"));
	    for (int i = 0 ; i < scores.length ; i++)
	    {
		name = input.readLine ();
		read = input.readLine ();
		scores [i] = Integer.parseInt (read);
	    }
	}
	catch (IOException e)
	{
	}
	if (newScore == true)
	{
	    for (int i = 0 ; i < scores.length ; i++)
	    {
		if (score > scores [i])
		{
		    temporary = scores [i];
		    scores [i] = score;
		    scores [i + 1] = temporary;
		    if (i == scores.length - 2)
			break;
		}
	    }
	}
	Arrays.sort (scores);
	try
	{
	    PrintWriter output = new PrintWriter (new FileWriter ("highScores.txt"));
	    for (int i = 0 ; i < scores.length ; i++)
	    {
		output.println (name);
		output.println (scores [i]);
	    }
	}
	catch (IOException e)
	{
	}
    }


    //Outputs high score
    public void highScore ()
    {
	drawTitle ();
	for (int i = 0 ; i < scores.length ; i++)
	{
	    c.println (scores [i]);
	}
	pauseProgram (10, 456, 28);
    }


    //Instructions on how to play the game
    public void instructions ()
    {
	char input;
	int instCount = 0;
	Font instFont = new Font ("Myanmar Text", 1, 18);
	int xpoints[] = {10, 20, 220, 210};
	int xpoints2[] = {210, 220, 420, 410};
	int xpoints3[] = {410, 420, 620, 610};
	int ypoints[] = {130, 80, 80, 130};
	int npoints = 4;
	while (true)
	{
	    drawTitle ();
	    c.setColor (Color.black);
	    c.fillPolygon (xpoints, ypoints, npoints);
	    c.fillPolygon (xpoints2, ypoints, npoints);
	    c.fillPolygon (xpoints3, ypoints, npoints);
	    c.setColor (Color.yellow);
	    c.drawPolygon (xpoints, ypoints, npoints);
	    c.drawPolygon (xpoints2, ypoints, npoints);
	    c.drawPolygon (xpoints3, ypoints, npoints);            //c.fillRect (10, 80, 390, 50);
	    c.setFont (menuFont);
	    c.drawString ("How to Play", 35, 115);
	    c.drawString ("Controls", 255, 115);
	    c.drawString ("Main Menu", 435, 115);
	    c.setColor (Color.black);
	    c.fillRect (10, 140, 610, 340);
	    if (instCount == 0)
	    {
		c.setColor (Color.yellow);
		c.fillPolygon (xpoints, ypoints, npoints);
		c.setColor (Color.black);
		c.setFont (menuFont);
		c.drawString ("How to Play", 35, 115);
		c.setFont (instFont);
		c.setColor (Color.yellow);
		c.drawString ("Roulette is the classic casino game, where players bet on", 20, 160);
		c.drawString ("where the ball spinning on the wheel will land.", 20, 180);
	    }
	    else if (instCount == 1)
	    {
		c.setColor (Color.yellow);
		c.fillPolygon (xpoints2, ypoints, npoints);
		c.setColor (Color.black);
		c.setFont (menuFont);
		c.drawString ("Controls", 255, 115);
		c.setFont (instFont);
		c.setColor (red);
		c.drawString ("Choosing Numbers", 20, 160);
		c.setColor (Color.yellow);
		c.drawString ("To choose a single number, enter the number you wish to bet", 20, 180);
		c.drawString ("on.", 20, 200);
		c.drawString ("To bet on 2 numbers (place bet inbetween), input:", 20, 220);
		c.drawString ("FirstNumber-SecondNumber", 20, 240);
		c.drawString ("To bet on the categories, input: CategoryName (spaces okay).", 20, 260);
		c.setColor (red);
		c.drawString ("Placing Bets", 20, 280);
		c.setColor (Color.yellow);
		c.drawString ("After you choose a number/category to place bets on, input", 20, 300);
	    }
	    else if (instCount == 2)
	    {
		c.setColor (Color.yellow);
		c.fillPolygon (xpoints3, ypoints, npoints);
		c.setColor (Color.black);
		c.setFont (menuFont);
		c.drawString ("Main Menu", 435, 115);
		c.setFont (instFont);
		c.setColor (Color.yellow);
		c.drawString ("Press Enter to Return to the Main Menu", 20, 160);
	    }
	    input = c.getChar ();
	    if (input == 'a' || input == 'A')
	    {
		instCount--;
		if (instCount < 0)
		    instCount = 2;
	    }
	    else if (input == 'd' || input == 'd')
	    {
		instCount++;
		if (instCount > 2)
		    instCount = 0;
	    }
	    if (instCount == 2 && input == 10)
		break;
	}
    }


    //Draws out the game board
    private void drawBoard (String numChoose)
    {
	c.setFont (instFont);
	for (int i = 0 ; i < values.length ; i++)
	{
	    for (int x = 0 ; x < values [i].length ; x++)
	    {
		if (i == 3 || i == 4 || i == 5)
		{
		    break;
		}
		if ((i == 0 && x == 1) || (i == 0 && x == 4) || (i == 0 && x == 7) || (i == 0 && x == 10) || (i == 1 && x == 0) || (i == 1 && x == 2) || (i == 1 && x == 3) || (i == 1 && x == 5) || (i == 1 && x == 6) || (i == 1 && x == 8) || (i == 1 && x == 9) || (i == 1 && x == 11) || (i == 2 && x == 1) || (i == 2 && x == 3) || (i == 2 && x == 4) || (i == 2 && x == 6) || (i == 2 && x == 7) || (i == 2 && x == 10))
		{
		    c.setColor (Color.black);
		    c.fillRect ((x * 33), (i * 32) + 120, 33, 32);
		}
		else
		{
		    c.setColor (red);
		    c.fillRect ((x * 33), (i * 32) + 120, 33, 32);
		}
		if (numChoose.equalsIgnoreCase (values [i] [x]))
		{
		    c.setColor (Color.yellow);
		    c.drawString (values [i] [x], 10 + (x * 32), 145 + (i * 33));
		    c.drawRect ((x * 33), (i * 32) + 120, 33, 32);
		}
		else
		{
		    c.setColor (Color.white);
		    c.drawString (values [i] [x], 10 + (x * 32), 145 + (i * 33));
		    c.drawRect ((x * 33), (i * 32) + 120, 33, 32);
		}
	    }
	    if (i == 3)
	    {
		for (int y = 0 ; y < values [3].length ; y++)
		{
		    c.setColor (green);
		    c.fillRect ((y * 132), 216, 132, 33);
		    if (numChoose.equalsIgnoreCase (values [i] [y]))
		    {

			c.setColor (Color.yellow);
			c.drawString (values [i] [y], 32 + (y * 130), 150 + (i * 30));
			c.drawRect ((y * 132), 216, 132, 33);
		    }
		    else
		    {
			c.setColor (Color.white);
			c.drawString (values [i] [y], 32 + (y * 130), 150 + (i * 30));
			c.drawRect ((y * 132), 216, 132, 33);
		    }
		}
	    }
	    else if (i == 4)
	    {
		for (int y = 0 ; y < values [4].length ; y++)
		{
		    if (y == 2)
		    {
			c.setColor (red);
			c.fillRect (132, 250, 66, 32);
			if (numChoose.equalsIgnoreCase (values [i] [y]))
			{
			    c.setColor (Color.yellow);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
			else
			{
			    c.setColor (Color.white);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
		    }
		    else if (y == 3)
		    {
			c.setColor (Color.black);
			c.fillRect (198, 250, 66, 32);
			if (numChoose.equalsIgnoreCase (values [i] [y]))
			{
			    c.setColor (Color.yellow);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
			else
			{
			    c.setColor (Color.white);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
		    }
		    else
		    {
			c.setColor (green);
			c.fillRect ((y * 66), 249, 66, 33);
			if (numChoose.equalsIgnoreCase (values [i] [y]))
			{
			    c.setColor (Color.yellow);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
			else
			{
			    c.setColor (Color.white);
			    c.drawString (values [i] [y], 10 + (y * 65), 150 + (i * 30));
			    c.drawRect ((y * 66), 248, 66, 33);
			}
		    }
		}
	    }
	    else if (i == 5)
	    {
		for (int y = 0 ; y < values [5].length ; y++)
		{
		    c.setColor (green);
		    c.fillRect ((y * 198), 87, 198, 33);
		    if (numChoose.equalsIgnoreCase (values [i] [y]))
		    {

			c.setColor (Color.yellow);
			c.drawString (values [i] [y], 100 + (y * 185), 110);
			c.drawRect ((y * 198), 87, 198, 33);

		    }
		    else
		    {
			c.setColor (Color.white);
			c.drawString (values [i] [y], 100 + (y * 185), 110);
			c.drawRect ((y * 198), 87, 198, 33);

		    }
		}
	    }
	}
    }


    //Calculates payout for user input
    private void calc (String[] choices1, String[] choices2, int p1Bet, int p2Bet, int roll)
    {
	int p1Payout = 0;
	int p2Payout = 0;
	boolean even = false;
	boolean red = false;
	int first12 = 0;
	boolean done = false;
	boolean first18 = false;
	if (roll % 2 == 0)
	{
	    even = true;
	}
	if (roll == 3 || roll == 9 || roll == 12 || roll == 18 || roll == 21 || roll == 27 || roll == 30 || roll == 36 || roll == 5 || roll == 14 || roll == 23 || roll == 32 || roll == 1 || roll == 7 || roll == 16 || roll == 25 || roll == 28 || roll == 34)
	{
	    red = true;
	}
	if (roll - 12 <= 0)
	{
	    first12 = 0;
	}
	else if (roll - 12 > 0 && roll - 12 <= 12)
	{
	    first12 = 1;
	}
	else if (roll - 12 > 12)
	{
	    first12 = 2;
	}
	if (roll - 18 <= 0)
	{
	    first18 = true;
	}
	for (int i = 0 ; i <= choices1.length - 1 ; i++)
	{
	    if (choices1 [i] == null)
		break;
	    if ((choices1 [i].equalsIgnoreCase ("red") && red == true) || (choices1 [i].equalsIgnoreCase ("black") && red == false) || (choices1 [i].equalsIgnoreCase ("1-18") && first18 == true) || (choices1 [i].equalsIgnoreCase ("19-36") && first18 == false) || (choices1 [i].equalsIgnoreCase ("even") && even == true) || (choices1 [i].equalsIgnoreCase ("odd") && even == false))
	    {
		p1Payout += p1Bet + (p1Bet * 1);
	    }
	    if ((choices1 [i].equalsIgnoreCase ("1st 12") && first12 == 0) || (choices1 [i].equalsIgnoreCase ("2nd 12") && first12 == 1) || (choices1 [i].equalsIgnoreCase ("3rd 12") && first12 == 2))
	    {
		p1Payout += p1Bet + (p1Bet * 2);
	    }
	    if (choices1 [i] == String.valueOf (roll))
	    {
		p1Payout += p1Bet + (p1Bet * 35);
	    }
	    if (choices1 [i].equalsIgnoreCase ("even") && even == true)
	    {
		p1Payout += p1Bet + (p1Bet * 1);
	    }
	}
	for (int i = 0 ; i <= choices2.length - 1 ; i++)
	{
	    if (choices2 [i] == null)
		break;
	    if ((choices2 [i].equalsIgnoreCase ("red") && red == true) || (choices2 [i].equalsIgnoreCase ("black") && red == false) || (choices2 [i].equalsIgnoreCase ("1-18") && first18 == true) || (choices2 [i].equalsIgnoreCase ("19-36") && first18 == false) || (choices2 [i].equalsIgnoreCase ("even") && even == true) || (choices2 [i].equalsIgnoreCase ("odd") && even == false))
	    {
		p2Payout += p2Bet + (p2Bet * 1);
	    }
	    if ((choices2 [i].equalsIgnoreCase ("1st 12") && first12 == 0) || (choices2 [i].equalsIgnoreCase ("2nd 12") && first12 == 1) || (choices2 [i].equalsIgnoreCase ("3rd 12") && first12 == 2))
	    {
		p2Payout += p2Bet + (p2Bet * 2);
	    }
	    if (choices2 [i] == String.valueOf (roll))
	    {
		p2Payout += p2Bet + (p2Bet * 35);
	    }
	    if (choices2 [i].equalsIgnoreCase ("even") && even == true)
	    {
		p2Payout += p2Bet + (p2Bet * 1);
	    }
	}
	player1Money = player1Money + p1Payout;
	player2Money = player2Money + p2Payout;
    }


    //Roulette game
    public void roulette ()
    {
	int count = 0;
	Color gameGrey = new Color (89, 89, 89);
	int xpoints[] = {0, 0, 200, 220};
	int ypoints[] = {430, 400, 400, 430};
	int xpoints2[] = {640, 640, 440, 420};
	int ypoints2[] = {430, 400, 400, 430};
	int npoints = 4;
	int range;
	int bet = 0;
	int player1Bet = 0;
	int player2Bet = 0;
	int nameCount = 0;
	int turn = 0;
	int pot = 0;
	int rCount = 1;
	boolean gameStart = false;
	char number = 0;
	boolean betMade = false;
	String betNum = "";
	String check = "";
	String betInput = "";
	boolean names = false;
	boolean playerName1 = false;
	//Draws the game UI
	drawTitle ();
	c.setColor (drawGrey);
	c.fillPolygon (xpoints2, ypoints2, npoints);
	c.setColor (Color.black);
	c.drawRect (0, 430, 640, 70);
	c.drawPolygon (xpoints2, ypoints2, npoints);
	c.setFont (instFont);
	c.setColor (Color.white);
	c.drawString ("Current Money:", 10, 395);
	c.drawString ("'q' to end turn", 450, 420);
	//Input
	while (true)
	{
	    while (true)
	    {
		rCount = 0;
		drawBoard (" ");
		c.setColor (gameGrey);
		c.fillRect (400, 431, 240, 69);
		c.setColor (Color.black);
		c.setFont (titleFont);
		c.drawString ("Pot: $" + pot, 420, 475);
		c.setFont (instFont);
		c.setColor (red);
		c.fillOval (420, 120, 80, 80);
		c.fillPolygon (xpoints, ypoints, npoints);
		c.setColor (green);
		c.fillOval (520, 120, 80, 80);
		c.setColor (Color.black);
		c.fillOval (520, 220, 80, 80);
		c.drawPolygon (xpoints, ypoints, npoints);
		c.setColor (Color.blue);
		c.fillRect (0, 400, 105, 30);
		c.fillOval (420, 220, 80, 80);
		c.setColor (Color.white);
		c.fillOval (430, 130, 60, 60);
		c.fillOval (530, 130, 60, 60);
		c.fillOval (430, 230, 60, 60);
		c.fillOval (530, 230, 60, 60);
		c.drawString (String.valueOf (player1Money), 35, 422);
		c.drawString (String.valueOf (player2Money), 135, 422);
		c.setColor (Color.black);
		c.setFont (instFont);
		c.drawString ("$5", 450, 165);
		c.drawString ("$25", 545, 165);
		c.drawString ("$50", 445, 265);
		c.drawString ("$100", 540, 265);
		//Asks for names of users
		if (gameStart == false)
		{
		    if (playerName1 == false)
		    {
			c.setColor (Color.black);
			c.fillRect (0, 281, 396, 33);
			c.setColor (Color.white);
			c.drawRect (0, 281, 396, 33);
			c.fillRect (0, 314, 396, 33);
			c.drawString ("Please enter player 1's name:", 10, 305);
		    }
		    else
		    {
			c.setColor (Color.black);
			c.fillRect (0, 281, 396, 33);
			c.setColor (Color.white);
			c.drawRect (0, 281, 396, 33);
			c.fillRect (0, 314, 396, 33);
			c.drawString ("Please enter player 2's name:", 10, 305);
		    }
		    while (true)
		    {
			number = c.getChar ();
			if (nameCount == 0)
			{
			    if (number == 10 && userName1.length () == 0 || number == 8 && userName1.length () == 0)
			    {
				userName1 = "";
				new Message ("Please enter a name!");
				continue;
			    }
			    userName1 += number;
			    c.setColor (Color.black);
			    c.drawString (userName1, 10, 335);
			    if (number == 8)
			    {
				userName1 = userName1.substring (0, userName1.length () - 2);
				c.setColor (Color.white);
				c.fillRect (0, 314, 396, 33);
				c.setColor (Color.black);
				c.drawString (userName1, 10, 335);
			    }
			}
			else if (nameCount == 1)
			{
			    if (number == 10 && userName2.length () == 0 || number == 8 && userName2.length () == 0)
			    {
				userName1 = "";
				new Message ("Please enter a name!");
				continue;
			    }
			    userName2 += number;
			    c.setColor (Color.black);
			    c.drawString (userName2, 10, 335);
			    if (number == 8)
			    {
				userName2 = userName2.substring (0, userName2.length () - 2);
				c.setColor (Color.white);
				c.fillRect (0, 314, 396, 33);
				c.setColor (Color.black);
				c.drawString (userName2, 10, 335);
			    }
			}
			if (number == 10)
			{
			    nameCount++;
			    if (nameCount == 2)
			    {
				gameStart = true;
				break;
			    }
			    if (nameCount == 1)
			    {
				playerName1 = true;
				break;
			    }
			}
		    }
		    if (gameStart == false)
		    {
			continue;
		    }
		}
		if (betNum.equals ("q") && betMade == true && turn == 0) //Ends turn
		{
		    turn++;
		    count = 0;
		    betNum = "";
		    continue;
		}
		if (betNum.equals ("q") && betMade == true && turn == 1) //Ends turn
		{
		    turn--;
		    count = 0;
		    betNum = "";
		    break;
		}
		//Draws name
		if (turn == 0)
		{
		    c.setColor (Color.blue);
		    c.fillRect (0, 281, 396, 33);
		    c.setColor (Color.white);
		    c.drawString (userName1 + "'s turn", 10, 305);
		}
		else
		{
		    c.setColor (red);
		    c.fillRect (0, 281, 396, 33);
		    c.setColor (Color.white);
		    c.drawString (userName2 + "'s turn", 10, 305);
		}
		c.setColor (Color.white);
		c.drawRect (0, 281, 396, 33);
		c.fillRect (0, 314, 397, 33);
		//Asks user for number choice
		c.setColor (Color.black);
		c.drawString ("Enter the value you want to bet on:", 10, 335);
		while (true)
		{
		    if (count == user1Vals.length)
		    {
			count++;
			new Message ("You have reached your bet limit! Your turn will now be ended!");
			betNum = "q";
			c.setColor (Color.white);
			c.fillRect (0, 314, 396, 33);
			break;
		    }
		    number = c.getChar ();
		    if (number == 's' || number == 'S')
		    {
			if (rCount == 1 || rCount == 4 || rCount == 7 || rCount == 10)
			{
			    rCount = 39;
			}
			else if (rCount == 13 || rCount == 16 || rCount == 19 || rCount == 22)
			{
			    rCount = 40;
			}
			else if (rCount == 25 || rCount == 28 || rCount == 31 || rCount == 34)
			{
			    rCount = 41;
			}
			else if (rCount == 37)
			{
			    rCount = 3;
			}
			else if (rCount == 38)
			{
			    rCount = 21;
			}
			else if (rCount == 39)
			{
			    rCount = 42;
			}
			else if (rCount == 40)
			{
			    rCount = 44;
			}
			else if (rCount == 41)
			{
			    rCount = 46;
			}
			else
			{
			    rCount--;
			}
		    }
		    else if (number == 'w' || number == 'W')
		    {
			if (rCount == 3 || rCount == 6 || rCount == 9 || rCount == 12 || rCount == 15 || rCount == 18)
			{
			    rCount = 37;
			}
			else if (rCount == 21 || rCount == 24 || rCount == 27 || rCount == 30 || rCount == 33 || rCount == 36)
			{
			    rCount = 38;
			}
			else if (rCount == 39)
			{
			    rCount = 1;
			}
			else if (rCount == 40)
			{
			    rCount = 13;
			}
			else if (rCount == 41)
			{
			    rCount = 25;
			}
			else if (rCount == 42 || rCount == 43)
			{
			    rCount = 39;
			}
			else if (rCount == 44 || rCount == 45)
			{
			    rCount = 40;
			}
			else if (rCount == 46 || rCount == 47)
			{
			    rCount = 41;
			}
			else
			{
			    rCount++;
			}
		    }
		    else if (number == 'a' || number == 'A')
		    {
			if (rCount == 37)
			{
			    rCount++;
			}
			else if (rCount == 38 || rCount == 40 || rCount == 41 || rCount == 43 || rCount == 44 || rCount == 45 || rCount == 46 || rCount == 47)
			{
			    rCount--;
			}
			else if (rCount == 39)
			{
			    rCount += 2;
			}
			else if (rCount == 42)
			{
			    rCount += 5;
			}
			else
			{
			    rCount -= 3;
			    if (rCount == -2)
				rCount = 34;
			    else if (rCount == -1)
				rCount = 35;
			    else if (rCount == 0)
				rCount = 36;
			}
		    }
		    else if (number == 'd' || number == 'D')
		    {
			if (rCount == 37 || rCount == 39 || rCount == 40 || rCount == 42 || rCount == 43 || rCount == 44 || rCount == 45 || rCount == 46)
			{
			    rCount++;
			}
			else if (rCount == 38)
			{
			    rCount--;
			}
			else if (rCount == 41)
			{
			    rCount -= 2;
			}
			else if (rCount == 47)
			{
			    rCount -= 5;
			}
			else
			{
			    rCount += 3;
			    if (rCount == 37)
				rCount = 1;
			    else if (rCount == 38)
				rCount = 2;
			    else if (rCount == 39)
				rCount = 3;
			}
		    }
		    else if ((number == 'q' || number == 'Q') && betMade == true)
		    {
			betNum = "q";
			break;
		    }
		    if (rCount == 37)
			drawBoard ("0");
		    else if (rCount == 38)
			drawBoard ("00");
		    else if (rCount == 39)
			drawBoard ("1st 12");
		    else if (rCount == 40)
			drawBoard ("2nd 12");
		    else if (rCount == 41)
			drawBoard ("3rd 12");
		    else if (rCount == 42)
			drawBoard ("1-18");
		    else if (rCount == 43)
			drawBoard ("Even");
		    else if (rCount == 44)
			drawBoard ("Red");
		    else if (rCount == 45)
			drawBoard ("Black");
		    else if (rCount == 46)
			drawBoard ("Odd");
		    else if (rCount == 47)
			drawBoard ("19-36");
		    else
			drawBoard (String.valueOf (rCount));
		    if (number == 10)
		    {
			count++;
			break;
		    }
		}
		if ((number == 'q' || number == 'Q') && betMade == true)
		{
		    continue;
		}
		if (turn == 0)
		{
		    if (rCount == 37)
			user1Vals [count - 1] = "0";
		    else if (rCount == 38)
			user1Vals [count - 1] = "00";
		    else if (rCount == 39)
			user1Vals [count - 1] = "1st 12";
		    else if (rCount == 40)
			user1Vals [count - 1] = "2nd 12";
		    else if (rCount == 41)
			user1Vals [count - 1] = "3rd 12";
		    else if (rCount == 42)
			user1Vals [count - 1] = "1-18";
		    else if (rCount == 43)
			user1Vals [count - 1] = "Even";
		    else if (rCount == 44)
			user1Vals [count - 1] = "Red";
		    else if (rCount == 45)
			user1Vals [count - 1] = "Black";
		    else if (rCount == 46)
			user1Vals [count - 1] = "Odd";
		    else if (rCount == 47)
			user1Vals [count - 1] = "19-36";
		    else
			user1Vals [count - 1] = String.valueOf (rCount);
		}
		else if (turn == 1)
		{
		    if (rCount == 37)
			user2Vals [count - 1] = "0";
		    else if (rCount == 38)
			user2Vals [count - 1] = "00";
		    else if (rCount == 39)
			user2Vals [count - 1] = "1st 12";
		    else if (rCount == 40)
			user2Vals [count - 1] = "2nd 12";
		    else if (rCount == 41)
			user2Vals [count - 1] = "3rd 12";
		    else if (rCount == 42)
			user2Vals [count - 1] = "1-18";
		    else if (rCount == 43)
			user2Vals [count - 1] = "Even";
		    else if (rCount == 44)
			user2Vals [count - 1] = "Red";
		    else if (rCount == 45)
			user2Vals [count - 1] = "Black";
		    else if (rCount == 46)
			user2Vals [count - 1] = "Odd";
		    else if (rCount == 47)
			user2Vals [count - 1] = "19-36";
		    else
			user2Vals [count - 1] = String.valueOf (rCount);
		}
		c.setColor (Color.white);
		c.drawRect (0, 281, 396, 33);
		c.fillRect (0, 314, 396, 33);
		c.setFont (instFont);
		int chipCount = 0;
		//Asks user for how much they want to bet
		c.setColor (Color.black);
		c.drawString ("Enter your bet amount (use 'r' to input):", 10, 335);
		boolean moneyBet = false;
		while (true)
		{
		    c.setColor (Color.yellow);
		    if (chipCount == 0)
			c.drawOval (420, 120, 80, 80);
		    else if (chipCount == 1)
			c.drawOval (520, 120, 80, 80);
		    else if (chipCount == 2)
			c.drawOval (420, 220, 80, 80);
		    else if (chipCount == 3)
			c.drawOval (520, 220, 80, 80);

		    number = c.getChar ();
		    if (number == 'A' || number == 'a')
		    {
			if (chipCount == 0)
			{
			    c.setColor (red);
			    c.drawOval (420, 120, 80, 80);
			}
			else if (chipCount == 1)
			{
			    c.setColor (green);
			    c.drawOval (520, 120, 80, 80);
			}
			else if (chipCount == 2)
			{
			    c.setColor (Color.blue);
			    c.drawOval (420, 220, 80, 80);
			}
			else if (chipCount == 3)
			{
			    c.setColor (Color.black);
			    c.drawOval (520, 220, 80, 80);
			}
			chipCount--;
			if (chipCount < 0)
			    chipCount = 3;

		    }
		    else if (number == 'D' || number == 'd')
		    {
			if (chipCount == 0)
			{
			    c.setColor (red);
			    c.drawOval (420, 120, 80, 80);
			}
			else if (chipCount == 1)
			{
			    c.setColor (green);
			    c.drawOval (520, 120, 80, 80);
			}
			else if (chipCount == 2)
			{
			    c.setColor (Color.blue);
			    c.drawOval (420, 220, 80, 80);
			}
			else if (chipCount == 3)
			{
			    c.setColor (Color.black);
			    c.drawOval (520, 220, 80, 80);
			}
			chipCount++;
			if (chipCount > 3)
			    chipCount = 0;
		    }
		    if (number == 'r' || number == 'R')
		    {
			moneyBet = true;
			if (chipCount == 0)
			{
			    bet = 5;
			}
			else if (chipCount == 1)
			{
			    bet = 25;
			}
			else if (chipCount == 2)
			{
			    bet = 50;
			}
			else if (chipCount == 3)
			{
			    bet = 100;
			}
			if (turn == 0)
			{
			    player1Bet += bet;
			    if (player1Bet > player1Money)
			    {
				player1Bet = player1Money;
				player1Money = 0;
			    }
			    else
				player1Money -= bet;

			}
			else if (turn == 1)
			{
			    player2Bet += bet;
			    if (player2Bet > player2Money)
			    {
				player2Bet = player2Money;
				player2Money = 0;
			    }
			    else
				player2Money -= bet;
			}
			c.setColor (red);
			c.fillPolygon (xpoints, ypoints, npoints);
			c.setColor (Color.black);
			c.drawPolygon (xpoints, ypoints, npoints);
			c.setColor (Color.blue);
			c.fillRect (0, 400, 105, 30);
			c.setColor (Color.white);
			c.drawString (String.valueOf (player1Money), 35, 422);
			c.drawString (String.valueOf (player2Money), 135, 422);
			if ((turn == 0 && player1Money == 0) || (turn == 1 && player2Money == 0))
			{
			    new Message ("No more money left to bet! Turn will be ended.");
			    betMade = true;
			    betNum = "q";
			    break;
			}
		    }
		    if (number == 10 && moneyBet == true)
		    {
			betMade = true;
			break;
		    }
		}
	    }
	    int rolledNum = 0;
	    for (int i = 0 ; i < 15 ; i++) //Rolls the wheel, outputs numbers that are rolled by
	    {
		drawBoard (" ");
		range = ((int) ((38 * Math.random ()) + 1));
		if (range == 3 || range == 9 || range == 12 || range == 18 || range == 21 || range == 27 || range == 30 || range == 36 || range == 5 || range == 14 || range == 23 || range == 32 || range == 1 || range == 7 || range == 16 || range == 25 || range == 28 || range == 34)
		{
		    c.setColor (red);
		    c.fillRect (420, 122, 180, 180);
		    c.setFont (numberFont);
		    c.setColor (Color.white);
		    if (String.valueOf (range).length () == 2)
		    {
			c.drawString (String.valueOf (range), 425, 255);
		    }
		    else
		    {
			c.drawString (String.valueOf (range), 465, 255);
		    }

		}
		else if (range == 37 || range == 38)
		{
		    c.setColor (green);
		    c.fillRect (420, 122, 180, 180);
		    c.setFont (numberFont);
		    c.setColor (Color.white);
		    if (range == 37)
		    {
			c.drawString (values [5] [0], 465, 255);
		    }
		    else
		    {
			c.drawString (values [5] [1], 425, 255);
		    }
		}
		else
		{
		    c.setColor (Color.black);
		    c.fillRect (420, 122, 180, 180);
		    c.setFont (numberFont);
		    c.setColor (Color.white);
		    if (String.valueOf (range).length () == 2)
		    {
			c.drawString (String.valueOf (range), 425, 255);
		    }
		    else
		    {
			c.drawString (String.valueOf (range), 465, 255);
		    }
		}
		if (i == 14)
		{
		    rolledNum = range;
		}
		try
		{
		    Thread.sleep (200);
		}
		catch (Exception e)
		{
		}
	    }
	    calc (user1Vals, user2Vals, player1Bet, player2Bet, rolledNum);
	    pauseProgram (10, 500, 28);
	}

    }


    public void goodBye ()
    {
	drawTitle ();
	pauseProgram (10, 456, 28);
	c.close ();
    }


    public static void main (String[] args)
    {
	Roulette r = new Roulette ();
	//r.splashScreen ();
	while (true)
	{
	    r.mainMenu ();
	    if (r.count == 0)
		r.roulette ();
	    else if (r.count == 1)
		r.instructions ();
	    else if (r.count == 2)
		r.highScore ();
	    else if (r.count == 3)
		break;
	}


	r.goodBye ();
    }
}


