package exercise;

// BEGIN
public class Segment {

    private Point begin;
    private Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Point getMidPoint() {
        return new Point((getEndPoint().getX() + getBeginPoint().getX()) / 2,
                (getEndPoint().getY() + getBeginPoint().getY()) / 2);
    }
}
// END
