import java.util.List;
import java.util.*;

public class Solution {
    public Map map;
    public int nodesGenerated;
    public int prevNodes;
    public int exploredNodes;
    public long runTime;

    public Solution(Map map, int nodesGenerated, int prevNodes, int exploredNodes, long runTime) {
        this.map = map;
        this.nodesGenerated = nodesGenerated;
        this.prevNodes = prevNodes;
        this.exploredNodes = exploredNodes;
        this.runTime = runTime;
    }

    public String toString()
    {
        String output = "";
        output += "Nodes Generated : " + this.nodesGenerated;
        output += "\nPrev Nodes : " + this.prevNodes;
        output += "\nExplored Nodes : " + this.exploredNodes;
        output += "\nRun Time : " + this.runTime;

        if(this.map == null)
        {
            output += "\nNo Solution Found";
        }
        else
        {
            output += "\nEdges : "; 
            output += "\nCost : ";
            output += "\n";
        }
        return output;
    }

    public void print()
    {
        System.out.println("Nodes Generated : " + this.nodesGenerated);
        System.out.println("Prev Nodes : " + this.prevNodes);
        System.out.println("Explored Nodes : " + this.exploredNodes);
        System.out.println("Run Time : " + this.runTime);

        if(this.map == null)
        {
            System.out.println("No Solution Found");
        }
        else
        {

        }
    }

}


