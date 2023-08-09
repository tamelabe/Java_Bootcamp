package ex01;

public class Program {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Please write 2 input files");
            System.exit(-1);
        }
        SimilarityDeterminator sim = new SimilarityDeterminator();
        sim.getSimilarity(args[0], args[1]);
    }
}