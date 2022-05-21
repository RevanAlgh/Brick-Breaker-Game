package TheGame;

import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import java.util.Timer;



public class GamePlay extends JPanel implements KeyListener, ActionListener{
    
    
      Image background;
   
    
    private boolean play=false;
    private int score=0; // the score
    
    private int totalBricks=21; //the total of destried bricks
    private Timer timer;
    private int delay=7; //the ball postponed (lateined)
    
    private int playerX=310;
    
    private int ballPosX=120; // x postion for the ball
    private int ballPosY=350;// y postion for the ball
    
    private int ballXDir=-1; // x diraction for the ball
    private int ballYDir=-2;// y diraction for the ball
    
    private MapGenerator mapG;
    
    
    
    void playSound() throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
   
      File f = new File("./" + "src/TheGame/hit.wav");
     
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL()); 
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);  
    clip.start();
     
}
    // Constructor to set the timer
    public GamePlay() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        
        background = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/TheGame/YOUR.jpeg"));
        
        
        mapG = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this); //timer object
        timer.start();

    }
     
   
    @Override
    public void paint(Graphics g){
        
         //background//
          super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
           
        //borders /*
        g.setColor(Color.white);
        g.fillRect(0,0,3,592); // from right to left
        g.fillRect(0,0,697,3);// top
        g.fillRect(697,0,3,592);// from left to right  */
        
        //the score
        g.setColor(Color.white);
        
        // the scores
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
         // the paddle
        g.setColor(Color.white);
        g.fillRect(playerX, 510, 100, 8);

        //the ball 
        g.setColor(Color.white);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        //map call
                mapG.draw((Graphics2D)g);
                
                 //winning
        if(totalBricks<=0) {
        	play=false;
        	ballYDir=0;
        	ballXDir=0;
        	g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won! : ", 260, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart ", 230, 350);

        	
        }

        //over
        if(ballPosY>570) {
        	play=false;
        	ballXDir=0;
        	ballYDir=0;
        	g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over Score: "+score, 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart ", 230, 350);

        }
        
        g.dispose();

    }
    
    
     public void keyPressed(KeyEvent e){
         
	  if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		if(playerX>=600) {
                    //checking if the player didtn get out of the broder
			
                    playerX = 600;// the bonded on the border <stay>
		
                }
                else {
			moveRight();
		}
	}
	
          
	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
		if(playerX<10) {
                    //checking if the player didtn get out of the broder

			playerX = 10; //keep it 
		}
                
                else {
			moveLeft();
		}
	}
        
        
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {//if they press enter
		if(!play) {
                    
                        play=true;
			ballPosX = 120;
			ballPosY=350;
			ballXDir = -1;
			ballYDir=-2;
                        
			score = 0;
                        
			playerX=310;
                        totalBricks = 21;
			mapG = new MapGenerator(3,7);
			
                        repaint();
		}
	}
}
     

     public void keyTyped(KeyEvent e) { }
     
   
     public void keyReleased(KeyEvent e) { }

     
     public void moveRight(){
         
         play = true ;
         playerX+=20; //move 20px to the right
     
     }
     
     public void moveLeft(){
         
         play = true ;
         playerX-=20;//move 20px to the left 

}
     
    public void actionPerformed(ActionEvent e){
        
        
    	timer.start();
       
        
    	if(play) {
    		if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 510, 100, 8))){//the paddel postion while playing
    			
    			ballYDir = -ballYDir;
                       
                }  
                        else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYDir = -ballYDir;
				ballXDir = ballXDir + 1;
			}
			else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYDir = -ballYDir;
			}
                        
                      
    		A:
    			for(int i=0;i<mapG.map.length;i++) {
    				for(int j=0;j<mapG.map[0].length;j++) {
    					
    					if(mapG.map[i][j]>0) {
                                            
                                                //scores++;
    						int brickX = j*mapG.brichWidth + 80;
    						int brickY = i*mapG.brichHeight + 50;
    						int brickWidth = mapG.brichWidth;
    						int brickHight = mapG.brichHeight;
    						
    						Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHight);
    						Rectangle ballrect = new Rectangle(ballPosX,ballPosY,20,20); 
    						Rectangle brickrect = rect;
    						
    						if(ballrect.intersects(brickrect)) {
    							mapG.setBrickValue(0, i, j);
                                                        try {
                                                            playSound();
                                                        } catch (UnsupportedAudioFileException ex) {
                                                            Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                                        } catch (LineUnavailableException ex) {
                                                            Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        score+=5;
    							totalBricks--;
                                                        
  							// when ball hit right or left of brick
    							if(ballPosX + 19 <= brickrect.x||ballPosX+1 >= brickrect.x+ brickrect.width) {
    								
                                                            ballXDir =- ballXDir;
                                                               
    							}
                                                         // when ball hits top or bottom of brick
                                                        else {
    								ballYDir =- ballYDir;
    							}
    							break A;
    						}
    					
    					}
    				}
    			}
    		
    		ballPosX+=ballXDir;
    		ballPosY+=ballYDir;
                
    		if(ballPosX < 0) {//left border
    			ballXDir = -ballXDir;
    		}
    		if(ballPosY < 0) {//top border
    			ballYDir = -ballYDir;
    		}
    		if(ballPosX > 670) {//right border
    			ballXDir = -ballXDir;
    		}
        
    		
    	 repaint();//recalling the method   
        
        } 
    }
}



  
      
