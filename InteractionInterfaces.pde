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

String sketchPath;


ControlP5 cp5;

int myColor = color(255);

int c1,c2;

float n,n1;


PFont f;
int recPort = 8007;
int sendPort = 8008;

String wekinatorProjectName = "Project_1";

void setup() {
  
  prepareExitHandler();
  
  size(430,600);
  noStroke();
  cp5 = new ControlP5(this);
  
  // create a new button with name 'buttonA'
  cp5.addButton("Record_output1")
     .setValue(1)
     .setPosition(10,10)
     .setSize(200,19)
     ;
  
  // and add another 2 buttons
  cp5.addButton("Record_output2")
     .setValue(2)
     .setPosition(10,30)
     .setSize(200,19)
     ;
     
  cp5.addButton("Record_output3")
     .setValue(3)
     .setPosition(10,50)
     .setSize(200,19)
     ;
  
  cp5.addButton("Record_output4")
     .setValue(4)
     .setPosition(10,70)
     .setSize(200,19)
     ;
  
  cp5.addButton("Record_output5")
     .setValue(5)
     .setPosition(10,90)
     .setSize(200,19)
     ;
     
  cp5.addButton("Stop_Recording")
     .setValue(0)
     .setPosition(10,120)
     .setSize(200,19)
     ;

 cp5.addButton("Show_Video")
     .setValue(128)
     .setPosition(220,10)
     .setSize(200,19)
     ;
     
  cp5.addButton("Hide_Video")
     .setValue(128)
     .setPosition(220,30)
     .setSize(200,19)
     ;
 
  cp5.addButton("Start")
     .setValue(128)
     .setPosition(140,300)
     .updateSize()
     ;
     
  cp5.addButton("Stop")
     .setValue(128)
     .setPosition(210,300)
     .updateSize()
     ;
  
  
  
  //launch Handpose-osc (light) and Wekinator with preloaded project:
  //https://yoyling.herokuapp.com/https/github.com/gonski/HandPose-OSC
  //https://github.com/brannondorsey/wekimini
  sketchPath = sketchPath();
  
  launch(sketchPath + "/HandPose-OSC.app");
  //exec("java", "-jar", sketchPath + "/Wekinator/WekiMini.jar", sketchPath + "/WekinatorProjects" +"/" + wekinatorProjectName + "/" + wekinatorProjectName + ".wekproj");
  exec("java", "-jar", sketchPath + "/Wekinator/WekiMini.jar", sketchPath + "/WekinatorProjects" +"/" + wekinatorProjectName + "/" + wekinatorProjectName + ".wekproj");

   //exec("osascript" ,"-e", "\'tell application \\\"System Events\\\" to set visible of process \\\"WekiMiniRunner\\\" to false\'");
  //launch(sketchPath + "/code/hideWekinator.scpt");
  // hide wekinator
  //exec("osascript" ,"-e", "\'tell application \"System Events\" to set visible of process \"WekiMiniRunner\" to false\'");
  //String script = "tell application \"System Events\" to set visible of process \"WekiMiniRunner\" to false";
  
  
  /* start oscP5, listening for incoming messages at port 8007 */
  oscP5 = new OscP5(this,recPort);
  myRemoteLocation = new NetAddress("127.0.0.1",sendPort);
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
    exec("killall", "java");
  }
  catch(Exception e) {
    e.printStackTrace();
    
    
  }
}

}));

}

public void startRecording(int i) {
  OscMessage msg = new OscMessage("/wekinator/control/startDtwRecording");
  msg.add(i); 
  //msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
}

public void stopRecording() {
  OscMessage msg = new OscMessage("/wekinator/control/stopDtwRecording"); 
  //msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
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
public void Record_output1(int theValue) {
  println("a button event from colorA: "+theValue);
  //c1 = c2;
  //c2 = color(0,160,100);
  startRecording(1);
  
}

public void Record_output2(int theValue) {
  println("a button event from colorA: "+theValue);
  //c1 = c2;
  //c2 = color(0,160,100);
  startRecording(2);
  
}

public void Record_output3(int theValue) {
  println("a button event from colorA: "+theValue);
  //c1 = c2;
  //c2 = color(0,160,100);
  startRecording(3);
  
}

public void Record_output4(int theValue) {
  println("a button event from colorA: "+theValue);
  //c1 = c2;
  //c2 = color(0,160,100);
  startRecording(4);
  
}

public void Record_output5(int theValue) {
  println("a button event from colorA: "+theValue);
  //c1 = c2;
  //c2 = color(0,160,100);
  startRecording(5);
  
}

// function colorB will receive changes from 
// controller with name colorB
public void Stop_Recording(int theValue) {
  //println("a button event from colorB: "+theValue);
  //c1 = c2;
  //c2 = color(150,0,0);
  stopRecording();
}


public void Show_Video() {
  exec("killall", "HandPose-OSC");
  launch(sketchPath + "/HandPose-OSC_large.app");
}

public void Hide_Video() {
  exec("killall", "HandPose-OSC");
  launch(sketchPath + "/HandPose-OSC.app");
}

public void Start() {
  OscMessage msg = new OscMessage("/wekinator/control/startRunning"); 
  //msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
}

public void Stop() {
  OscMessage msg = new OscMessage("/wekinator/control/stopRunning"); 
  //msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
}




void sendOsc(String text) {
  OscMessage msg = new OscMessage("/wek/inputs");
  msg.add(text); 
  //msg.add((float)mouseY);
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
