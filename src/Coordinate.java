public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object other)
    {
        boolean result = false;
        if(other instanceof Coordinate)
        {
            Coordinate that = (Coordinate) other;
            result = (this.x == that.x && this.y == that.y);
        }
        return result;
    }
}
