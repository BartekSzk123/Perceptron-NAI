import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFIleLoader {
    public List<Observation> loadData(String fileName) {

        List<Observation> observations = new ArrayList<Observation>();
        String[] parts;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                parts = line.split(",");
                double[] values = new double[parts.length - 1];
                for (int i = 0; i < values.length; i++) {
                    values[i] = Double.parseDouble(parts[i]);
                }

                observations.add(new Observation(parts[parts.length - 1], values));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return observations;
    }
}
