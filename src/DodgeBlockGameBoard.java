import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Observable;


public class DodgeBlockGameBoard extends Observable
{
     Dimension sizeOfPlayArea;
     int numberOfBlocks = 0;
     Rectangle2D mainBlock;
     Rectangle2D[][] blockWalls = new Rectangle2D[3][5];
     int numberOfRows = 0;
     double blockHights;
     double blockWidths;
     double mainBlockLocation;
     public DodgeBlockGameBoard(Dimension sizeOfPlayArea)
     {
	 this.sizeOfPlayArea = sizeOfPlayArea;
	 blockHights = sizeOfPlayArea.getHeight() / 5;
	 blockWidths = sizeOfPlayArea.getWidth() / 5;
	 mainBlockLocation = blockWidths * 2;
	 mainBlock = new Rectangle2D.Double(mainBlockLocation, sizeOfPlayArea.getHeight() - blockHights, blockWidths, blockHights);
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
}
