import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel
{
    private String panelName = "";
    DodgeBlockGameBoard gameBoard;
    private Rectangle2D[][] WallBlocks;
    private Rectangle2D[] lives;
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
	
	if (panelName.equals("lives"))
	{
	    int itr = 0;
	    int numberOfLives = gameBoard.getNumberOfLives();
	    int numberOfLivesLeft = gameBoard.getLivesLeft();
	    gameBoard.livesBlocks();
	    lives = gameBoard.getLives();
	    while (itr < numberOfLivesLeft)
	    {
	         g2d.setColor(Color.red);
	         g2d.fill(lives[itr]);
	         g2d.draw(lives[itr]);
	         itr++;
	    }
	    if (numberOfLives > numberOfLivesLeft)
	    {
		while (itr < numberOfLives)
		{
		    g2d.setColor(Color.red);
		    Stroke thisStroke = new BasicStroke(2);
		    g2d.setStroke(thisStroke);
		    g2d.draw(lives[itr]);
		    itr++;
		}
	    }
	}
		
    }
    
    public void drawWallBlocks(Graphics2D g2d)
    {
	
	return;
    }
}
