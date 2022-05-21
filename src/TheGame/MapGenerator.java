package TheGame;



//MapGenerator

    import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.Graphics2D;

     public class MapGenerator {
	

	    public int map[][];
	    public int brichWidth;
	    public int brichHeight;


	    public MapGenerator(int row,int col){
                
	        map = new int[row][col];
                
                for (int i =0 ; i<map.length;i++) {
                    for (int j = 0; j<map[0].length; j++) {
                        map[i][j] = 1;
                    }
                }
                
	        brichWidth=540/col;
	        brichHeight=150/row;
	    }

	    public void draw(Graphics2D g ){
                
	        for(int i=0;i<map.length;i++){
                    
	            for(int j=0;j<map[0].length;j++){
                        
	                if(map[i][j]>0){
                            
	                    g.setColor(Color.white);
	                    g.fillRect(j*brichWidth+80,i*brichHeight+50,brichWidth,brichHeight);
                            
                            // this is just to show separate brick, game can still run without it
	                    g.setStroke(new BasicStroke(4));
	                    g.setColor(Color.DARK_GRAY);
	                    g.drawRect(j*brichWidth+80,i*brichHeight+50,brichWidth,brichHeight);    

	                }
	            }
	        }

	    }

	    public void setBrickValue(int value,int row,int col) {
	        map[row][col] = value;

	    }
	    
	}




