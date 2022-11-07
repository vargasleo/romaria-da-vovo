package alest3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneticAlgorithm {

    private final ArrayList<City> cities;
    private ArrayList<Chromosome> population;

    GeneticAlgorithm(String filename) throws IOException {
        this.cities = getCities(filename);
    }

    //ler cidades
    //chutar uma ordem
    //calcular fitness
    //selecionar os melhores
    //reproduzir
    //mutar
    //repetir

    void run(int generations, int populationSize, double mutationRate) {
        System.out.println("Running genetic algorithm for " + generations + " generations with population size " + populationSize);
        this.population = generatePopulation(populationSize);
        population.forEach(c -> c.calculateFitness(cities));
        population.sort(Comparator.comparing(Chromosome::getFitness).reversed());
        for (int i = 0; i < generations; i++) {
            this.population = evolvePopulation(this.population);
            System.out.println("Best solution: " + (1/this.population.get(0).getFitness()));
        }
        System.out.println("Best solution: " + this.population.get(0).getFitness());
    }

    private ArrayList<Chromosome> evolvePopulation(ArrayList<Chromosome> population) {
        population.sort(Comparator.comparing(Chromosome::getFitness).reversed());
        final var newPopulation = new ArrayList<Chromosome>();
        for (int i = 0; i < population.size() - 1; i++) {
            final var parent1 = selectParent(population);
            final var parent2 = selectParent(population);
            final var child = crossover(parent1, parent2);
            child.mutate(0.01);
            child.calculateFitness(cities);
            newPopulation.add(child);
        }
        newPopulation.add(population.get(0));
        return newPopulation;
    }

    private Chromosome crossover(Chromosome parent1, Chromosome parent2) {
        final var child = new Chromosome(new ArrayList<>());
        final var start = (int) (Math.random() * parent1.sequence.size());
        final var end = (int) (Math.random() * parent1.sequence.size());
        for (int i = 0; i < parent1.sequence.size(); i++) {
            if (i > start && i < end) {
                child.sequence.add(parent1.sequence.get(i));
            } else if (start > end) {
                if (!(i < start && i > end)) {
                    child.sequence.add(parent1.sequence.get(i));
                }
            }
        }
        for (int i = 0; i < parent2.sequence.size(); i++) {
            if (!child.sequence.contains(parent2.sequence.get(i))) {
                child.sequence.add(parent2.sequence.get(i));
            }
        }
        return child;
    }

    private Chromosome selectParent(ArrayList<Chromosome> population) {
        final var index = (int) (Math.random() * population.size()) % 10;
        return population.get(index);
    }

    private ArrayList<Chromosome> generatePopulation(int populationSize) {
        final var population = new ArrayList<Chromosome>();
        final var sequence = new ArrayList<Integer>();
        for (int i = 0; i < cities.size(); i++) {
            sequence.add(i);
        }
        for (int i = 0; i < populationSize; i++) {
            Collections.shuffle(sequence);
            population.add(new Chromosome(sequence));
        }
        return population;
    }

    private static ArrayList<City> getCities(final String filename) throws IOException {
        final var reader = new BufferedReader(new FileReader(filename));
        final var size = Integer.parseInt(reader.readLine());
        final var cities = new ArrayList<City>(size);
        System.out.println("Reading " + size + " cities from file " + filename);
        for (int i = 0; i < size; i++) {
            final var line = reader.readLine().split(" ");
            final var x = Double.parseDouble(line[0]);
            final var y = Double.parseDouble(line[1]);
            cities.add(new City(x, y, line[2]));
        }
        return cities;
    }
}