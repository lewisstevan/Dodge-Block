import java.awt.Dimension;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import javax.swing.Timer;


public class DodgeBlockGameBoard extends Observable
{
    //Variable deciding how fast the game moves
    private int gameSpeed = 500;
    
    //Timer for the game, fill in the actionListener on this variable once the controller section of the program is completed.
    Timer timer = new Timer(gameSpeed, null);
    
     Dimension sizeOfPlayArea;
     int numberOfBlocks = 0;
     Rectangle2D mainBlock;
     Rectangle2D[][] blockWalls = new Rectangle2D[3][5];
     int numberOfRows = 0;
     double blockHights;
     double blockWidths;
     double mainBlockLocation;
     int score = 0;
     int numberOfLives = 4;
     int numberOfLivesLeft = 4;
     Dimension sizeOfLiveBlock = new Dimension(10, 10);
     Rectangle2D[] lives = new Rectangle2D[numberOfLives];
     
     public DodgeBlockGameBoard(Dimension sizeOfPlayArea)
     {
	 this.sizeOfPlayArea = sizeOfPlayArea;
	 blockHights = sizeOfPlayArea.getHeight() / 5;
	 blockWidths = sizeOfPlayArea.getWidth() / 5;
	 mainBlockLocation = blockWidths * 2;
	 mainBlock = new Rectangle2D.Double(mainBlockLocation, sizeOfPlayArea.getHeight() - blockHights, blockWidths, blockHights);
     }
     
     public void livesBlocks()
     {
	 int itr = 0;
	 int xLocation = 5;
	 while (itr < numberOfLives)
	 {
	     lives[itr] = new Rectangle2D.Double(xLocation,5, (120 / numberOfLives), 10);
	     xLocation += 5 + (120 / numberOfLives);
	     itr++;
	 }
     }
     
     public Rectangle2D[] getLives()
     {
	 return lives;
     }
     
     public void setNewRow()
     {
	 double absentBlock =  0 + (int)(Math.random() * ((4 - 0) + 1));
         if (!(numberOfRows < 3))
         {
             numberOfRows = 0;
         }
         while (numberOfBlocks < 5)
         { 
              if (numberOfBlocks != absentBlock)
              {
	           blockWalls[numberOfRows][numberOfBlocks] = new Rectangle2D.Double(0.0 + (blockWidths * (numberOfBlocks)), -blockHights, blockWidths, blockHights);
              }
              numberOfBlocks++;
         }
         numberOfRows++;
         numberOfBlocks = 0;
     }
     
     public void moveWall()
     {
	 int counter = 0;
	 while (counter < numberOfRows)
	 {
	     int blockItr = 0;
	     while (blockItr < 5)
	     {
	          Rectangle2D oldBlock = blockWalls[counter][blockItr];
	          if (!(oldBlock == null))
	          {
	               blockWalls[counter][blockItr].setRect(oldBlock.getX(), oldBlock.getY() + 1, oldBlock.getWidth(), oldBlock.getHeight());
	          }
                  blockItr++;
	     } 
	     counter++;
	 }
     }
     
     public Rectangle2D[][] getWallBlocks()
     {
	return blockWalls; 
     }
     
     public int getRows()
     {
	 return numberOfRows;
     }
     
     public void moveRight()
     {
	 mainBlockLocation += blockWidths;
	 mainBlock.setRect(mainBlockLocation, sizeOfPlayArea.getHeight() - blockHights, blockWidths, blockHights);
     }
     
     public void moveLeft()
     {
	 mainBlockLocation -= blockWidths;
	 mainBlock.setRect(mainBlockLocation, sizeOfPlayArea.getHeight() - blockHights, blockWidths, blockHights);
     }
     
     public int getScore()
     {
	 return score;
     }
     
     public void addScore()
     {
	 score += 100 * (gameSpeed / 500);
     }
     
     public void updateTimerFaster()
     {
 	gameSpeed *= 2;
     }
     
     public void updateTimerSlower()
     {
 	gameSpeed /= 2;
     }
     
     public int getNumberOfLives()
     {
	 return numberOfLives;
     }
     
     public boolean checkCollision()
     {
	 int counter = 0;
	 boolean canGetPoints = true;
	 while (counter < numberOfRows)
	 {
	     int blockItr = 0;
	     while (blockItr < 5)
	     {
	          Area currentBlock = new Area(blockWalls[counter][blockItr]);
	          if (!(currentBlock == null))
	          {
	               if (currentBlock.intersects(mainBlock))
	               {
	                    canGetPoints = false;
	               }
	          }
                  blockItr++;
	     } 
	     counter++;
	 }
	 return canGetPoints;
     }
     
     public boolean checkCreateNewRow()
     {
	 boolean createRow = false;
	 int counter = 0;
	 while (counter < numberOfRows)
	 {
	    if (blockWalls[counter][0].getY() == blockHights * 2)
	    {
		createRow = true;
	    }
	     counter++;
	 }
	 return createRow;
     }
     
     public int getLivesLeft()
     {
	 return numberOfLivesLeft;
     }
}
