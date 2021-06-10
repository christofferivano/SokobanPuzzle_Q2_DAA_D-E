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
  private JButton solver, next, previ;
  private JTextArea textofAnswer;
  private JPanel panelofQuestion;
  
  //labels and text fields 
  private JLabel labelofQuestion = new JLabel();
  private JLabel labelofQuestion2 = new JLabel();
  private JTextField labelofQuestionField = new JTextField();
  private JLabel labelofLoading, labelofSteps;
  
  //scroll-down menus for choice menus
  private JComboBox choicesMenu; 
  
  //choices for scroll down
  private String[] search = {"","Breadth-First", "Depth-First"};
  
  //selected choices
  private String selectedofQuestion = " ";
  
 
  
//  public static void initGame() {
//		
//		String[] options1 = {"Start Solve the Problem"} ;
//  	// JOptionPane untuk menu screen
//		int input1 = JOptionPane.showOptionDialog(null, 
//				"Welcome to Sokoban Solve!", 
//				"Menu", 
//				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]) ;
		
//		//play game
//		if(input1==0) {
//			MainFrame();
//		}
//		else if(input1==2) {
//			quitGame();
//		}
		
//	}
  public MainFrame() throws IOException 
  {
	  init();
	  QuestionsUpdate("");
      setSize(800, 600);
      setTitle("Sokoban Problem Solver");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
      main = new Main();
  }
  
  
  private void QuestionsUpdate(String choices) 
  {
	// TODO Auto-generated method stub
//	  labelofQuestion.setText("Enter the Filename:  ");
//	  labelofQuestionField.setText("[insert the filename]");
//	  labelofQuestionField.setPreferredSize(new Dimension(150, 22));
//	  labelofQuestionField.setVisible(true);
//	  solver.setEnabled(true);
	  
	  if(choices.equals("breadth-first") || choices.equals("depth-first"))
	  	{
		  		labelofQuestion.setText("Enter the Filename:  ");
		  		labelofQuestionField.setText("[insert the filename]");
		  		labelofQuestionField.setPreferredSize(new Dimension(150, 22));
		  		labelofQuestionField.setVisible(true);
		  		solver.setEnabled(true);
		}
	  else
	    {
		  		labelofQuestion.setText("Please Select a Search Methode from The Right");
		  		labelofQuestionField.setVisible(false);
		  		labelofQuestion2.setText("");
		  		solver.setEnabled(false);
	    }
	  repaint();
	
}

