
package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*; 
        
public class Welcome {
        
        JFrame R =new JFrame();
        Welcome() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
            
		GamePlay gameplay= new GamePlay();
                
             
                R.add(gameplay); 
	        R.setVisible(true);
	        R.setResizable(false);
		R.setBounds(10, 10, 700, 600);
	        R.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        R.setTitle("Revan's Brick Breaker Game");
                
                
                 
                
    }
    }
    
    



