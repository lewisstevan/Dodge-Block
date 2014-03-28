import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel
{
    private String panelName = "";
    DodgeBlockGameBoard gameBoard;
    private Rectangle2D[][] WallBlocks;
    public DisplayPanel(String panelname, DodgeBlockGameBoard theBoard)
    {
	super();
	this.panelName = panelname;
	this.setBackground(Color.white);
	this.setBorder(BorderFactory.createLineBorder(Color.black));
	this.gameBoard = theBoard;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	
	if (panelName.equals("draw"))
	{
	    WallBlocks = gameBoard.getWallBlocks();
	    int rowIterator = 0;
	    int blockIterator = 0;
	    int rows = gameBoard.getRows();
            g2d.draw(gameBoard.mainBlock);
	    while (rowIterator < rows)
	    {
               while (blockIterator < 5)
        	 {
        		if (!(WallBlocks[rowIterator][blockIterator] == null))
        		{
        		    g2d.draw(WallBlocks[rowIterator][blockIterator]);
        		}
        		blockIterator++;
        	 }
               rowIterator++;
	    }   
	}
		
    }
    
    public void drawWallBlocks(Graphics2D g2d)
    {
	
	return;
    }
}
