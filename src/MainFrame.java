import java.awt.* ;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;

import javax.swing.JFrame;

public class MainFrame extends JFrame 
{
  
  
  public MainFrame() throws IOException 
  {
//       init();
      setSize(800, 600);
      setTitle("Sokoban Puzzle");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
      
  }
  
}
