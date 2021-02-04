import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 
import controlP5.*; 
import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.KeyEvent; 

import netP5.*; 
import oscP5.*; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class InteractionInterfaces extends PApplet {

/**
* 
*
**/














OscP5 oscP5;
//NetAddress dest;
NetAddress myRemoteLocation;

Robot robot;


String firstKey; 
String secondKey;
String thirdKey;

String sketchPath;

String[] Keys;

int[][] defaultValues = { {25, 106}, //ctrl + RIGHT
                          {25, 75},//ctrl + LEFT
                          {25, 119},//ctrl + UP 
                          {25, 29},//ctrl + DOWN 
                          {125, 40},//NONE + F11 
                        };



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

public void setup() {

  prepareExitHandler();
  populateKeys();
  
  
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
  d51 = cp5.addDropdownList("myList-d51")
          .setPosition(220, 170)
          ;
   
  customize(d51,defaultValues[4][0]); // customize the first list  
  
  d52 = cp5.addDropdownList("myList-d52")
          .setPosition(325, 170)
          ;
          
  customize(d52,defaultValues[4][1]); // customize the first list
  
     // create a DropdownList
  d41 = cp5.addDropdownList("myList-d41")
          .setPosition(220, 130)
          ;
   
  customize(d41,defaultValues[3][0]); // customize the first list  
  
  d42 = cp5.addDropdownList("myList-d42")
          .setPosition(325, 130)
          ;
          
  customize(d42,defaultValues[3][1]); // customize the first list

   
  // create a DropdownList
  d31 = cp5.addDropdownList("myList-d31")
          .setPosition(220, 90)
          ;
   
  customize(d31,defaultValues[2][0]); // customize the first list  
  
  d32 = cp5.addDropdownList("myList-d32")
          .setPosition(325, 90)
          ;
          
  customize(d32,defaultValues[2][1]); // customize the first list 
   
      // create a DropdownList
  d21 = cp5.addDropdownList("myList-d21")
          .setPosition(220, 50)
          ;
  customize(d21,defaultValues[1][0]); // customize the first list  
  
  d22 = cp5.addDropdownList("myList-d22")
          .setPosition(325, 50)
          ;
          
  customize(d22,defaultValues[1][1]); // customize the first list
  
 
  // create a DropdownList
  d11 = cp5.addDropdownList("myList-d11")
          .setPosition(220, 10)
          ;
   
  customize(d11,defaultValues[0][0]); // customize the first list  
  
  d12 = cp5.addDropdownList("myList-d12")
          .setPosition(325, 10)
          ;
          
  customize(d12,defaultValues[0][1]); // customize the first list
  


  

  

  
  
  //launch Handpose-osc (light) and Wekinator with preloaded project:
  //https://yoyling.herokuapp.com/https/github.com/gonski/HandPose-OSC
  //https://github.com/brannondorsey/wekimini
  sketchPath = sketchPath();
  
  //!!! change this before bundling to .app
  sketchPath = sketchPath + "/InteractionInterfaces.app/Contents/MacOS";
  
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

public void customize(DropdownList ddl, int defaultValue) {
  // a convenience function to customize a DropdownList
  ddl.setBackgroundColor(color(190));
  ddl.setItemHeight(20);
  ddl.setBarHeight(15);
  ddl.setOpen(false);
  ddl.setDefaultValue(defaultValue);
 
  //ddl.scroll(0);
  ddl.setCaptionLabel(Keys[defaultValue]);
  //ddl.captionLabel().style().marginTop = 3;
  //ddl.captionLabel().style().marginLeft = 3;
  //ddl.valueLabel().style().marginTop = 3;
  for (int i=0;i<Keys.length;i++) {
    ddl.addItem(Keys[i], i);
  }
  
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
  
  //println("label:" + d42.getLabel());
}
  

public void draw() {
    //sendOsc();
    
    background(myColor);
  myColor = lerpColor(c1,c2,n);
  n += (1-n)* 0.1f; 
 
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
public void Stop_Recording() {
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


public void populateKeys() {
  Keys = new String[126];
Keys[0] = "VK_0";
Keys[1] = "VK_1";
Keys[2] = "VK_2"; 
Keys[3] = "VK_3"; 
Keys[4] = "VK_4";
Keys[5] = "VK_5"; 
Keys[6] = "VK_6"; 
Keys[7] = "VK_7"; 
Keys[8] = "VK_8"; 
Keys[9] = "VK_9"; 
Keys[10] = "VK_A";
Keys[11] = "VK_B"; 
Keys[12] = "VK_BACK_QUOTE";
Keys[13] = "VK_BACK_SLASH";
Keys[14] = "VK_BACK_SPACE"; 
Keys[15] = "VK_BRACELEFT"; 
Keys[16] = "VK_BRACERIGHT"; 
Keys[17] = "VK_C";
Keys[18] = "VK_CANCEL"; 
Keys[19] = "VK_CAPS_LOCK"; 
Keys[20] = "VK_CIRCUMFLEX";
Keys[21] = "VK_CLEAR";
Keys[22] = "VK_CLOSE_BRACKET";
Keys[23] = "VK_COLON";
Keys[24] = "VK_COMMA";
Keys[25] = "VK_CONTROL"; 
Keys[26] = "VK_D";
Keys[27] = "VK_DELETE"; 
Keys[28] = "VK_DOLLAR";
Keys[29] = "VK_DOWN";
Keys[30] = "VK_E";
Keys[31] = "VK_END"; 
Keys[32] = "VK_ENTER"; 
Keys[33] = "VK_EQUALS";
Keys[34] = "VK_ESCAPE"; 
Keys[35] = "VK_EURO_SIGN";
Keys[36] = "VK_EXCLAMATION_MARK";
Keys[37] = "VK_F";
Keys[38] = "VK_F1";
Keys[39] = "VK_F10";
Keys[40] = "VK_F11";
Keys[41] = "VK_F12";
Keys[42] = "VK_F13";
Keys[43] = "VK_F14";
Keys[44] = "VK_F15";
Keys[45] = "VK_F16";
Keys[46] = "VK_F17";
Keys[47] = "VK_F18";
Keys[48] = "VK_F19";
Keys[49] = "VK_F2";
Keys[50] = "VK_F20";
Keys[51] = "VK_F21";
Keys[52] = "VK_F22";
Keys[53] = "VK_F23";
Keys[54] = "VK_F24";
Keys[55] = "VK_F3";
Keys[56] = "VK_F4";
Keys[57] = "VK_F5";
Keys[58] = "VK_F6";
Keys[59] = "VK_F7";
Keys[60] = "VK_F8";
Keys[61] = "VK_F9";
Keys[62] = "VK_G";
Keys[63] = "VK_GREATER"; 
Keys[64] = "VK_H";
Keys[65] = "VK_HOME"; 
Keys[66] = "VK_I";
Keys[67] = "VK_INSERT"; 
Keys[68] = "VK_J";
Keys[69] = "VK_K"; 
Keys[70] = "VK_KP_DOWN";
Keys[71] = "VK_KP_LEFT";
Keys[72] = "VK_KP_RIGHT";
Keys[73] = "VK_KP_UP";
Keys[74] = "VK_L";
Keys[75] = "VK_LEFT";
Keys[76] = "VK_LEFT_PARENTHESIS";
Keys[77] = "VK_LESS";
Keys[78] = "VK_M";
Keys[79] = "VK_MINUS";
Keys[80] = "VK_MULTIPLY"; 
Keys[81] = "VK_N";
Keys[82] = "VK_NUM_LOCK"; 
Keys[83] = "VK_NUMBER_SIGN";
Keys[84] = "VK_NUMPAD0";
Keys[85] = "VK_NUMPAD1"; 
Keys[86] = "VK_NUMPAD2"; 
Keys[87] = "VK_NUMPAD3"; 
Keys[88] = "VK_NUMPAD4"; 
Keys[89] = "VK_NUMPAD5"; 
Keys[90] = "VK_NUMPAD6"; 
Keys[91] = "VK_NUMPAD7"; 
Keys[92] = "VK_NUMPAD8"; 
Keys[93] = "VK_NUMPAD9"; 
Keys[94] = "VK_O";
Keys[95] = "VK_OPEN_BRACKET"; 
Keys[96] = "VK_P";
Keys[97] = "VK_PAGE_DOWN"; 
Keys[98] = "VK_PAGE_UP";
Keys[99] = "VK_PASTE";
Keys[100] = "VK_PAUSE"; 
Keys[101] = "VK_PERIOD";
Keys[102] = "VK_PLUS";
Keys[103] = "VK_Q";
Keys[104] = "VK_QUOTE"; 
Keys[105] = "VK_R";
Keys[106] = "VK_RIGHT";
Keys[107] = "VK_RIGHT_PARENTHESIS";
Keys[108] = "VK_S";
Keys[109] = "VK_SEMICOLON";
Keys[110] = "VK_SEPARATOR";
Keys[111] = "VK_SHIFT";
Keys[112] = "VK_SLASH";
Keys[113] = "VK_SPACE";
Keys[114] = "VK_SUBTRACT"; 
Keys[115] = "VK_T";
Keys[116] = "VK_TAB"; 
Keys[117] = "VK_U"; 
Keys[118] = "VK_UNDERSCORE";
Keys[119] = "VK_UP";
Keys[120] = "VK_V"; 
Keys[121] = "VK_W"; 
Keys[122] = "VK_X"; 
Keys[123] = "VK_Y"; 
Keys[124] = "VK_Z";
Keys[125] = "NONE";

}






public void sendOsc(String text) {
  OscMessage msg = new OscMessage("/wek/inputs");
  msg.add(text); 
  //msg.add((float)mouseY);
  oscP5.send(msg, myRemoteLocation);
}

/* incoming osc message are forwarded to the oscEvent method. */
public void oscEvent(OscMessage theOscMessage) {
  /* print the address pattern and the typetag of the received OscMessage */
  if(theOscMessage.checkAddrPattern("/output_1")==true){
    println("output 1.");
          convertKeyPress(d11.getLabel());
          convertKeyPress(d12.getLabel());
          convertKeyRelease(d11.getLabel());
          convertKeyRelease(d12.getLabel());
          
  }else if(theOscMessage.checkAddrPattern("/output_2")==true){
    println("output 2.");
          convertKeyPress(d21.getLabel());
          convertKeyPress(d22.getLabel());
          convertKeyRelease(d21.getLabel());
          convertKeyRelease(d22.getLabel());
  }else if(theOscMessage.checkAddrPattern("/output_3")==true){
    println("output 3.");
          convertKeyPress(d31.getLabel());
          convertKeyPress(d32.getLabel());
          convertKeyRelease(d31.getLabel());
          convertKeyRelease(d32.getLabel());
  }else if(theOscMessage.checkAddrPattern("/output_4")==true){
    println("output 4.");
          convertKeyPress(d41.getLabel());
          convertKeyPress(d42.getLabel());
          convertKeyRelease(d41.getLabel());
          convertKeyRelease(d42.getLabel());
        }else if(theOscMessage.checkAddrPattern("/output_5")==true){
    println("output 5.");
          convertKeyPress(d51.getLabel());
          println(d51.getLabel());
          convertKeyPress(d52.getLabel());
          convertKeyRelease(d51.getLabel());
          convertKeyRelease(d52.getLabel());
        }else{
    //println(" addrpattern: "+theOscMessage.addrPattern());
     // println(" typetag: "+theOscMessage.typetag());
  }
}

public void convertKeyPress(String command) {
  switch (command) {
   case "VK_0":
   robot.keyPress(KeyEvent.VK_0);
   break;
   
   case "VK_1":
   robot.keyPress(KeyEvent.VK_1);
   break;
   
   case "VK_2":
   robot.keyPress(KeyEvent.VK_2);
   break;
   
   
   case "VK_3":
   robot.keyPress(KeyEvent.VK_3);
   break;
   
   case "VK_4":
   robot.keyPress(KeyEvent.VK_4);
   break;
   
   case "VK_5":
   robot.keyPress(KeyEvent.VK_5);
   break;
   
   case "VK_6":
   robot.keyPress(KeyEvent.VK_6);
   break;
   
   case "VK_7":
   robot.keyPress(KeyEvent.VK_7);
   break;
   
   case "VK_8":
   robot.keyPress(KeyEvent.VK_8);
   break;
   
   case "VK_9)":
   robot.keyPress(KeyEvent.VK_9);
   break;
   
   case "VK_A":
   robot.keyPress(KeyEvent.VK_A);
   break;
   
   case "VK_B":
   robot.keyPress(KeyEvent.VK_B);
   break;
   
   case "VK_BACK_QUOTE":
   robot.keyPress(KeyEvent.VK_BACK_QUOTE);
   break;
   
   case "VK_BACK_SLASH":
   robot.keyPress(KeyEvent.VK_BACK_SLASH);
   break;
   
   case "VK_BACK_SPACE":
   robot.keyPress(KeyEvent.VK_BACK_SPACE);
   break;
   
   case "VK_BRACELEFT":
   robot.keyPress(KeyEvent.VK_BRACELEFT);
   break;
   
   case "VK_BRACERIGHT":
   robot.keyPress(KeyEvent.VK_BRACERIGHT);
   break;
   
   case "VK_C":
   robot.keyPress(KeyEvent.VK_C);
   break;
   
   case "VK_CANCEL":
   robot.keyPress(KeyEvent.VK_CANCEL);
   break;
   
   case "VK_CAPS_LOCK":
   robot.keyPress(KeyEvent.VK_CAPS_LOCK);
   break;
   
   case "VK_CIRCUMFLEX":
   robot.keyPress(KeyEvent.VK_CIRCUMFLEX);
   break;
   
   case "VK_CLEAR":
   robot.keyPress(KeyEvent.VK_CLEAR);
   break;
   
   case "VK_CLOSE_BRACKET":
   robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
   break;
   
   case "VK_COLON":
   robot.keyPress(KeyEvent.VK_COLON);
   break;
   
   case "VK_COMMA":
   robot.keyPress(KeyEvent.VK_COMMA);
   break;
   
   case "VK_CONTROL":
   robot.keyPress(KeyEvent.VK_CONTROL);
   break;
   
   case "VK_D":
   robot.keyPress(KeyEvent.VK_D);
   break;
   
   case "VK_DELETE":
   robot.keyPress(KeyEvent.VK_DELETE);
   break;
   
   case "VK_DOLLAR":
   robot.keyPress(KeyEvent.VK_DOLLAR);
   break;
   
   case "VK_DOWN":
   robot.keyPress(KeyEvent.VK_DOWN);
   break;
   
   case "VK_E":
   robot.keyPress(KeyEvent.VK_E);
   break;
   
   case "VK_F1":
   robot.keyPress(KeyEvent.VK_F1);
   break;
   
   case "VK_F10":
   robot.keyPress(KeyEvent.VK_F10);
   break;
   
   case "VK_F11":
   robot.keyPress(KeyEvent.VK_F11);
   break;
   
   case "VK_F12":
   robot.keyPress(KeyEvent.VK_F12);
   break;
   
   case "VK_F13":
   robot.keyPress(KeyEvent.VK_F13);
   break;
   
   case "VK_F14":
   robot.keyPress(KeyEvent.VK_F14);
   break;
   
   case "VK_F15":
   robot.keyPress(KeyEvent.VK_F15);
   break;
   
   case "VK_F16":
   robot.keyPress(KeyEvent.VK_F16);
   break;
   
   case "VK_F17":
   robot.keyPress(KeyEvent.VK_F17);
   break;
   
   case "VK_F18":
   robot.keyPress(KeyEvent.VK_F18);
   break;
   
   case "VK_F19":
   robot.keyPress(KeyEvent.VK_F19);
   break;
   
   case "VK_F2":
   robot.keyPress(KeyEvent.VK_F2);
   break;
   
   case "VK_F20":
   robot.keyPress(KeyEvent.VK_F20);
   break;
   
   case "VK_F21":
   robot.keyPress(KeyEvent.VK_F21);
   break;
   
   case "VK_F22":
   robot.keyPress(KeyEvent.VK_F22);
   break;
   
   case "VK_F23":
   robot.keyPress(KeyEvent.VK_F23);
   break;
   
   case "VK_F24":
   robot.keyPress(KeyEvent.VK_F24);
   break;
   
   case "VK_F3":
   robot.keyPress(KeyEvent.VK_F3);
   break;
   
   case "VK_F4":
   robot.keyPress(KeyEvent.VK_F4);
   break;
   
   case "VK_F5":
   robot.keyPress(KeyEvent.VK_F5);
   break;
   
   case "VK_F6":
   robot.keyPress(KeyEvent.VK_F6);
   break;
   
   case "VK_F7":
   robot.keyPress(KeyEvent.VK_F7);
   break;
   
   case "VK_F8":
   robot.keyPress(KeyEvent.VK_F8);
   break;
   
   case "VK_F9":
   robot.keyPress(KeyEvent.VK_F9);
   break;
   
   case "VK_G":
   robot.keyPress(KeyEvent.VK_G);
   break;
   
   case "VK_GREATER":
   robot.keyPress(KeyEvent.VK_GREATER);
   break;
   
   case "VK_H":
   robot.keyPress(KeyEvent.VK_H);
   break;
   
   case "VK_HOME":
   robot.keyPress(KeyEvent.VK_HOME);
   break;
   
   case "VK_I":
   robot.keyPress(KeyEvent.VK_I);
   break;
   
   case "VK_INSERT":
   robot.keyPress(KeyEvent.VK_INSERT);
   break;
   
   case "VK_J":
   robot.keyPress(KeyEvent.VK_J);
   break;
   
   case "VK_K":
   robot.keyPress(KeyEvent.VK_K);
   break;
   
   case "VK_KP_DOWN":
   robot.keyPress(KeyEvent.VK_KP_DOWN);
   break;
   
   case "VK_KP_LEFT":
   robot.keyPress(KeyEvent.VK_KP_LEFT);
   break;
   
   case "VK_KP_RIGHT":
   robot.keyPress(KeyEvent.VK_KP_RIGHT);
   break;
   
   case "VK_KP_UP":
   robot.keyPress(KeyEvent.VK_KP_UP);
   break;
   
   case "VK_L":
   robot.keyPress(KeyEvent.VK_L);
   break;
   
   case "VK_LEFT":
   robot.keyPress(KeyEvent.VK_LEFT);
   break;
   
   case "VK_LEFT_PARENTHESIS":
   robot.keyPress(KeyEvent.VK_LEFT_PARENTHESIS);
   break;
   
   case "VK_LESS":
   robot.keyPress(KeyEvent.VK_LESS);
   break;
   
   case "VK_M":
   robot.keyPress(KeyEvent.VK_M);
   break;
   
   case "VK_MINUS":
   robot.keyPress(KeyEvent.VK_MINUS);
   break;
   
   case "VK_MULTIPLY":
   robot.keyPress(KeyEvent.VK_MULTIPLY);
   break;
   
   case "VK_N":
   robot.keyPress(KeyEvent.VK_N);
   break;
   
   case "VK_NUM_LOCK":
   robot.keyPress(KeyEvent.VK_NUM_LOCK);
   break;
   
   case "VK_NUMBER_SIGN":
   robot.keyPress(KeyEvent.VK_NUMBER_SIGN);
   break;
   
   case "VK_NUMPAD0":
   robot.keyPress(KeyEvent.VK_NUMPAD0);
   break;
   
   case "VK_NUMPAD1":
   robot.keyPress(KeyEvent.VK_NUMPAD1);
   break;
   
   case "VK_NUMPAD2":
   robot.keyPress(KeyEvent.VK_NUMPAD2);
   break;
   
   case "VK_NUMPAD3":
   robot.keyPress(KeyEvent.VK_NUMPAD3);
   break;
   
   case "VK_NUMPAD4":
   robot.keyPress(KeyEvent.VK_NUMPAD4);
   break;
   
   case "VK_NUMPAD5":
   robot.keyPress(KeyEvent.VK_NUMPAD5);
   break;
   
   case "VK_NUMPAD6":
   robot.keyPress(KeyEvent.VK_NUMPAD6);
   break;
   
   case "VK_NUMPAD7":
   robot.keyPress(KeyEvent.VK_NUMPAD7);
   break;
   
   case "VK_NUMPAD8":
   robot.keyPress(KeyEvent.VK_NUMPAD8);
   break;
   
   case "VK_NUMPAD9":
   robot.keyPress(KeyEvent.VK_NUMPAD9);
   break;
   
   case "VK_O":
   robot.keyPress(KeyEvent.VK_O);
   break;
   
   case "VK_OPEN_BRACKET":
   robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
   break;
   
   case "VK_P":
   robot.keyPress(KeyEvent.VK_P);
   break;
   
   case "VK_PAGE_DOWN":
   robot.keyPress(KeyEvent.VK_PAGE_DOWN);
   break;
   
   case "VK_PAGE_UP":
   robot.keyPress(KeyEvent.VK_PAGE_UP);
   break;
   
   case "VK_PASTE":
   robot.keyPress(KeyEvent.VK_PASTE);
   break;
   
   case "VK_PAUSE":
   robot.keyPress(KeyEvent.VK_PAUSE);
   break;
   
   case "VK_PERIOD":
   robot.keyPress(KeyEvent.VK_PERIOD);
   break;
   
   case "VK_PLUS":
   robot.keyPress(KeyEvent.VK_PLUS);
   break;
   
   case "VK_Q":
   robot.keyPress(KeyEvent.VK_Q);
   break;
   
   case "VK_QUOTE":
   robot.keyPress(KeyEvent.VK_QUOTE);
   break;
   
   case "VK_R":
   robot.keyPress(KeyEvent.VK_R);
   break;
   
   case "VK_RIGHT":
   robot.keyPress(KeyEvent.VK_RIGHT);
   break;
   
   case "VK_RIGHT_PARENTHESIS":
   robot.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS);
   break;
   
   case "VK_S":
   robot.keyPress(KeyEvent.VK_S);
   break;
   
   case "VK_SEMICOLON":
   robot.keyPress(KeyEvent.VK_SEMICOLON);
   break;
   
   case "VK_SEPARATOR":
   robot.keyPress(KeyEvent.VK_SEPARATOR);
   break;
   
   case "VK_SHIFT":
   robot.keyPress(KeyEvent.VK_SHIFT);
   break;
   
   case "VK_SLASH":
   robot.keyPress(KeyEvent.VK_SLASH);
   break;
   
   case "VK_SPACE":
   robot.keyPress(KeyEvent.VK_SPACE);
   break;
   
   case "VK_SUBTRACT":
   robot.keyPress(KeyEvent.VK_SUBTRACT);
   break;
   
   case "VK_T":
   robot.keyPress(KeyEvent.VK_T);
   break;
   
   case "VK_TAB":
   robot.keyPress(KeyEvent.VK_TAB);
   break;
   
   case "VK_U":
   robot.keyPress(KeyEvent.VK_U);
   break;
   
   case "VK_UNDERSCORE":
   robot.keyPress(KeyEvent.VK_UNDERSCORE);
   break;
   
   case "VK_UP":
   robot.keyPress(KeyEvent.VK_UP);
   break;
   
   case "VK_V":
   robot.keyPress(KeyEvent.VK_V);
   break;
   
   case "VK_W":
   robot.keyPress(KeyEvent.VK_W);
   break;
   
   case "VK_X":
   robot.keyPress(KeyEvent.VK_X);
   break;
   
   case "VK_Y":
   robot.keyPress(KeyEvent.VK_Y);
   break;
   
   case "VK_Z":
   robot.keyPress(KeyEvent.VK_Z);
   break;
   
   case "NONE":
   break;
  }
}

public void convertKeyRelease( String command) {
  switch (command) {
   case "VK_0":
   robot.keyRelease(KeyEvent.VK_0);
   break;
   
   case "VK_1":
   robot.keyRelease(KeyEvent.VK_1);
   break;
   
   case "VK_2":
   robot.keyRelease(KeyEvent.VK_2);
   break;
   
   
   case "VK_3":
   robot.keyRelease(KeyEvent.VK_3);
   break;
   
   case "VK_4":
   robot.keyRelease(KeyEvent.VK_4);
   break;
   
   case "VK_5":
   robot.keyRelease(KeyEvent.VK_5);
   break;
   
   case "VK_6":
   robot.keyRelease(KeyEvent.VK_6);
   break;
   
   case "VK_7":
   robot.keyRelease(KeyEvent.VK_7);
   break;
   
   case "VK_8":
   robot.keyRelease(KeyEvent.VK_8);
   break;
   
   case "VK_9)":
   robot.keyRelease(KeyEvent.VK_9);
   break;
   
   case "VK_A":
   robot.keyRelease(KeyEvent.VK_A);
   break;
   
   case "VK_B":
   robot.keyRelease(KeyEvent.VK_B);
   break;
   
   case "VK_BACK_QUOTE":
   robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
   break;
   
   case "VK_BACK_SLASH":
   robot.keyRelease(KeyEvent.VK_BACK_SLASH);
   break;
   
   case "VK_BACK_SPACE":
   robot.keyRelease(KeyEvent.VK_BACK_SPACE);
   break;
   
   case "VK_BRACELEFT":
   robot.keyRelease(KeyEvent.VK_BRACELEFT);
   break;
   
   case "VK_BRACERIGHT":
   robot.keyRelease(KeyEvent.VK_BRACERIGHT);
   break;
   
   case "VK_C":
   robot.keyRelease(KeyEvent.VK_C);
   break;
   
   case "VK_CANCEL":
   robot.keyRelease(KeyEvent.VK_CANCEL);
   break;
   
   case "VK_CAPS_LOCK":
   robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
   break;
   
   case "VK_CIRCUMFLEX":
   robot.keyRelease(KeyEvent.VK_CIRCUMFLEX);
   break;
   
   case "VK_CLEAR":
   robot.keyRelease(KeyEvent.VK_CLEAR);
   break;
   
   case "VK_CLOSE_BRACKET":
   robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
   break;
   
   case "VK_COLON":
   robot.keyRelease(KeyEvent.VK_COLON);
   break;
   
   case "VK_COMMA":
   robot.keyRelease(KeyEvent.VK_COMMA);
   break;
   
   case "VK_CONTROL":
   robot.keyRelease(KeyEvent.VK_CONTROL);
   break;
   
   case "VK_D":
   robot.keyRelease(KeyEvent.VK_D);
   break;
   
   case "VK_DELETE":
   robot.keyRelease(KeyEvent.VK_DELETE);
   break;
   
   case "VK_DOLLAR":
   robot.keyRelease(KeyEvent.VK_DOLLAR);
   break;
   
   case "VK_DOWN":
   robot.keyRelease(KeyEvent.VK_DOWN);
   break;
   
   case "VK_E":
   robot.keyRelease(KeyEvent.VK_E);
   break;
   
   case "VK_F1":
   robot.keyRelease(KeyEvent.VK_F1);
   break;
   
   case "VK_F10":
   robot.keyRelease(KeyEvent.VK_F10);
   break;
   
   case "VK_F11":
   robot.keyRelease(KeyEvent.VK_F11);
   break;
   
   case "VK_F12":
   robot.keyRelease(KeyEvent.VK_F12);
   break;
   
   case "VK_F13":
   robot.keyRelease(KeyEvent.VK_F13);
   break;
   
   case "VK_F14":
   robot.keyRelease(KeyEvent.VK_F14);
   break;
   
   case "VK_F15":
   robot.keyRelease(KeyEvent.VK_F15);
   break;
   
   case "VK_F16":
   robot.keyRelease(KeyEvent.VK_F16);
   break;
   
   case "VK_F17":
   robot.keyRelease(KeyEvent.VK_F17);
   break;
   
   case "VK_F18":
   robot.keyRelease(KeyEvent.VK_F18);
   break;
   
   case "VK_F19":
   robot.keyRelease(KeyEvent.VK_F19);
   break;
   
   case "VK_F2":
   robot.keyRelease(KeyEvent.VK_F2);
   break;
   
   case "VK_F20":
   robot.keyRelease(KeyEvent.VK_F20);
   break;
   
   case "VK_F21":
   robot.keyRelease(KeyEvent.VK_F21);
   break;
   
   case "VK_F22":
   robot.keyRelease(KeyEvent.VK_F22);
   break;
   
   case "VK_F23":
   robot.keyRelease(KeyEvent.VK_F23);
   break;
   
   case "VK_F24":
   robot.keyRelease(KeyEvent.VK_F24);
   break;
   
   case "VK_F3":
   robot.keyRelease(KeyEvent.VK_F3);
   break;
   
   case "VK_F4":
   robot.keyRelease(KeyEvent.VK_F4);
   break;
   
   case "VK_F5":
   robot.keyRelease(KeyEvent.VK_F5);
   break;
   
   case "VK_F6":
   robot.keyRelease(KeyEvent.VK_F6);
   break;
   
   case "VK_F7":
   robot.keyRelease(KeyEvent.VK_F7);
   break;
   
   case "VK_F8":
   robot.keyRelease(KeyEvent.VK_F8);
   break;
   
   case "VK_F9":
   robot.keyRelease(KeyEvent.VK_F9);
   break;
   
   case "VK_G":
   robot.keyRelease(KeyEvent.VK_G);
   break;
   
   case "VK_GREATER":
   robot.keyRelease(KeyEvent.VK_GREATER);
   break;
   
   case "VK_H":
   robot.keyRelease(KeyEvent.VK_H);
   break;
   
   case "VK_HOME":
   robot.keyRelease(KeyEvent.VK_HOME);
   break;
   
   case "VK_I":
   robot.keyRelease(KeyEvent.VK_I);
   break;
   
   case "VK_INSERT":
   robot.keyRelease(KeyEvent.VK_INSERT);
   break;
   
   case "VK_J":
   robot.keyRelease(KeyEvent.VK_J);
   break;
   
   case "VK_K":
   robot.keyRelease(KeyEvent.VK_K);
   break;
   
   case "VK_KP_DOWN":
   robot.keyRelease(KeyEvent.VK_KP_DOWN);
   break;
   
   case "VK_KP_LEFT":
   robot.keyRelease(KeyEvent.VK_KP_LEFT);
   break;
   
   case "VK_KP_RIGHT":
   robot.keyRelease(KeyEvent.VK_KP_RIGHT);
   break;
   
   case "VK_KP_UP":
   robot.keyRelease(KeyEvent.VK_KP_UP);
   break;
   
   case "VK_L":
   robot.keyRelease(KeyEvent.VK_L);
   break;
   
   case "VK_LEFT":
   robot.keyRelease(KeyEvent.VK_LEFT);
   break;
   
   case "VK_LEFT_PARENTHESIS":
   robot.keyRelease(KeyEvent.VK_LEFT_PARENTHESIS);
   break;
   
   case "VK_LESS":
   robot.keyRelease(KeyEvent.VK_LESS);
   break;
   
   case "VK_M":
   robot.keyRelease(KeyEvent.VK_M);
   break;
   
   case "VK_MINUS":
   robot.keyRelease(KeyEvent.VK_MINUS);
   break;
   
   case "VK_MULTIPLY":
   robot.keyRelease(KeyEvent.VK_MULTIPLY);
   break;
   
   case "VK_N":
   robot.keyRelease(KeyEvent.VK_N);
   break;
   
   case "VK_NUM_LOCK":
   robot.keyRelease(KeyEvent.VK_NUM_LOCK);
   break;
   
   case "VK_NUMBER_SIGN":
   robot.keyRelease(KeyEvent.VK_NUMBER_SIGN);
   break;
   
   case "VK_NUMPAD0":
   robot.keyRelease(KeyEvent.VK_NUMPAD0);
   break;
   
   case "VK_NUMPAD1":
   robot.keyRelease(KeyEvent.VK_NUMPAD1);
   break;
   
   case "VK_NUMPAD2":
   robot.keyRelease(KeyEvent.VK_NUMPAD2);
   break;
   
   case "VK_NUMPAD3":
   robot.keyRelease(KeyEvent.VK_NUMPAD3);
   break;
   
   case "VK_NUMPAD4":
   robot.keyRelease(KeyEvent.VK_NUMPAD4);
   break;
   
   case "VK_NUMPAD5":
   robot.keyRelease(KeyEvent.VK_NUMPAD5);
   break;
   
   case "VK_NUMPAD6":
   robot.keyRelease(KeyEvent.VK_NUMPAD6);
   break;
   
   case "VK_NUMPAD7":
   robot.keyRelease(KeyEvent.VK_NUMPAD7);
   break;
   
   case "VK_NUMPAD8":
   robot.keyRelease(KeyEvent.VK_NUMPAD8);
   break;
   
   case "VK_NUMPAD9":
   robot.keyRelease(KeyEvent.VK_NUMPAD9);
   break;
   
   case "VK_O":
   robot.keyRelease(KeyEvent.VK_O);
   break;
   
   case "VK_OPEN_BRACKET":
   robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
   break;
   
   case "VK_P":
   robot.keyRelease(KeyEvent.VK_P);
   break;
   
   case "VK_PAGE_DOWN":
   robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
   break;
   
   case "VK_PAGE_UP":
   robot.keyRelease(KeyEvent.VK_PAGE_UP);
   break;
   
   case "VK_PASTE":
   robot.keyRelease(KeyEvent.VK_PASTE);
   break;
   
   case "VK_PAUSE":
   robot.keyRelease(KeyEvent.VK_PAUSE);
   break;
   
   case "VK_PERIOD":
   robot.keyRelease(KeyEvent.VK_PERIOD);
   break;
   
   case "VK_PLUS":
   robot.keyRelease(KeyEvent.VK_PLUS);
   break;
   
   case "VK_Q":
   robot.keyRelease(KeyEvent.VK_Q);
   break;
   
   case "VK_QUOTE":
   robot.keyRelease(KeyEvent.VK_QUOTE);
   break;
   
   case "VK_R":
   robot.keyRelease(KeyEvent.VK_R);
   break;
   
   case "VK_RIGHT":
   robot.keyRelease(KeyEvent.VK_RIGHT);
   break;
   
   case "VK_RIGHT_PARENTHESIS":
   robot.keyRelease(KeyEvent.VK_RIGHT_PARENTHESIS);
   break;
   
   case "VK_S":
   robot.keyRelease(KeyEvent.VK_S);
   break;
   
   case "VK_SEMICOLON":
   robot.keyRelease(KeyEvent.VK_SEMICOLON);
   break;
   
   case "VK_SEPARATOR":
   robot.keyRelease(KeyEvent.VK_SEPARATOR);
   break;
   
   case "VK_SHIFT":
   robot.keyRelease(KeyEvent.VK_SHIFT);
   break;
   
   case "VK_SLASH":
   robot.keyRelease(KeyEvent.VK_SLASH);
   break;
   
   case "VK_SPACE":
   robot.keyRelease(KeyEvent.VK_SPACE);
   break;
   
   case "VK_SUBTRACT":
   robot.keyRelease(KeyEvent.VK_SUBTRACT);
   break;
   
   case "VK_T":
   robot.keyRelease(KeyEvent.VK_T);
   break;
   
   case "VK_TAB":
   robot.keyRelease(KeyEvent.VK_TAB);
   break;
   
   case "VK_U":
   robot.keyRelease(KeyEvent.VK_U);
   break;
   
   case "VK_UNDERSCORE":
   robot.keyRelease(KeyEvent.VK_UNDERSCORE);
   break;
   
   case "VK_UP":
   robot.keyRelease(KeyEvent.VK_UP);
   break;
   
   case "VK_V":
   robot.keyRelease(KeyEvent.VK_V);
   break;
   
   case "VK_W":
   robot.keyRelease(KeyEvent.VK_W);
   break;
   
   case "VK_X":
   robot.keyRelease(KeyEvent.VK_X);
   break;
   
   case "VK_Y":
   robot.keyRelease(KeyEvent.VK_Y);
   break;
   
   case "VK_Z":
   robot.keyRelease(KeyEvent.VK_Z);
   break;
   
   case "NONE":
   break;
   
  }
}
  public void settings() {  size(440,280); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "InteractionInterfaces" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
