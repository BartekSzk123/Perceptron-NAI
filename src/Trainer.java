import java.util.List;

public class Trainer {

    private final List<Observation> observations;
    private final double a;
    private final Perceptron perceptron;

    public Trainer(List<Observation> observations, double a, Perceptron perceptron) {
        this.observations = observations;
        this.a = a;
        this.perceptron = perceptron;
    }

    public void learn() {

        for (int i = 0; i < 100; i++) {

            for (Observation observation : observations) {

                int d;
                if (observation.getType().equals("Iris-setosa")) {
                    d = 1;
                } else {
                    d = 0;
                }

                int y = perceptron.Output(observation.getData());
                if (d != y) {
                    perceptron.deltaRule(observation.getData(), d, y, a);
                }

            }
        }
    }

    public double testing(List<Observation> testSet) {
        double accuracy = 0;
        for (Observation observation : testSet) {
            int d;
            if (observation.getType().equals("Iris-setosa")) {
                d = 1;
            } else {
                d = 0;
            }

            int y = perceptron.Output(observation.getData());

            if(d == y){
                accuracy +=1;
            }

        }
        return accuracy/testSet.size();
    }
}
