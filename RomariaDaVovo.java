import java.io.IOException;

public class RomariaDaVovo {

    public static void main(String[] args) throws IOException {
        new GeneticAlgorithm(args[0]).run(100000000);
    }
}
