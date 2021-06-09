import java.util.HashSet;

public class State {
    
    HashSet<Coordinate> box;
    Coordinate user;

    public State(HashSet<Coordinate> box, Coordinate user)
    {
        this.box = box;
        this.user = user;
    }

    //Check if state is already explored
    @Override
    public int hashCode()
    {
        int result = 17;
        for (Coordinate b : box)
        {
            result = 37 * result + b.hashCode();
        }
        result = 37 * result + user.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o_state)
    {
        if(o_state == null)
        {
            return false;
        }
        if(o_state == this)
        {
            return true;
        }
        if(this.getClass() !=  o_state.getClass())
        {
            return false;
        }
        State i = (State)o_state;

        if(this.hashCode() == i.hashCode())
        {
            return true;
        }
        if((this.box == i.box) && (this.user == i.user))
        {
            return true;
        }
        
        return false;
    }
}
