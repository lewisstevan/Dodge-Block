import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Game that encourages its user to dodge falling objects.
//The game will speed up as lines are cleared.
//Dodging blocks at increased speeds yields higher points.

public class DodgeBlockGui implements Observer
{
    //declare a new Enum describing the different game sizes
    public enum SizeOfGame 
    {
        SMALL (new Dimension(500, 500)),
        MEDIUM (new Dimension(1000, 1000)),
        LARGE (new Dimension(1500, 1500));
        
        Dimension gameSize;
        SizeOfGame(Dimension gameSize)
        {
    	this.gameSize = gameSize;
        }
        
        public Dimension getSize()
        {
    	return gameSize;
        }
    }
    
    private final Dimension GAME_SIZE = SizeOfGame.MEDIUM.getSize();
    
    private final Dimension HUD_ELEMENT_DIMENSIONS = new Dimension(145, 20);
    
    private final Dimension HUD_SPACING = new Dimension(0, (int)GAME_SIZE.getHeight()/6);
    
    DodgeBlockGameBoard GameBoard = new DodgeBlockGameBoard(new Dimension((int)GAME_SIZE.getWidth(), (int)GAME_SIZE.getHeight()));
    
    
    public DodgeBlockGui()
    {
	//initialize the JFrame
	JFrame mainFrame = new JFrame("Dodge Block");
	Border blackline = BorderFactory.createLineBorder(Color.black);
	
	//set the mainFrame properties
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//initialize the main main drawing panel
	DisplayPanel draw = new DisplayPanel("draw", GameBoard);
	draw.setBackground(Color.white);
	draw.setBorder(blackline);
	draw.setPreferredSize(GAME_SIZE);
	
	//initialize the Hud panel
	JPanel Hud = new JPanel();
	Hud.setLayout(new BoxLayout(Hud, BoxLayout.PAGE_AXIS));
	Hud.setPreferredSize(new Dimension((int)HUD_ELEMENT_DIMENSIONS.getWidth() + 14, (int)GAME_SIZE.getHeight()));
	
	//initialize the Lives label
	JLabel liveslbl = new JLabel("Lives: ");
	
	//initialize the Associated Lives display box box
	DisplayPanel lives = new DisplayPanel("lives", GameBoard);
	lives.setAlignmentX((float).0);
	lives.setMaximumSize(new Dimension(HUD_ELEMENT_DIMENSIONS));
	
	//initialize the label for the points box
	JLabel pointslbl = new JLabel("Points: ");
	pointslbl.setMaximumSize(new Dimension(HUD_ELEMENT_DIMENSIONS));
	
	//initialize the Points box that will display the users current point total
	JLabel pointstxt = new JLabel();
	pointstxt.setBackground(Color.white);
	pointstxt.setOpaque(true);
	pointstxt.setMaximumSize(new Dimension(HUD_ELEMENT_DIMENSIONS));
	pointstxt.setBorder(blackline);
	
	//initialize the speed label for the speed box
	JLabel speedlbl = new JLabel("Speed: ");
	speedlbl.setMaximumSize(new Dimension(HUD_ELEMENT_DIMENSIONS));
	
	//initialize the speed display
	JLabel speedtxt = new JLabel();
	speedtxt.setBackground(Color.white);
	speedtxt.setOpaque(true);
	speedtxt.setMaximumSize(new Dimension(HUD_ELEMENT_DIMENSIONS));
	speedtxt.setBorder(blackline);
	
	//Set all components to the HUD
	Hud.add(Box.createRigidArea(new Dimension((int)HUD_SPACING.getWidth() + 5, (int)HUD_SPACING.getHeight())));
	Hud.add(liveslbl);
	Hud.add(lives);
	Hud.add(Box.createRigidArea((new Dimension(HUD_SPACING))));
	Hud.add(pointslbl);
	Hud.add(pointstxt);
	Hud.add(Box.createRigidArea((new Dimension(HUD_SPACING))));
	Hud.add(speedlbl);
	Hud.add(speedtxt);
	
	//Set components to the mainFrame
	mainFrame.add(draw, BorderLayout.CENTER);
	mainFrame.add(Hud, BorderLayout.EAST);
	
	//Set final mainFrame fields
	mainFrame.pack();
	mainFrame.setLocationRelativeTo(null);
	mainFrame.setVisible(true);
    }
    @Override
    public void update(Observable arg0, Object arg1) {
	// TODO Auto-generated method stub
	
    }
    
    public static void main(String [] args)
    {
	DodgeBlockGui run = new DodgeBlockGui();
    }
}
