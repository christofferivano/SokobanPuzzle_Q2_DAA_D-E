
public class Nodes 
{
    public int cost;
    public String moving;
    public Nodes parent;
    public State state;

    public Nodes(State state, Nodes parent,int cost, String moving) // Constructor. Fungsinya harus urut
    {
        this.state = state;
        this.cost = cost ;
        this.parent = parent;
        this.moving = moving;
    }

    @Override
    public boolean equals(Object i)
    {
        return(this.state == ((Nodes)i).state);
    }

}