private void init() throws IOException
  {
	  JPanel panelMain = (JPanel) getContentPane();
	  panelMain.setLayout(new BorderLayout());
	  panelMain.add(TopPanel(), BorderLayout.NORTH);
	  panelMain.add(AnswerPanel(), BorderLayout.CENTER);
	  panelMain.add(LoadingPanel(), BorderLayout.SOUTH);
	  addListener();
  }
  
  private JScrollPane AnswerPanel()
  {
	  JPanel panelofAnswer = new JPanel();
	  panelofAnswer.setLayout(new BorderLayout());
	  textofAnswer = new JTextArea();
	  textofAnswer.setText("");
	  textofAnswer.setSize(new Dimension(550, 100));
	  textofAnswer.setLineWrap(true);
	  textofAnswer.setEditable(false);
	  
	  Font thefont = new Font("Helvatica", Font.PLAIN, 12);
	  textofAnswer.setFont(thefont);
	  
	  panelofAnswer.add(textofAnswer);
	  JScrollPane paneofAnswer = new JScrollPane(panelofAnswer);
	  
	  paneofAnswer.setSize(new Dimension(750,400));
	  paneofAnswer.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	  paneofAnswer.setBorder(BorderFactory.createTitledBorder("Answer"));
	return paneofAnswer;
	  
  }
  private JPanel TopPanel() throws IOException
  {
	  JPanel PanelTop = new JPanel();
	  PanelTop.setSize(new Dimension (800,100));
	  PanelTop.setLayout(new GridLayout(2,1));
	  
	  Font topPanelFont = new Font("Monserrat", Font.BOLD, 12);
	  PanelTop.setFont(topPanelFont);
	  
	  //add Sokoban Logo on the top of the grid
	  Image logo = ImageIO.read(new File("sokoban.jpg"));
	  Image sizeofLogo = logo.getScaledInstance(800, 60 , Image.SCALE_SMOOTH);
	  JLabel labelofPict = new JLabel(new ImageIcon(sizeofLogo));
	  PanelTop.add(labelofPict);
	  
	  //add panel of question
	  panelofQuestion = new JPanel();
	  panelofQuestion.setLayout(new BorderLayout());
	  panelofQuestion.setSize(800,200);
	  
	  //add drop down for choices search menu
	  choicesMenu = new JComboBox(search);
	  choicesMenu.setSize(120, 60);
	  choicesMenu.setEditable(false);
	  
	  //add solver button to the panelofQuestion
	  solver = new JButton("Solve the Problem");
	  
	  //add question labels and fields to panelofQuestion
	  SpringLayout layout = new SpringLayout();
	  JPanel panelofLabel = new JPanel(layout);
	  panelofLabel.add(labelofQuestion);
	  panelofLabel.add(labelofQuestionField);
	  panelofLabel.add(labelofQuestion2);
	  setLayoutBound(layout, panelofLabel);
	  
	  panelofQuestion.add(choicesMenu, BorderLayout.EAST);
	  panelofQuestion.add(panelofLabel, BorderLayout.CENTER);
	  panelofQuestion.add(solver, BorderLayout.SOUTH);
	  PanelTop.add(panelofQuestion);	  
	return PanelTop;
	  
  }
  
  private void addListener()
  {
	  //add listener of choices Menu 
	  choicesMenu.addActionListener(new ActionListener()
	  		{
		  		public void actionPerformed(ActionEvent actEvent)
		  		{
		  			JComboBox thecombo = (JComboBox) actEvent.getSource();
		  			selectedofQuestion = thecombo.getSelectedItem().toString();
		  			QuestionsUpdate(selectedofQuestion.toLowerCase());
		  		}
			  });
	  
	  //add listener solver 
	  solver.addMouseListener(new MouseListener()
			  {
		  		//template for each actions of mouse
		  			public void mouseClicked(MouseEvent arg0) {
		  				}
		  			public void mouseEntered(MouseEvent arg0) {
		  				}
		  			public void mouseExited(MouseEvent arg0) {
		  				}
		  			public void mouseReleased(MouseEvent arg0) {
		  			}
		  			
		  			//when the mouse pressed
		  			public void mousePressed(MouseEvent arg0)
		  			{
		  				if(solver.isEnabled())
		  				{
		  					labelofSteps.setVisible(false);
		  					previ.setVisible(false);
		  					next.setVisible(false);
		  					dispSolveMessage(selectedofQuestion);
		  					
		  				}
		  			}
		  			
			  });
	  
	  solver.addActionListener(new ActionListener()
			  {
		  			public void actionPerformed(ActionEvent actEvent)
		  			{
//		  				try
//		  				{
//		  					
//		  				}
//		  				catch(FileNotFoundException event)
//		  				{
//		  					dispMessage("File: \"" + labelofQuestion.getText() + "\" is not found:(");
//		  				}
		  			}
		  
			  });
  }
  
  private JPanel LoadingPanel()
  {
	  JPanel panelofLoading = new JPanel();
	  SpringLayout layout = new SpringLayout();
	  panelofLoading.setLayout(layout);
	  panelofLoading.setPreferredSize(new Dimension (650, 50));
	  
	  labelofLoading = new JLabel();
	  labelofLoading.setText(" ");
	  
	  //set step label 
	  labelofSteps = new JLabel();
	  labelofSteps.setText("Show Steps: ");
	  labelofSteps.setVisible(false);
	  
	  //previ and next button 
	  previ = new JButton("Previous");
	  previ.setVisible(false);
	  
	  next = new JButton("Next");
	  next.setVisible(false);
	  
	  panelofLoading.add(labelofLoading);
	  panelofLoading.add(labelofSteps);
	  
	  panelofLoading.add(previ);
	  panelofLoading.add(next);
	  
	return panelofLoading;
	  
  }
  
  private void dispSolveMessage(String message) 
  {
	// TODO Auto-generated method stub
	  labelofLoading.setText("Solving the Puzzle using " + message + " search .... ");
  }
  
  private void dispMessage(String message)
  {
	  labelofLoading.setText(message);
	  repaint();
  }


private void setLayoutBound(SpringLayout layout, JPanel labelPanel) 
  {
		layout.putConstraint(SpringLayout.WEST, labelofQuestion,
              7, SpringLayout.WEST, labelPanel);
		layout.putConstraint(SpringLayout.NORTH, labelofQuestion,
              13, SpringLayout.NORTH, labelPanel);
		layout.putConstraint(SpringLayout.WEST, labelofQuestionField,
              2, SpringLayout.EAST, labelofQuestion); //positions fill the file name
		layout.putConstraint(SpringLayout.NORTH, labelofQuestionField,
              11, SpringLayout.NORTH, labelPanel);//fill the file name
		layout.putConstraint(SpringLayout.WEST, labelofQuestion2,
              2, SpringLayout.EAST, labelofQuestionField);
		layout.putConstraint(SpringLayout.NORTH, labelofQuestion2,
              12, SpringLayout.NORTH, labelPanel);
  }
  
}
