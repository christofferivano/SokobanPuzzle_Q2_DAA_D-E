import java.io.*;
import java.util.*;

public class Map {
    public Hashtable<Coordinate, String> tiles = new Hashtable<Coordinate, String>();
    public int[] dimension;
    public int height = 0;
    public Coordinate playerCoord = new Coordinate(-1, -1);
    public String stringVersion;
    public LinkedList<String> moves;
    public int cost;
    public LinkedList<Coordinate> boxCoord;
    public LinkedList<Coordinate> targetCoord;

    Map(Hashtable<Coordinate, String> tiles, int[] dimension, int height, Coordinate playerCoord,
        String stringVersion, LinkedList<String> moves, int cost, LinkedList<Coordinate> boxCoord, LinkedList<Coordinate> targetCoord)
    {
        this.tiles = tiles;
        this.dimension = dimension;
        this.height = height;
        this.playerCoord = playerCoord;
        this.stringVersion = stringVersion;
        this.moves = moves;
        this.cost = cost;
        this.boxCoord = boxCoord;
        this.targetCoord = targetCoord;
    }

    public static Map init(String file)
    {
        try 
        {
            int height = Integer.parseInt(br.readLine());
            int [] dimension = new int[height];
            Hashtable<Coordinate, String> tiles = new Hashtable<Coordinate, String>();
            Coordinate playerCoord = null;
            String stringVersion = "";
            LinkedList<String> moves = new LinkedList<String>();
            LinkedList<Coordinate> boxCoord = new LinkedList<Coordinate>();
            LinkedList<Coordinate> targetCoord = new LinkedList<Coordinate>();
            boolean foundPlayer = false;

            for(int i = 0; i < height; i++)
            {
                String readLine = br.readLine();
                stringVersion = stringVersion + readLine + "\n";
                String [] mapLine = readLine.split("");
                String [] line = new String [mapLine.length - 1];
                for(int j = 0; j < line.length; j++)
                {
                    line[j] = mapLine[j + 1];
                }
                dimension[i] = line.length;
                for(int k = 0; k < line.length; k++)
                {
                    if(line[k].equals("@") || line[k].equals("+"))
                    {
                        playerCoord = new Coordinate(i, k);
                        foundPlayer = true;
                    }
                    if(line[k].equals("$") || line[k].equals("*"))
                    {
                        boxCoord.push(new Coordinate(i, k));
                    }
                    if(line[k].equals(".") || line[k].equals("*"))
                    {
                        targetCoord.push(new Coordinate(i, k));
                    }
                    tiles.put(new Coordinate(i, k), line[k]);
                }
            }
            if(!foundPlayer)
            {
                System.err.println("This map doesn't have a player and can't be solved.");
            }
            return new Map(tiles, dimension, height, playerCoord, stringVersion, moves, 0, boxCoord, targetCoord);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.err.println("Can't read file.");
        }
        return null;
    }

    public static Coordinate getMove(String move)
    {
        Coordinate cd = new Coordinate(0, 0);
        if(move.equals("u"))
        {
            cd.x += -1;
        }
        else if(move.equals("d"))
        {
            cs.x += 1;
        }
        else if(move.equals("l"))
        {
            cs.y += -1;
        }
        else if(move.equals("r"))
        {
            cs.y = 1;
        }
        else
        {
            System.err.println("Invalid move direction");
        }
        return cd;
    }
}
