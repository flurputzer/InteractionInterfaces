/**
* REALLY simple processing sketch that sends mouse x and y position to wekinator
* This sends 2 input values to port 6448 using message /wek/inputs
**/

import oscP5.*;
import netP5.*;

import controlP5.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;





OscP5 oscP5;
//NetAddress dest;
NetAddress myRemoteLocation;

Robot robot;

String firstKey; 
String secondKey;
String thirdKey;


ControlP5 cp5;

int myColor = color(255);

int c1,c2;

float n,n1;


PFont f;
int recPort = 9000;
int sendPort = 6448;

void setup() {
  
  prepareExitHandler();
  
  size(400,600);
  noStroke();
  cp5 = new ControlP5(this);
  
  // create a new button with name 'buttonA'
  cp5.addButton("colorA")
     .setValue(0)
     .setPosition(100,100)
     .setSize(200,19)
     ;
  
  // and add another 2 buttons
  cp5.addButton("colorB")
     .setValue(100)
     .setPosition(100,120)
     .setSize(200,19)
     ;
     
  cp5.addButton("colorC")
     .setPosition(100,140)
     .setSize(200,19)
     .setValue(0)
     ;

  PImage[] imgs = {loadImage("button_a.png"),loadImage("button_b.png"),loadImage("button_c.png")};
  cp5.addButton("play")
     .setValue(128)
     .setPosition(140,300)
     .setImages(imgs)
     .updateSize()
     ;
     
  cp5.addButton("playAgain")
     .setValue(128)
     .setPosition(210,300)
     .setImages(imgs)
     .updateSize()
     ;
  
  
  
  //launch Handpose-osc and Wekinator
  String sketchPath = sketchPath();
  
  launch(sketchPath + "/HandPose-OSC.app");
  launch(sketchPath + "/Wekinator.app");
  
  
  /* start oscP5, listening for incoming messages at port 8007 */
  oscP5 = new OscP5(this,8007);
  myRemoteLocation = new NetAddress("127.0.0.1",6448);
  firstKey = "VK_CONTROL";
  secondKey = "VK_UP";
  thirdKey = "empty";
    try { 
    robot = new Robot();
  } catch (AWTException e) {
    e.printStackTrace();
    exit();
  }



}

void stop() {
  exec("osascript -e \'quit app \"HandPose-OSC\"\'");
   exec("osascript -e \'quit app \"Wekinator\"\'");
  println("test");
  
  
} 

private void prepareExitHandler() {

Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

public void run () {

System.out.println("SHUTDOWN HOOK");

   // application exit code here
   //exec("osascript -e 'quit app \"HandPose OSC\"'");
   //exec("osascript -e 'quit app \"Wekinator\"'");
  try {
    //exec("osascript -e 'quit app \"Wekinator\"'");
    exec("killall", "HandPose-OSC");
    exec("killall", "Wekinator");
  }
  catch(Exception e) {
    e.printStackTrace();
    
    
  }
}

}));

}

void draw() {
    //sendOsc();
    
    background(myColor);
  myColor = lerpColor(c1,c2,n);
  n += (1-n)* 0.1; 
 
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());
  n = 0;
}

// function colorA will receive changes from 
// controller with name colorA
public void colorA(int theValue) {
  println("a button event from colorA: "+theValue);
  c1 = c2;
  c2 = color(0,160,100);
}

// function colorB will receive changes from 
// controller with name colorB
public void colorB(int theValue) {
  println("a button event from colorB: "+theValue);
  c1 = c2;
  c2 = color(150,0,0);
}

// function colorC will receive changes from 
// controller with name colorC
public void colorC(int theValue) {
  println("a button event from colorC: "+theValue);
  c1 = c2;
  c2 = color(255,255,0);
}

public void play(int theValue) {
  println("a button event from buttonB: "+theValue);
  c1 = c2;
  c2 = color(0,0,0);
}

void sendOsc() {
  OscMessage msg = new OscMessage("/wek/inputs");
  msg.add((float)mouseX); 
  msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
}

/* incoming osc message are forwarded to the oscEvent method. */
void oscEvent(OscMessage theOscMessage) {
  /* print the address pattern and the typetag of the received OscMessage */
  if(theOscMessage.checkAddrPattern("/output_1")==true){
    println("### FUCKFUCKFUNK.");
          robot.setAutoDelay(250);
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_CONTROL);
  }else if(theOscMessage.checkAddrPattern("/output_2")==true){
    println("output 2.");
          robot.setAutoDelay(250);
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_CONTROL);
  }else if(theOscMessage.checkAddrPattern("/output_3")==true){
    println("output 3.");
          robot.setAutoDelay(250);
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_RIGHT);
          robot.keyRelease(KeyEvent.VK_RIGHT);
          robot.keyRelease(KeyEvent.VK_CONTROL);
  }else if(theOscMessage.checkAddrPattern("/output_4")==true){
    println("output 4.");
          robot.setAutoDelay(250);
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_CONTROL);
        }else if(theOscMessage.checkAddrPattern("/output_5")==true){
    println("output 5.");
          robot.setAutoDelay(250);
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_CONTROL);
        }else{
    //println(" addrpattern: "+theOscMessage.addrPattern());
     // println(" typetag: "+theOscMessage.typetag());
  }
}
