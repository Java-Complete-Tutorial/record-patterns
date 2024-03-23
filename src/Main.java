public class Main {

    //JEP 440: Record Patterns | Java 21
    //https://openjdk.org/jeps/440

    //Java 16 added records, which are a new kind of type declaration that
    // allow you to declare classes effectively and concisely.
    record Point(int x, int y) {}
    record Line(Point start, Point end) {}
    record Triangle(Line line1, Line line2, Line line3) {}

    public static void main(String[] args) {

        Point point1 = new Point(1, 2);

        //Java 16+
        if (point1 instanceof Point p) {
            System.out.println(p.x());
            System.out.println(p.y());
        }

        //Java 21+
        //Record Patterns allow you to deconstruct record values.
        if (point1 instanceof Point(int x, int y)) {
            //Now, we can more concisely grab the fields of the record.
            System.out.println(x);
            System.out.println(y);
        }else{
            System.out.println("Not a Point");
        }

        //Nesting record patterns is possible if you have a
        // record that contains another record.
        Line line = new Line(new Point(1, 2), new Point(3, 4));
        if (line instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println(x1);
            System.out.println(y1);
            System.out.println(x2);
            System.out.println(y2);
        }

        //You may not care about all values though so you can
        // choose to not deconstruct all of them.
        if (line instanceof Line(Point(int x1, int y1), var p2)) {
            System.out.println(x1);
            System.out.println(y1);
        }

        //You can also use record patterns in switch statements.
        switch (line) {
            case Line(Point(int x1, int y1), Point(int x2, int y2)) -> {
                System.out.println(x1);
                System.out.println(y1);
                System.out.println(x1);
                System.out.println(y1);
            }
            //...
        }

    }


}