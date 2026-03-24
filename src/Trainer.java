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

    public int getExpected(Observation observation) {
        return observation.getType().equals("Iris-virginica") ? 1 : 0;
    }

    public boolean isVirginica(Observation observation) {
        return observation.getType().equals("Iris-virginica");
    }

    public void learn() {

        for (int i = 0; i < 100; i++) {

            for (Observation observation : observations) {

                int d = getExpected(observation);
                int y = perceptron.Output(observation.getData());
                if (d != y) {
                    perceptron.deltaRule(observation.getData(), d, y, a);
                }

            }
        }
    }

    public void testing(List<Observation> testSet) {

        double accuracy = 0;
        double virginicaCorrect = 0;
        double versicolorCorrect = 0;
        int virginicaTotal = 0;
        int versicolorTotal = 0;

        for (Observation observation : testSet) {

            int d = getExpected(observation);
            int y = perceptron.Output(observation.getData());

            if (isVirginica(observation)) {
                virginicaTotal++;
            } else {
                versicolorTotal++;
            }

            if (d == y) {
                accuracy++;

                if (isVirginica(observation)) {
                    virginicaCorrect++;
                } else {
                    versicolorCorrect++;
                }
            }
        }

        System.out.println("Accuracy: " + accuracy/testSet.size());
        System.out.println("Virginica accuracy: " + virginicaCorrect/virginicaTotal);
        System.out.println("Versicolor accuracy: " + versicolorCorrect/versicolorTotal);

    }
}
