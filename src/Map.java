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
            BufferedReader br = new BufferedReader(new FileReader(file));
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
        if(move.equals("U"))
        {
            cd.x += -1;
        }
        else if(move.equals("D"))
        {
            cd.x += 1;
        }
        else if(move.equals("L"))
        {
            cd.y += -1;
        }
        else if(move.equals("R"))
        {
            cd.y = 1;
        }
        else
        {
            System.err.println("Invalid move direction");
        }
        return cd;
    }

    public static Coordinate getNormalMove(String move)
    {
        Coordinate cd = new Coordinate(0, 0);
        if(move.equals("U"))
        {
            cd.y += -1;
        }
        else if(move.equals("D"))
        {
            cd.y += 1;
        }
        else if(move.equals("L"))
        {
            cd.x += -1;
        }
        else if(move.equals("R"))
        {
            cd.x = 1;
        }
        else
        {
            System.err.println("Invalid move direction");
        }
        return cd;
    }

    public boolean validMove(String move)
    {
        Coordinate cd = getMove(move);
        Coordinate normCD = getNormalMove(move);
        Coordinate v = new Coordinate(this.playerCoord.x + cd.x, this.playerCoord.y + cd.y);
        Coordinate v2 = new Coordinate(this.playerCoord.x + (2*cd.x), this.playerCoord.y + (2*cd.y));
        Coordinate v3 = new Coordinate(this.playerCoord.x + (3*cd.x), this.playerCoord.y + (3*cd.y));
        Coordinate v2PN = new Coordinate(this.playerCoord.x + (2*cd.x), this.playerCoord.y + (2*cd.y));
        Coordinate v2NN = new Coordinate(this.playerCoord.x + (2*cd.x), this.playerCoord.y + (2*cd.y));
        Coordinate v2PNC = new Coordinate(this.playerCoord.x + (2*cd.x) + normCD.x, this.playerCoord.y + (2*cd.y) + normCD.y);
        Coordinate v2NNC = new Coordinate(this.playerCoord.x + (2*cd.x) - normCD.y, this.playerCoord.y + (2*cd.y) - normCD.y);
        String nextTile = this.tiles.get(v);
        String nextNextTile = this.tiles.get(v2);
        String nextNextNextTile = this.tiles.get(v3);
        String nextNextPositiveNormalTile = this.tiles.get(v2PN);
        String nextNextNegativeNormalTile = this.tiles.get(v2NN);
        String nextNextPositiveNormalTileC = this.tiles.get(v2PNC);
        String nextNextNegativeNormalTileC = this.tiles.get(v2NNC);

        if(nextTile.equals("#"))
        {
            return false;
        }

        if(nextTile.equals("*") || nextTile.equals("$"))
        {
            if(nextNextTile.equals("$") || nextNextTile.equals("*") || nextNextTile.equals("#"))
            {
                return false;
            }
            if(nextNextNextTile.equals("#") && !nextNextTile.equals("."))
            {
                if(nextNextNegativeNormalTileC.equals("#") || nextNextPositiveNormalTileC.equals("#"))
                {
                    return false;
                }
                boolean goalPositiveNormal = false;
                boolean goalNegativeNormal = false;
                boolean allWalls = true;
                Coordinate v3PN = new Coordinate(this.playerCoord.x + (3*cd.x), this.playerCoord.y + (3*cd.y));
                Coordinate v3NN = new Coordinate(this.playerCoord.x + (3*cd.x), this.playerCoord.y + (3*cd.y));
                String nextNextNextPositiveNormalTile = "";
                String nextNextNextNegativeNormalTile = "";
                while(!nextNextPositiveNormalTile.equals("#") && !goalPositiveNormal && allWalls)
                {
                    v2PN.x += normCD.x;
                    v2PN.y += normCD.y;
                    v3PN.x += normCD.x;
                    v3PN.y += normCD.y;
                    nextNextPositiveNormalTile = this.tiles.get(v2PN);
                    nextNextNextPositiveNormalTile = this.tiles.get(v3PN);
                    if(nextNextPositiveNormalTile.equals("."))
                    {
                        goalPositiveNormal = true;
                    }
                    if(!nextNextNextPositiveNormalTile.equals("#"))
                    {
                        allWalls = false;
                    }
                }
                while(!nextNextNegativeNormalTile.equals("#") && !goalNegativeNormal && allWalls)
                {
                    v2NN.x -= normCD.x;
                    v2NN.y -= normCD.y;
                    v3NN.x -= normCD.x;
                    v3NN.y -= normCD.y;
                    nextNextNegativeNormalTile = this.tiles.get(v2NN);
                    nextNextNextNegativeNormalTile = this.tiles.get(v3NN);
                    if(nextNextNegativeNormalTile.equals("."))
                    {
                        goalNegativeNormal = true;
                    }
                    if(!nextNextNextNegativeNormalTile.equals("#"))
                    {
                        allWalls = false;
                    }
                }
                if(!goalPositiveNormal && !goalNegativeNormal && allWalls)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Map move(String move)
    {
        Map movedMap = this.deepCopy();
        Coordinate cd = getMove(move);
        Coordinate v = new Coordinate(movedMap.playerCoord.x + cd.x, movedMap.playerCoord.y + cd.y);
        Coordinate v2 = new Coordinate(movedMap.playerCoord.x + (2*cd.x), movedMap.playerCoord.y + (2*cd.y));
        String currTile = movedMap.tiles.get(movedMap.playerCoord);
        String nextTile = movedMap.tiles.get(v);
        String nextNextTile = movedMap.tiles.get(v2);
        if(currTile.equals("@"))
        {
            movedMap.tiles.put(movedMap.playerCoord, " ");
        }
        else if(currTile.equals("+"))
        {
            movedMap.tiles.put(movedMap.playerCoord, ".");
        }
        else
        {
            System.err.println("standing no where");
        }

        // Next tile is a space
        if(nextTile.equals(" "))
        {
            movedMap.tiles.put(v, "@");
        }
        // Next tile is a box
        else if(nextTile.equals("$") || nextTile.equals("*"))
        {
            movedMap.boxCoord.push(v2);
            movedMap.boxCoord.remove(v);
            if(nextTile.equals("$"))
            {
                movedMap.tiles.put(v, "@");
            }
            else if(nextTile.equals("*"))
            {
                movedMap.tiles.put(v, "+");
            }
            else
            {
                System.err.println("Next Tile changed.");
            }
            // Next tile after the box
            if(nextNextTile.equals(" "))
            {
                movedMap.tiles.put(v2, "$");
            }
            else if(nextNextTile.equals("."))
            {
                movedMap.tiles.put(v2, "*");
            }
            else
            {
                System.err.println("Something weird happened pushing boxes");
            }
        }
        else if(nextTile.equals("."))
        {
            movedMap.tiles.put(v, "+");
        }
        movedMap.cost++;
        movedMap.updateStringVersion();
        movedMap.playerCoord = v;
        return movedMap;
    }

    public LinkedList<Map> adjEdges()
    {
        LinkedList<Map> edges = new LinkedList<Map>();
        String[] cart = {"U", "D", "L", "R"};
        for(int i = 0; i < cart.length; i++)
        {
            if(this.validMove(cart[i]))
            {
                Map edge = this.move(cart[i]);
                edge.moves.add(cart[i]);
                edges.push(edge);
            }
        }
        return edges;
    }

    public int targetsLeft()
    {
        return this.targetCoord.size() - (this.stringVersion.split("\\*").length - 1);
    }

    public boolean isSolved()
    {
        return !(this.tiles.contains(".") || this.tiles.contains("+"));
    }

    public void print()
    {
        for(int i = 0; i < this.height; i++)
        {
            for(int j = 0; j < this.dimension[i]; j++)
            {
                System.out.println(this.tiles.get(new Coordinate(i, j)));
            }
            System.out.println();
        }
    }

    public Map deepCopy()
    {
        int[] dimension;
        int height = 0;
        Coordinate playeCoord = new Coordinate(-1, -1);
        int cost;
        Hashtable<Coordinate, String> tiles = new Hashtable<Coordinate, String>();
        String stringVersion;
        for(Enumeration<Coordinate> e = this.tiles.keys(); e.hasMoreElements();)
        {
            Coordinate coordinate = e.nextElement();
            tiles.put(coordinate, this.tiles.get(coordinate));
        }
        @SuppressWarnings("unchecked")
        LinkedList<String> moves = (LinkedList<String>)this.moves.clone();
        @SuppressWarnings("unchecked")
        LinkedList<Coordinate> boxCoord = (LinkedList<Coordinate>)this.boxCoord.clone();
        @SuppressWarnings("unchecked")
        LinkedList<Coordinate> targetCoord = (LinkedList<Coordinate>)this.targetCoord.clone();
        stringVersion = new String(this.stringVersion);
        dimension = this.dimension;
        height = this.height;
        playeCoord = this.playerCoord;
        cost = this.cost;
        return new Map(tiles, dimension, height, playerCoord, stringVersion, moves, cost, boxCoord, targetCoord);
    }

    public void updateStringVersion()
    {
        String updatedStringVersion = "";
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < this.dimension[i]; j++)
            {
                updatedStringVersion += this.tiles.get(new Coordinate(i, j));
            }
        }
        this.stringVersion = updatedStringVersion;
    }

    public String prettyEdges()
    {
        String prettyEdges = "";
        for(int i = 0; i < this.moves.size(); i++)
        {
            prettyEdges = prettyEdges + ", " + this.moves.get(i);
        }
        return prettyEdges.substring(2);
    }

    @Override public boolean equals(Object other)
    {
        if(other instanceof Map)
        {
            Map that = (Map) other;
            return this.stringVersion.equals(that.stringVersion);
        }
        return false;
    }
}
