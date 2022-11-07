package alest3;

import java.io.IOException;

public class TravellingSalesman {

    public static void main(String[] args) throws IOException {
        new GeneticAlgorithm("alest3/data.txt").run(1000000,100, 0.1);
    }
}
