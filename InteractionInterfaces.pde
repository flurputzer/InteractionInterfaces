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

DropdownList d11;
DropdownList d12;

DropdownList d21;
DropdownList d22;

DropdownList d31;
DropdownList d32;

DropdownList d41;
DropdownList d42;

DropdownList d51;
DropdownList d52;

int myColor = color(255);

int c1,c2;

float n,n1;


PFont f;
int recPort = 8009;
int sendPort = 8008;

String wekinatorProjectName = "Project_1";

void setup() {

  prepareExitHandler();
  
  size(440,280);
  noStroke();
  cp5 = new ControlP5(this);
  
  cp5.addTextlabel("label")
                    .setText("Use WekiMiniRunner to save Project \n -> File -> Save")
                    .setPosition(10,230)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
  
  // create a new button with name 'buttonA'
  cp5.addButton("Record_output1")
     .setValue(1)
     .setPosition(10,10)
     .setSize(200,19)
     ;
     
  cp5.addTextlabel("label2")
                    .setText("default: swipe right")
                    .setPosition(10,28)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
  
  // and add another 2 buttons
  cp5.addButton("Record_output2")
     .setValue(2)
     .setPosition(10,50)
     .setSize(200,19)
     ;
     
  cp5.addTextlabel("label3")
                    .setText("default: swipe left")
                    .setPosition(10,68)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
     
  cp5.addButton("Record_output3")
     .setValue(3)
     .setPosition(10,90)
     .setSize(200,19)
     ;
  
  cp5.addTextlabel("label4")
                    .setText("default: open palm")
                    .setPosition(10,108)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
  
  cp5.addButton("Record_output4")
     .setValue(4)
     .setPosition(10,130)
     .setSize(200,19)
     ;
     
  cp5.addTextlabel("label5")
                    .setText("default: close palm")
                    .setPosition(10,148)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
  
  cp5.addButton("Record_output5")
     .setValue(5)
     .setPosition(10,170)
     .setSize(200,19)
     ;
     
   cp5.addTextlabel("label6")
                    .setText("default: thumbs up")
                    .setPosition(10,188)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",10))
                    ;
     
  cp5.addButton("Stop_Recording")
     .setValue(0)
     .setPosition(10,210)
     .setSize(200,19)
     ;

 cp5.addButton("Show_Video")
     .setValue(128)
     .setPosition(220,210)
     .setSize(100,19)
     ;
     
  cp5.addButton("Hide_Video")
     .setValue(128)
     .setPosition(330,210)
     .setSize(100,19)
     ;
 
  cp5.addButton("Start")
     .setValue(128)
     .setPosition(160,250)
     .setSize(50,19)
     ;
     
  cp5.addButton("Stop")
     .setValue(128)
     .setPosition(220,250)
     .setSize(50,19)
     ;
  
  
  // create a DropdownList
  d11 = cp5.addDropdownList("myList-d11")
          .setPosition(220, 10)
          ;
   
  customize(d11,24); // customize the first list  
  
  d12 = cp5.addDropdownList("myList-d12")
          .setPosition(325, 10)
          ;
          
  customize(d12,105); // customize the first list
  
  // create a DropdownList
  d21 = cp5.addDropdownList("myList-d21")
          .setPosition(220, 50)
          ;
   
  customize(d21,24); // customize the first list  
  
  d22 = cp5.addDropdownList("myList-d22")
          .setPosition(325, 50)
          ;
          
  customize(d22,105); // customize the first list
  
  // create a DropdownList
  d31 = cp5.addDropdownList("myList-d31")
          .setPosition(220, 90)
          ;
   
  customize(d31,24); // customize the first list  
  
  d32 = cp5.addDropdownList("myList-d32")
          .setPosition(325, 90)
          ;
          
  customize(d32,105); // customize the first list
  
  // create a DropdownList
  d41 = cp5.addDropdownList("myList-d41")
          .setPosition(220, 130)
          ;
   
  customize(d41,24); // customize the first list  
  
  d42 = cp5.addDropdownList("myList-d42")
          .setPosition(325, 130)
          ;
          
  customize(d42,105); // customize the first list
  
  // create a DropdownList
  d51 = cp5.addDropdownList("myList-d51")
          .setPosition(220, 170)
          ;
   
  customize(d51,24); // customize the first list  
  
  d52 = cp5.addDropdownList("myList-d52")
          .setPosition(325, 170)
          ;
          
  customize(d52,105); // customize the first list
  
  
  //launch Handpose-osc (light) and Wekinator with preloaded project:
  //https://yoyling.herokuapp.com/https/github.com/gonski/HandPose-OSC
  //https://github.com/brannondorsey/wekimini
  sketchPath = sketchPath();
  
  //!!! change this before bundling to .app
  //sketchPath = sketchPath + "/InteractionInterfaces.app/Contents/MacOS";
  
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
      robot.setAutoDelay(250);
  } catch (AWTException e) {
    e.printStackTrace();
    exit();
  }



}

