import java.util.ArrayList;

public class Chromosome {

    private final ArrayList<City> cities;
    private double distance;

    Chromosome(ArrayList<City> cities) {
        this.cities = cities;
    }

    Chromosome(ArrayList<City> cities, double distance) {
        this.cities = cities;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Chromosome copy() {
        return new Chromosome(new ArrayList<>(cities), distance);
    }

    private double distance(final City a, final City b) {
        final var x = Math.pow(b.coordinates[0] - a.coordinates[0], 2);
        final var y = Math.pow(b.coordinates[1] - a.coordinates[1], 2);
        return Math.sqrt(x + y);
    }

    public double calculateFitness() {
        double totalDistance = 0;
        for (int i = 0; i < cities.size() - 1; i++) totalDistance += distance(cities.get(i), cities.get(i + 1));
        this.distance = totalDistance + distance(cities.get(cities.size() - 1), cities.get(0));
        return distance;
    }

    public void mutate() {
        final var a = (int) (Math.random() * cities.size());
        var b = (int) (Math.random() * cities.size());
        while (a == b) b = (int) (Math.random() * cities.size());
        final var start = Math.min(a, b);
        final var end = Math.max(a, b);
        final var copy = new ArrayList<>(cities.subList(start, end));
        for (int i = 0; i < copy.size(); i++) cities.set(start + i, copy.get(copy.size() - i - 1));
    }
}