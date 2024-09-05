package site.buraki.strike.models.geometry;

/**
 * Точка на плоскости в декартовой прямоугольной системе координат.
 *
 * <p>Равенство точек основывается на равенстве их координат.
 *
 * <p>Естественный порядок точек основывается на возрастании их
 * {@link Point#distance удалённости от начала координат}.
 *
 * <p>Равенство <b>несовместимо</b> с естественным порядком:
 *
 * <pre>{@code
 *     Point point = new Point(1, 1);
 *     Point anotherPoint = new Point(-1, -1);
 *
 *     System.out.println(point.compareTo(anotherPoint));  // 0
 *     System.out.println(point.equals(anotherPoint));  // false
 * }</pre>
 *
 * @author Расим "Buraki" Эминов
 * @since 0.1.0
 */
public class Point implements Comparable<Point> {

    private final float x;
    private final float y;

    /**
     * Квадрант, которому принадлежит точка.
     *
     * <ul>
     *     <li>0 – точка принадлежит осям координат.
     *
     *     <li>1 – абсцисса и ордината точки положительные.
     *
     *     <li>2 – абсцисса точки отрицательная, ордината точки
     *     положительная.
     *
     *     <li>3 – абсцисса и ордината точки отрицательные.
     *
     *     <li>4 – абсцисса точки положительная, ордината точки
     *     отрицательная.
     * </ul>
     *
     * <pre>{@code
     *     System.out.println(new Point(0, 0).getQuadrant());  // 0
     *     System.out.println(new Point(1, 0).getQuadrant());  // 0
     *     System.out.println(new Point(0, 1).getQuadrant());  // 0
     *     System.out.println(new Point(1, 1).getQuadrant());  // 1
     *     System.out.println(new Point(-1, 1).getQuadrant());  // 2
     *     System.out.println(new Point(-1, -1).getQuadrant());  // 3
     *     System.out.println(new Point(1, -1).getQuadrant());  // 4
     * }</pre>
     *
     * @since 0.1.0
     */
    private Integer quadrant;
    /**
     * Расстояние от начала координат.
     *
     * <pre>{@code
     *     System.out.println(new Point(5, 2).getDistance());  // 5.39
     *     System.out.println(new Point(-3, 1).getDistance());  // 3.16
     *     System.out.println(new Point(-7, 0).getDistance());  // 7.0
     *     System.out.println(new Point(4, -9).getDistance());  // 9.85
     * }</pre>
     *
     * @since 0.1.0
     */
    private Double distance;

    /**
     * Конструктор точки.
     *
     * @param x координата по оси абсцисс.
     * @param y координата по оси ординат.
     * @author Расим "Buraki" Эминов
     * @since 0.1.0
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getQuadrant() {
        if (quadrant == null) {
            if (x > 0 && y > 0) return quadrant = 1;
            if (x < 0 && y > 0) return quadrant = 2;
            if (x < 0 && y < 0) return quadrant = 3;
            if (x > 0 && y < 0) return quadrant = 4;
            return quadrant = 0;
        }

        return quadrant;
    }

    public double getDistance() {
        if (distance == null) {
            return distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        return distance;
    }

    @Override
    public int hashCode() {
        // Используемые системой точки, как правило, имеют целочисленные
        // координаты и лежат близко друг к другу.
        final float GRID_SIZE = 1;

        int xSector = (int) Math.floor(x/GRID_SIZE);
        int ySector = (int) Math.floor(y/GRID_SIZE);

        return 31*xSector + ySector;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Point anotherPoint = (Point) other;

        return Float.compare(x, anotherPoint.x) == 0
            && Float.compare(y, anotherPoint.y) == 0;
    }

    @Override
    public int compareTo(Point anotherPoint) {
        return Double.compare(getDistance(), anotherPoint.getDistance());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(\n"
             + ("x=" + x + ",").indent(4)
             + ("y=" + y).indent(4) + ")";
    }
}
