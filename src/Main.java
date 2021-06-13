import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Main
{ 

	public static Solution bFS(Map map)
	{
		LinkedList<Map> stackMap = new LinkedList<Map>();
		Set<String> visited = new HashSet<String>();
 
		long timerOn = System.currentTimeMillis();
		int generatedNodes = 1;
		int uniqueNodes = 1;
		long timerOff;
 
		stackMap.add(map);
		visited.add(map.stringVersion);
 
		while(!stackMap.isEmpty())
		{
			Map temporary = stackMap.pop();
			LinkedList<Map> maps = temporary.adjEdges();
			generatedNodes = generatedNodes + maps.size();
 
			while(!maps.isEmpty())
			{
				Map adjTemporary = maps.pop();
 
				if(! visited.contains(adjTemporary.stringVersion))
				{
					uniqueNodes++;
					visited.add(adjTemporary.stringVersion);
					stackMap.add(adjTemporary);
 
					if(adjTemporary.isSolved())
					{
						timerOff = System.currentTimeMillis();
                        Solution bfs_td = new Solution(adjTemporary, generatedNodes, (generatedNodes - uniqueNodes), uniqueNodes, (timerOff - timerOn));
						return bfs_td;
					}
				}
			}
		}
		timerOff = System.currentTimeMillis();
        Solution bfs_lt = new Solution(null, generatedNodes, (generatedNodes - uniqueNodes), uniqueNodes, (timerOff - timerOn));
		return bfs_lt;
	}
 
	public static Solution dFS(Map map)
	{
		Set<String> discoveredNodes = new HashSet<String>();
		Set<String> exploredNodes = new HashSet<String>();
		LinkedList<Map> stackMap = new LinkedList<Map>();
 
		long timerOn = System.currentTimeMillis();
		int generatedNodes = 1;
		int uniqueNodes = 1;
		long timerOff;
 
		discoveredNodes.add(map.stringVersion);
		stackMap.add(map);
 
		while(!stackMap.isEmpty())
		{
			Map temporary = stackMap.pop();
 
			while(exploredNodes.contains(temporary.stringVersion))
			{
				temporary = stackMap.pop();
			}
 
			if(temporary.isSolved())
			{
				timerOff = System.currentTimeMillis();
				return new Solution(temporary, generatedNodes, (generatedNodes - uniqueNodes),exploredNodes.size(), (timerOff - timerOn));
			}
 
			LinkedList<Map> maps = temporary.adjEdges();
			generatedNodes = generatedNodes + maps.size();
			exploredNodes.add(temporary.stringVersion);
 
			while(!maps.isEmpty())
			{
				Map adjTemp = maps.removeLast();
 
				if( !(discoveredNodes.contains(adjTemp.stringVersion)) )
				{
					uniqueNodes += 1;
					stackMap.add(adjTemp);
					discoveredNodes.add(adjTemp.stringVersion);
				}
			}
		}
		timerOff = System.currentTimeMillis();
        Solution dfs_lt = new Solution(null, generatedNodes, (generatedNodes - uniqueNodes),exploredNodes.size(), (timerOff - timerOn)); 
		return dfs_lt ;
		
		
	}
	
	public static void print(Object stringOrMore){
		System.out.println(stringOrMore);
	}
	public static void main(String[] string)
	{
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		if(string.length != 0 && string[0].equalsIgnoreCase("test"))
		{
			String output = "";
			for(int i = 0 ; i <=10; i++)
			{
				output += "All Algorithms for Puzzles: " + i + "\n";
				Map mapinit = Map.init("sokotest"+ i +".txt");
				output += mapinit.stringVersion + "\n";
				
				//BFS 
				Solution solution;
				output += "BFS: ";
				solution = bFS(mapinit);
				
				//DFS 
				output += "DFS: ";
				solution = dFS(mapinit);
				
				output += "\n"; 
				
				try {
					PrintWriter printout = new PrintWriter (new FileWriter("output.txt"), true);
					printout.write(output);
					printout.close();
				} catch (IOException ex){
					System.err.println("FileWritter has been error :(");
					System.err.println(ex);
					}
				
				}
			print ("Check the ouput.txt");
//			try 
//			{
//				PrintWriter printout = new PrintWriter (new FileWriter("output.txt"), true);
//				printout.write(output);
//				printout.close();
//			} 
//			catch (IOException ex)
//			{
//				System.err.println("FileWritter has been error :(");
//				System.err.println(ex);
//			}
		}
		else {
			String userin;
			print("");
			print("\tWelcome to Sokoban Puzzle Solver\t");
			print("------------------------------------------------");
			print("How to Use this Program?");
			print("But, Before that you must know about the equation from this program");
			print("\tA. If you want to show all puzzles, Type puzzles");
			print("\tB. To show all Algorithms, Type algorithms");
			print("\tC. If you want to Input puzzle, Type file and the the file name");
			print("\tD. Type script to run all Algorithms on all puzzles");
			print("1. Enter the puzzle number and search of Algorithms");
			print("\tFor Example: ");
			print("\tsokoin0.txt BFS");
			print("\tsokoion1.txt DFS");
			print("2. Make sure puzzles should be valid Sokoban Puzzles");
			print("");
			
			try {
				while((userin = stdIn.readLine()) != null)
				{
					Map mapUser;
					Solution solutionUser = null;
					String algoUser = "";
					String mapUserstring = "";
					if(userin.equalsIgnoreCase("algorithms") || 
							userin.equals("puzzles") || userin.startsWith("file")|| userin.startsWith("script"))
					{
							if(userin.equalsIgnoreCase("algorithms"))
							{
								print("There two Algorithms, there are:");
								print("A. BFS --> Breadth First Search");
								print("B. DFS --> Depth First Search");
								print("");
							}
							if(userin.equals("puzzles"))
							{
								print("Puzzle sokoin0.txt:");
								Map maps = Map.init("sokoin0.txt");
								maps.print();
								print("");
								print("Puzzle sokoin1.txt:");
								Map maps1 = Map.init("sokoin1.txt");
								maps1.print();
								print("");
								print("Puzzle sokoin2.txt:");
								Map maps2 = Map.init("sokoin2.txt");
								maps2.print();
								print("");
							}
							
							
							if(userin.startsWith("file"))
							{
								String file = userin.substring(5);
								mapUser = Map.init(file);
								mapUser.print();
								print("Now enter in a puzzle and a algorithm");
								print("example. " + file + " BFS");
							}
							if(userin.equalsIgnoreCase("script"))
							{
								for(int i=0; i<3; i++)
								{
									print("All Algorithms for puzzle: " + i);
									Map map = Map.init("sokoin"+ i +".txt");
									map.print();
									Solution solution;
									print("BFS:");
									solution = bFS(map);
									solution.print();
									print("DFS:");
									solution = dFS(map);
									solution.print();
								}
							}
						}
					else 
					{
						String[] userinp = userin.split();
						mapUserstring = userinp[0];
						algoUser = userinp[1];
						if(algoUser.equalsIgnoreCase("bfs"))
						{
							solutionUser = bFS(mapUser);
							print("Breadth First Search Solution:");
						}
						if(algoUser.equalsIgnoreCase("dfs"))
						{
							print("Depth First Solution:");
							solutionUser = dFS(mapUser);
						}
					}
							
				}
			}catch (IOException ex){
				System.err.println("Invalid Input :(");
				System.err.println(ex);
			}
		}
			
			
			
			
		
	}
	


}
