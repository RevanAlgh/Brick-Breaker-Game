
  package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.io.*;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

    
    public class MainClass implements ActionListener{
        
        // the Main 
    public static void main(String[] args) {

                MainClass m = new MainClass();                  
           
               
    }
       
     //File file;
    ImageIcon imageForOne = new ImageIcon(getClass().getResource("/TheGame//YOUR.jpeg")); 
    
    JFrame frame = new JFrame("Revan's Brick Breaker Game");
    JButton B2 = new JButton("WELCOME<3 CLICK TO START!");//the start button 
    JButton B3 = new JButton("MUSIC!");//the music button  
    JButton B = new JButton(imageForOne);//the background
   
    
    //constructor
    MainClass(){
                 
    B2.setBounds(230,180,350,300);
    B2.setSize(250,190);
    B2.setFocusable(false);
    B2.addActionListener(this);
    B2.setForeground(Color.WHITE);
    
       
    B.setBounds(10,10,700,600);
   
    
    B3.setBounds(30,40,350,300);
    B3.setSize(100,45);
    B3.setFocusable(false);
    B3.addActionListener(this);
    B3.setBackground(Color.BLACK);
    B3.setForeground(Color.WHITE);
   
    frame.add(B3);//the music button
    frame.add(B2);//the start button 
    frame.add(B);//the background image 
   
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 700, 600);
    frame.setVisible(true);
    frame.setResizable(false);
    
    
 }
    
    
 
    //the buttuns actions

 public void actionPerformed(ActionEvent e) {
  
  if(e.getSource()==B2) {
   frame.dispose();
      try {
          Welcome R = new Welcome();
      } catch (IOException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      } catch (UnsupportedAudioFileException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      } catch (LineUnavailableException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
}  
   
 
  if(e.getSource()==B3) {
      
      try {
          playSound();
      } catch (LineUnavailableException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      } catch (UnsupportedAudioFileException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
 }
 
 
 // "src/TheGame/KingPorterStomp.wav"
 
// for the background music
 void playSound() throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
   
      File f = new File("./" + "src/TheGame/KingPorterStomp.wav");
     
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL()); 
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);  
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(-25.0f); // Reduce volume by 25 decibels.
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
     
}

}
