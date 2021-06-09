import java.util.HashSet;

public class State {
    
    HashSet<Coordinate> box;
    Coordinate user;

    public State(HashSet<Coordinate> box, Coordinate user)
    {
        this.box = box;
        this.user = user;
    }
}
