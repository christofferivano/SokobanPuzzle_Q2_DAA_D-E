import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;

//import javax.swing.JButton;
//import javax.swing.JFrame;

public class MainFrame extends JFrame 
{
  
  private static final long serialVersionUID = 1L; 
  private Main main; 
  private JButton submit, next, previous;
  private JTextArea answertext;
  private JPanel panelofQuestion;
  
  //labels and text fields 
  private JLabel labelofQuestion = new JLabel();
  private JLabel labelofQuestion2 = new JLabel();
  
  
  //scroll-down menus for choice menus
  private JComboBox choicesMenu; 
  
  //choices for scroll down
  private String[] search = {"Breadth-First", "Depth-First"};
  
  public MainFrame() throws IOException 
  {
	  init();
      setSize(800, 600);
      setTitle("Sokoban Puzzle");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
      main = new Main();
  }
  
  private void init() throws IOException
  {
	  JPanel panelMain = (JPanel) getContentPane();
	  panelMain.setLayout(new BorderLayout());
	  panelMain.add(getTopPanel(), BorderLayout.EAST);
	  
  }
  
  private JPanel getTopPanel() throws IOException
  {
	  JPanel PanelTop = new JPanel();
	  PanelTop.setSize(new Dimension (800,100));
	  PanelTop.setLayout(new GridLayout(2,1));
	  
	  //add Sokoban Logo on the top of the grid
	  
	  
	  
	  //add drop down for choices search menu
	  choicesMenu = new JComboBox(search);
	  choicesMenu.setSize(90, 45);
	  choicesMenu.setEditable(false);
	  
	return PanelTop;
	  
  }
  
}
