//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class ParkingFury implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image bobbiePic;
    public Image SpotPic;
    public Image level1Pic;
//    public Image tomPic;

    //Declare the character objects
    //   public Cars Scene;
    public Cars bobbie;
    public Player user;
    public parkingSpot spot1;
    public Rectangle box;
    public Image StartingPic;
    public boolean startscreen = true;
    public Image birdPic;
    public Bird[] bird;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        ParkingFury myApp = new ParkingFury();   //creates a new instance of the game
        new Thread(myApp).start();

        //  while (true) {
        //   System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + "," + "(" + MouseInfo.getPointerInfo().getLocation().y + ",");
        //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public ParkingFury() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        bobbiePic = Toolkit.getDefaultToolkit().getImage("bobbie.png");
        birdPic = Toolkit.getDefaultToolkit().getImage("bird.png");
        SpotPic = Toolkit.getDefaultToolkit().getImage("Spot.jpg");
        StartingPic = Toolkit.getDefaultToolkit().getImage("Start.jpg");
        level1Pic = Toolkit.getDefaultToolkit().getImage("level1.jpg");

        // tomPic = Toolkit.getDefaultToolkit().getImage("tomCat.png");

        //create (construct) the objects needed for the game
        // mouse1 = new Mouse(200, 300, 4, 4, mousePic);
        bobbie = new Cars(400, 300);
       // bird = new Cars(300, 400);
        spot1 = new parkingSpot(705, 70);
//        ScenePic = new Scene (250, 250, 0, 0, ScenePic);
        bird = new Bird[10];

        for (int i=0; i < bird.length; i++) {
            bird[i] = new Bird((int) (Math.random() *1000), (int) (Math.random() *700), birdPic);

          //  int x = (int) (Math.random() *1000);
//            int y = (int) (Math.random() *700);
//            bird[i].xpos=x;
//            bird[i].ypos=y;


            int  x1 = (int) (Math.random() *4);
            int  y1 = (int) (Math.random() *4);
            bird[i].dx=x1;
            bird[i].dy=y1;

            bird[i].pic= birdPic;
            bird[i].isAlive = true;

            bird[i].printInfo();

        }


    } // CheeseWorld()

    public void randombird(){
        for (int i=0; i< bird.length; i++) {
            int x = (int) (Math.random() *4);
            int y = (int) (Math.random() *4);
            bird[i].dx=x;
            bird[i].dy=y;

        }

    }


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        // mouse1.move();
        bobbie.move();
        for(int i=0; i<bird.length; i++) {
            bird[i].birdmove();
        }
        //  user.move();
    }

    public void checkIntersections() {

    }

    public void run() {
        while (true) {
moveThings();
boxforbobbie();
            render();
            //randombird();// paint the graphics
               pause(25); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
       // g.drawImage(SpotPic, 0, 0, WIDTH, HEIGHT, null);


        if (startscreen == true) {
            g.drawImage(StartingPic, 0, 0, WIDTH, HEIGHT, null);
        }
        else {
            g.drawImage(SpotPic, 0, 0, WIDTH, HEIGHT, null);

//        for (int i=0; i< bird.length; i++) {
//            if (bobbie.rec.intersects(bird[i].rec)) {
//              //  bobbie.isAlive = false;
//                System.out.println("bobbie is dead");
//            }
//        }

            if (bobbie.isAlive == false) {
                g.drawImage(level1Pic, 0, 0, WIDTH, HEIGHT, null);
            }


            //draw characters to the screen
            //  g.drawImage(mouse1.pic, mouse1.xpos, mouse1.ypos, mouse1.width, mouse1.height, null);
            g.drawImage(bobbiePic, bobbie.xpos, bobbie.ypos, bobbie.width, bobbie.height, null);
            g.drawRect(bobbie.rec.x, bobbie.rec.y, bobbie.rec.width, bobbie.rec.height);
            for (int i = 0; i < bird.length; i++) {
                if (bird[i].isAlive == true) {
                    g.drawImage(bird[i].pic, bird[i].xpos, bird[i].ypos, bird[i].width, bird[i].height, null);
                    g.drawRect(bird[i].rec.x, bird[i].rec.y, bird[i].rec.width, bird[i].rec.height);
                    //  System.out.println(bird[i].xpos);
                    //W g.fillRect();

                }
            }
                g.drawRect(spot1.xpos, spot1.ypos, spot1.width, spot1.height);



        }
        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            bobbie.right = true;
        }
        if (keyCode == 65) { // a
            bobbie.left = true;
        }

        if (keyCode == 83) { // s
            bobbie.down = true;
        }
        if (keyCode == 87) { // w
            bobbie.up = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            bobbie.right = false;
        }
        if (keyCode == 65) { // a
            bobbie.left = false;
        }
        if (keyCode == 83) { // s
            bobbie.down = false;
        }
        if (keyCode == 87) { // w
            bobbie.up = false;
        }
        if (keyCode == 32) {
            startscreen = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    public void boxforbobbie() {
        box = new Rectangle(689, 135, 100, 100);
        if (bobbie.rec.intersects(box)) {
            System.out.println("bobbie is in the box");
            bobbie.isAlive = false;
            System.out.println("level1.jpg");
            System.out.println("boolean"+bobbie.isAlive);
            System.out.println("boolean start"+startscreen);


        }
     for(int i=0;i<bird.length;i++) {
          if (bobbie.rec.intersects(bird[i].rec))
          {
              System.out.println("bobbie is dead");
              bobbie.isAlive = false;

          }
     }
    }
        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time){
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {


            } finally {

            }
        }
}
//class
