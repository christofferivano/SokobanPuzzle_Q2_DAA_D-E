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
  private JButton submit, next, previ;
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
  private String[] search = {"Breadth-First", "Depth-First"};
  
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
	  QuestionsUpdate("default");
      setSize(800, 600);
      setTitle("Sokoban Puzzle");
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
//	  submit.setEnabled(true);
	  
	  if(choices.equals("breadth-first") || choices.equals("depth-first"))
	  	{
		  		labelofQuestion.setText("Enter the Filename:  ");
		  		labelofQuestionField.setText("[insert the filename]");
		  		labelofQuestionField.setPreferredSize(new Dimension(150, 22));
		  		labelofQuestionField.setVisible(true);
		  		submit.setEnabled(true);
		}
	  else
	    {
		  		labelofQuestion.setText("Please Select a Search Methode from The Right");
		  		labelofQuestionField.setVisible(false);
		  		labelofQuestion2.setText("");
		  		submit.setEnabled(false);
	    }
	  repaint();
	
}

private void init() throws IOException
  {
	  JPanel panelMain = (JPanel) getContentPane();
	  panelMain.setLayout(new BorderLayout());
	  panelMain.add(TopPanel(), BorderLayout.NORTH);
	  panelMain.add(AnswerPanel(), BorderLayout.CENTER);
//	  panelMain.add(LoadingPanel(, BorderLayout.SOUTH));
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
	  choicesMenu.setSize(90, 45);
	  choicesMenu.setEditable(false);
	  
	  //add submit button to the panelofQuestion
	  submit = new JButton("Solve");
	  
	  //add question labels and fields to panelofQuestion
	  SpringLayout layout = new SpringLayout();
	  JPanel panelofLabel = new JPanel(layout);
	  panelofLabel.add(labelofQuestion);
	  panelofLabel.add(labelofQuestionField);
	  panelofLabel.add(labelofQuestion2);
	  setLayoutBound(layout, panelofLabel);
	  
	  panelofQuestion.add(choicesMenu, BorderLayout.EAST);
	  panelofQuestion.add(panelofLabel, BorderLayout.CENTER);
	  panelofQuestion.add(submit, BorderLayout.SOUTH);
	  PanelTop.add(panelofQuestion);	  
	return PanelTop;
	  
  }
  
  private void addListener()
  {
	  choicesMenu.addActionListener(new ActionListener()
	  		{
		  		public void actionPerformed(ActionEvent actEvent)
		  		{
		  			JComboBox thecombo = (JComboBox) actEvent.getSource();
		  			selectedofQuestion = thecombo.getSelectedItem().toString();
		  			QuestionsUpdate(selectedofQuestion.toLowerCase());
		  		}
			  });
	  submit.addMouseListener(new MouseListener()
			  {
		  			public void mouseClicked(MouseEvent arg0) {
		  				}
		  			public void mouseEntered(MouseEvent arg0) {
		  				}
		  			public void mouseExited(MouseEvent arg0) {
		  				}
		  			public void mouseReleased(MouseEvent arg0) {
		  			}
		  			
		  			public void mousePressed(MouseEvent arg0)
		  			{
		  				if(submit.isEnabled())
		  				{
		  					labelofSteps.setVisible(false);
		  					previ.setVisible(false);
		  					next.setVisible(false);
		  					dispSolveMessage(selectedofQuestion);
		  					
		  				}
		  			}
		  			
			  });
  }
  
  private void dispSolveMessage(String message) 
  {
	// TODO Auto-generated method stub
	  labelofLoading.setText("Solving the Puzzle using " + message + " search .... ");
	  
	
}


private void setLayoutBound(SpringLayout layout, JPanel labelPanel) 
  {
		layout.putConstraint(SpringLayout.WEST, labelofQuestion,
              7, SpringLayout.WEST, labelPanel);
		layout.putConstraint(SpringLayout.NORTH, labelofQuestion,
              19, SpringLayout.NORTH, labelPanel);
		layout.putConstraint(SpringLayout.WEST, labelofQuestionField,
              1, SpringLayout.EAST, labelofQuestion);
		layout.putConstraint(SpringLayout.NORTH, labelofQuestionField,
              17, SpringLayout.NORTH, labelPanel);
		layout.putConstraint(SpringLayout.WEST, labelofQuestion2,
              1, SpringLayout.EAST, labelofQuestionField);
		layout.putConstraint(SpringLayout.NORTH, labelofQuestion2,
              14, SpringLayout.NORTH, labelPanel);
  }
  
}

