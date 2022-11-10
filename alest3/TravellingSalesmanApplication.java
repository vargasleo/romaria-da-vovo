package alest3;

import java.io.IOException;

public class TravellingSalesmanApplication {

    public static void main(String[] args) throws IOException {
        new GeneticAlgorithm("alest3/data.txt").run(100000000);
    }
}
