public class Observation {
    private String type;
    private double[] data;

    public Observation(String type, double[] data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public double[] getData() {
        return data;
    }
}
