package alest3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneticAlgorithm {

    private final ArrayList<City> cities;

    GeneticAlgorithm(String filename) throws IOException {
        this.cities = getCities(filename);
    }

    void run(final int generations) {
        var chromosome = new Chromosome(cities);

        for (int i = 0; i < generations; i++) {
            chromosome = evolve(chromosome);
            System.out.println("G " + i + ": " + chromosome.getDistance());
        }
    }

    private Chromosome evolve(final Chromosome chromosome) {
        final var copy = chromosome.copy();
        copy.mutate();
        return chromosome.calculateFitness() < copy.calculateFitness() ? chromosome : copy;
    }

    private static ArrayList<City> getCities(final String filename) throws IOException {
        final var reader = new BufferedReader(new FileReader(filename));
        final var size = Integer.parseInt(reader.readLine());
        final var cities = new ArrayList<City>(size);
        for (int i = 0; i < size; i++) {
            final var line = reader.readLine().split(" ");
            final var x = Double.parseDouble(line[0]);
            final var y = Double.parseDouble(line[1]);
            cities.add(new City(x, y, line[2]));
        }
        return cities;
    }
}
