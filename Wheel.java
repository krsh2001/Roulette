// The "Wheel" class.
import java.awt.*;
import hsa.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Wheel
{
    static Console c;           // The output console
    public Color red = new Color (163, 35, 31);
    public Color green = new Color (10, 108, 3);
    public Color brown = new Color (193, 102, 11);
    int count = 5;
    int tempAngle[] = new int [38];
    public void rWheel ()
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	// the starting angle of the rotation
	int startAngle = 0;
	// the ending angle
	int endAngle = 720;
	// the x radius of the arc that the object moves on
	int xrad = 135;
	// the yradius of the arc
	int yrad = 135;
	// the radius of the moving text
	int ballRadius = 20;
	// the x coordinate of the center of the arc
	int x = 5 + (c.maxx () / 2);
	// the y of the arc center
	int y = 35 + (c.maxy () / 2);

	tempAngle [0] = 9;
	for (int i = 1 ; i < 37 ; i++)
	{
	    tempAngle [i] = tempAngle [0] * i;
	}
	for (int angle = startAngle ; angle < endAngle ; angle++)
	{
	    // calculates the angle in radians
	    final double angleInRadians = 2 * Math.PI * angle / 360;
	    // gets the x of the object on the arc using cos
	    double textX = x + xrad * Math.cos (angleInRadians);
	    // gets the y of the object on the arc using sin
	    double textY = y + yrad * Math.sin (angleInRadians);
	    // gets the x of the object on the arc using cos
	    double circleX = x + xrad * Math.cos (angleInRadians);
	    // gets the y of the object on the arc using sin
	    double circleY = y + yrad * Math.sin (angleInRadians);
	    c.setColor (Color.red);
	    c.fillArc (175, 130, 294, 294, 260 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 279 - angle, 8);
	    c.fillArc (175, 130, 294, 294, 298 - angle, 8);
	    c.fillArc (175, 130, 294, 294, 316 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 336 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 353 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 374 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 393 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 413 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 432 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 451 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 471 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 509 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 527 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 545 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 565 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 583 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 602 - angle, 9);
	    c.setColor (green);
	    c.fillArc (175, 130, 294, 294, 306 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 490 - angle, 10);
	    c.setColor (Color.black);
	    c.fillArc (175, 130, 294, 294, 269 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 287 - angle, 11);
	    c.fillArc (175, 130, 294, 294, 325 - angle, 11);
	    c.fillArc (175, 130, 294, 294, 344 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 363 - angle, 11);
	    c.fillArc (175, 130, 294, 294, 383 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 403 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 422 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 441 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 461 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 480 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 500 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 518 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 536 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 555 - angle, 10);
	    c.fillArc (175, 130, 294, 294, 573 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 592 - angle, 9);
	    c.fillArc (175, 130, 294, 294, 610 - angle, 9);
	    c.setColor (brown);
	    c.fillOval (215, 175, 210, 210);
	    c.setColor (red);
	    c.fillOval (245, 205, 150, 150);
	    c.setColor (Color.black);
	    c.fillOval (310, 265, 25, 25);
	    c.setColor (Color.white);
	    c.drawOval (199, 158, 242, 242);
	    //draw the ball
	    c.drawString ("1", (int) Math.round (textX), (int) Math.round (textY));
	    // draw the ball
	    //c.fillOval ((int) Math.round (circleX), (int) Math.round (circleY), ballRadius, ballRadius);
	    try
	    {
		Thread.sleep (count);
	    }
	    catch (Exception e)
	    {
	    }
	    if (angle % 20 == 0)
		count++;
	}
    }


    public static void main (String[] args)
    {
	c = new Console ();
	Wheel w = new Wheel ();
	w.rWheel ();
    } // main method
} // Wheel class
