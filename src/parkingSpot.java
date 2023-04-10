import java.awt.*;

public class parkingSpot {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;

    public parkingSpot(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        width = 130;
        height = 170;
        dx = 0;
        dy = 0;
        isAlive = true;

        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


}