void customize(DropdownList ddl, int defaultValue) {
  // a convenience function to customize a DropdownList
  ddl.setBackgroundColor(color(190));
  ddl.setItemHeight(20);
  ddl.setBarHeight(15);
  ddl.setOpen(false);
  ddl.setDefaultValue(defaultValue);
 
  //ddl.scroll(0);
  ddl.setCaptionLabel("" + defaultValue);
  //ddl.captionLabel().style().marginTop = 3;
  //ddl.captionLabel().style().marginLeft = 3;
  //ddl.valueLabel().style().marginTop = 3;
  //for (int i=0;i<27;i++) {
    //ddl.addItem("item "+i, i);
ddl.addItem("VK_0", 0);
ddl.addItem("VK_1", 1);
ddl.addItem("VK_2", 2); 
ddl.addItem("VK_3", 3); 
ddl.addItem("VK_4", 4); 
ddl.addItem("VK_5", 5); 
ddl.addItem("VK_6", 6); 
ddl.addItem("VK_7", 7); 
ddl.addItem("VK_8", 8); 
ddl.addItem("VK_9", 9); 
ddl.addItem("VK_A", 10);
ddl.addItem("VK_B", 11); 
ddl.addItem("VK_BACK_QUOTE", 12); 
ddl.addItem("VK_BACK_SLASH", 13);
ddl.addItem("VK_BACK_SPACE", 14); 
ddl.addItem("VK_BRACELEFT", 14); 
ddl.addItem("VK_BRACERIGHT", 15); 
ddl.addItem("VK_C", 16); 
ddl.addItem("VK_CANCEL", 17); 
ddl.addItem("VK_CAPS_LOCK", 18); 
ddl.addItem("VK_CIRCUMFLEX", 19);
ddl.addItem("VK_CLEAR", 20); 
ddl.addItem("VK_CLOSE_BRACKET", 21);
ddl.addItem("VK_COLON", 22);
ddl.addItem("VK_COMMA", 23);
ddl.addItem("VK_CONTROL", 24); 
ddl.addItem("VK_D", 25); 
ddl.addItem("VK_DELETE", 26); 
ddl.addItem("VK_DOLLAR", 27);
ddl.addItem("VK_DOWN", 28);
ddl.addItem("VK_E", 29); 
ddl.addItem("VK_END", 30); 
ddl.addItem("VK_ENTER", 31); 
ddl.addItem("VK_EQUALS", 32);
ddl.addItem("VK_ESCAPE", 33); 
ddl.addItem("VK_EURO_SIGN", 34);
ddl.addItem("VK_EXCLAMATION_MARK", 35);
ddl.addItem("VK_F", 36); 
ddl.addItem("VK_F1", 37);
ddl.addItem("VK_F10", 38);
ddl.addItem("VK_F11", 39);
ddl.addItem("VK_F12", 40);
ddl.addItem("VK_F13", 41);
ddl.addItem("VK_F14", 42);
ddl.addItem("VK_F15", 43);
ddl.addItem("VK_F16", 44);
ddl.addItem("VK_F17", 45);
ddl.addItem("VK_F18", 46);
ddl.addItem("VK_F19", 47);
ddl.addItem("VK_F2", 48);
ddl.addItem("VK_F20", 49);
ddl.addItem("VK_F21", 50);
ddl.addItem("VK_F22", 51);
ddl.addItem("VK_F23", 52);
ddl.addItem("VK_F24", 53);
ddl.addItem("VK_F3", 54);
ddl.addItem("VK_F4", 55);
ddl.addItem("VK_F5", 56);
ddl.addItem("VK_F6", 57);
ddl.addItem("VK_F7", 58);
ddl.addItem("VK_F8", 59);
ddl.addItem("VK_F9", 60);
ddl.addItem("VK_G", 61);
ddl.addItem("VK_GREATER", 62); 
ddl.addItem("VK_H", 63); 
ddl.addItem("VK_HOME", 64); 
ddl.addItem("VK_I", 65); 
ddl.addItem("VK_INSERT", 66); 
ddl.addItem("VK_J", 67); 
ddl.addItem("VK_K", 68); 
ddl.addItem("VK_KP_DOWN", 69);
ddl.addItem("VK_KP_LEFT", 70);
ddl.addItem("VK_KP_RIGHT", 71);
ddl.addItem("VK_KP_UP", 72);
ddl.addItem("VK_L", 73); 
ddl.addItem("VK_LEFT", 74);
ddl.addItem("VK_LEFT_PARENTHESIS", 75);
ddl.addItem("VK_LESS", 76); 
ddl.addItem("VK_M", 77); 
ddl.addItem("VK_MINUS", 78);
ddl.addItem("VK_MULTIPLY", 79); 
ddl.addItem("VK_N", 80); 
ddl.addItem("VK_NUM_LOCK", 81); 
ddl.addItem("VK_NUMBER_SIGN", 82);
ddl.addItem("VK_NUMPAD0", 83); 
ddl.addItem("VK_NUMPAD1", 84); 
ddl.addItem("VK_NUMPAD2", 85); 
ddl.addItem("VK_NUMPAD3", 86); 
ddl.addItem("VK_NUMPAD4", 87); 
ddl.addItem("VK_NUMPAD5", 88); 
ddl.addItem("VK_NUMPAD6", 89); 
ddl.addItem("VK_NUMPAD7", 90); 
ddl.addItem("VK_NUMPAD8", 91); 
ddl.addItem("VK_NUMPAD9", 92); 
ddl.addItem("VK_O", 93); 
ddl.addItem("VK_OPEN_BRACKET", 94); 
ddl.addItem("VK_P", 95);  
ddl.addItem("VK_PAGE_DOWN", 96); 
ddl.addItem("VK_PAGE_UP", 97); 
ddl.addItem("VK_PASTE", 98); 
ddl.addItem("VK_PAUSE", 99); 
ddl.addItem("VK_PERIOD", 100);
ddl.addItem("VK_PLUS", 101);
ddl.addItem("VK_Q", 102); 
ddl.addItem("VK_QUOTE", 103); 
ddl.addItem("VK_R", 104); 
ddl.addItem("VK_RIGHT", 105);
ddl.addItem("VK_RIGHT_PARENTHESIS", 106);
ddl.addItem("VK_S", 107); 
ddl.addItem("VK_SEMICOLON", 108);
ddl.addItem("VK_SEPARATOR", 109);
ddl.addItem("VK_SHIFT", 110); 
ddl.addItem("VK_SLASH", 111);
ddl.addItem("VK_SPACE", 112); 
ddl.addItem("VK_SUBTRACT", 113); 
ddl.addItem("VK_T", 114); 
ddl.addItem("VK_TAB", 115); 
ddl.addItem("VK_U", 116); 
ddl.addItem("VK_UNDERSCORE", 117);
ddl.addItem("VK_UP", 118);
ddl.addItem("VK_V", 119); 
ddl.addItem("VK_W", 120); 
ddl.addItem("VK_X", 121); 
ddl.addItem("VK_Y", 122); 
ddl.addItem("VK_Z", 123);

  
  ddl.updateItemIndexOffset(); 
  
  
 
  
  
  ddl.setColorBackground(color(60));
  ddl.setColorActive(color(255, 128));
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
    println("output 1.");
          robot.keyPress(KeyEvent.VK_META);
          robot.keyPress(KeyEvent.VK_TAB);
          robot.keyRelease(KeyEvent.VK_META);
          robot.keyRelease(KeyEvent.VK_TAB);
         
  }else if(theOscMessage.checkAddrPattern("/output_2")==true){
    println("output 2.");
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_UP);
          robot.keyRelease(KeyEvent.VK_CONTROL);
  }else if(theOscMessage.checkAddrPattern("/output_3")==true){
    println("output 3.");
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_RIGHT);
          robot.keyRelease(KeyEvent.VK_RIGHT);
          robot.keyRelease(KeyEvent.VK_CONTROL);
  }else if(theOscMessage.checkAddrPattern("/output_4")==true){
    println("output 4.");
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_CONTROL);
        }else if(theOscMessage.checkAddrPattern("/output_5")==true){
    println("output 5.");
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_Y);
          robot.keyRelease(KeyEvent.VK_CONTROL);
        }else{
    //println(" addrpattern: "+theOscMessage.addrPattern());
     // println(" typetag: "+theOscMessage.typetag());
  }
}
