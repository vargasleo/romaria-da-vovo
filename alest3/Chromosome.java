package alest3;

import java.util.ArrayList;

public class Chromosome {

    public final ArrayList<Integer> sequence;
    public double fitness;

    Chromosome(ArrayList<Integer> sequence) {
        this.sequence = sequence;
        this.fitness = 0;
    }

    double getFitness() {
        return fitness;
    }

    double distance(final City a, final City b) {
        final var x = Math.pow(b.coordinates[0] - a.coordinates[0], 2);
        final var y = Math.pow(b.coordinates[1] - a.coordinates[1], 2);
        return Math.sqrt(x + y);
    }

    public void calculateFitness(final ArrayList<City> cities) {
        double totalDistance = 0;
        for (int i = 0; i < sequence.size() - 1; i++) {
            totalDistance += distance(cities.get(sequence.get(i)), cities.get(sequence.get(i + 1)));
        }
        totalDistance += distance(cities.get(sequence.get(sequence.size() - 1)), cities.get(sequence.get(0)));
        fitness = 1 / totalDistance;
    }

    void mutate(double chance) {
        if (Math.random() < chance) {
            final var index1 = (int) (Math.random() * sequence.size());
            final var index2 = (int) (Math.random() * sequence.size());
            final var temp = sequence.get(index1);
            sequence.set(index1, sequence.get(index2));
            sequence.set(index2, temp);
        }
    }
}