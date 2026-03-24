import java.util.List;

public class Main {
    public static void main(String[] args) {

        if(args.length < 3) {
            System.out.println("Usage: a train.csv test.csv");
            return;
        }

        double a = Double.parseDouble(args[0]);
        String trainFile = args[1];
        String testFile = args[2];

        CsvFIleLoader csvFIleLoader = new CsvFIleLoader();
        List<Observation> trainSet = csvFIleLoader.loadData(trainFile);
        List<Observation> testSet = csvFIleLoader.loadData(testFile);

        double[] weights = { 0.5 , 0.5 , 0.5 , 0.5};
        Perceptron perceptron = new Perceptron(weights,0);

        Trainer trainer =  new Trainer(trainSet, a, perceptron);
        trainer.learn();
        trainer.testing(testSet);
    }
}
