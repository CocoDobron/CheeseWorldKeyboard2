import java.awt.*;

public class Bird {

    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rec;
    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;
    public Image pic;

    public int maxWidth = 150;


    public Bird(int pXpos, int pYpos, Image picParameter) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 60;
        height = 60;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move1() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        // bounce off left and right sides
        if (xpos > 1000) {
            dy = dy;
            dx = -dx;
        }
        if (xpos < 0) {
            dy = dy;
            dx = -dx;

        }
        if (ypos > 700) {
            dx = dx;
            dy = -dy;
        }
        if (ypos < 0) {
            dx = dx;
            dy = -dy;

        }
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void printInfo() {
        System.out.println("the (x,y), position of my sun is: " + xpos + "," + ypos);
        System.out.println("the x speed is " + dx + " and the y speed is " + dy);
        System.out.println("the width of my sun is " + width + " and the height of my sun is " + height);
        System.out.println("isAlive=" + isAlive);
    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (right == true) {
            dx = 2;
        } else if (left == true) {
            dx = -2;
        } else { // (right == false && left == false)
            dx = 0;
        }

        if (down == true) {
            dy = 2;
        } else if (up == true) {
            dy = -2;
        } else {
            dy = 0;
        }

        if (xpos > 1000 - width) { // right
            xpos = 1000 - width;
        }
        if (xpos < 0) { // left
            xpos = 0;
        }
        if (ypos > 650 - height) { // down
            ypos = 650 - height;
        }
        if (ypos < 0) { // up
            ypos = 0;
        }
        // if(ypos <



        //always put this after you've done all the changing of the xpos and ypos values
        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void birdmove(){
        xpos=xpos+dx;
        if(xpos<0) {
            xpos = 1000;
        }
        if(xpos>1000-width) {
            xpos = 0;
        }
        ypos=ypos+dy;
        if(ypos<0) {
            ypos = 700;
        }
        if(ypos>700-height) {
            ypos = 0;
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }


}






