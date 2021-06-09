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


}