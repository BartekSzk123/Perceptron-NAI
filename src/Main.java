import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

        int attributes = trainSet.getFirst().getData().length;

        double[] weights = new double[attributes];
        Arrays.fill(weights, 0.5);

        Perceptron perceptron = new Perceptron(weights,0);

        Trainer trainer =  new Trainer(trainSet, a, perceptron);
        trainer.learn();
        trainer.testing(testSet);

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Please input data or type exit to exit");

            String line = scanner.nextLine();

            if(line.equals("exit")){
                break;
            }

            String[] parts = line.split(",");
            double[] data = new double[parts.length];

            if(parts.length != attributes){
                System.out.println("Wrong number of data");
                continue;
            }

            try{
                for(int i = 0; i < parts.length; i++){
                    data[i] = Double.parseDouble(parts[i]);
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong type of data");
                continue;
            }

            int result = perceptron.Output(data);

            if(result == 0){
                System.out.println("Versicolor");
            }else {
                System.out.println("Virginica");
            }

        }
    }
}
