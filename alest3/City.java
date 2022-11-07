package alest3;

public class City {

    final double[] coordinates;
    final String name;

    City(double x, double y, String name) {
        coordinates = new double[2];
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.name = name;
    }
}