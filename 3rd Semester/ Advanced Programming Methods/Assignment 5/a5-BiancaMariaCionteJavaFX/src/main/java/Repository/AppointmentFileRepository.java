

package Repository;

import Domain.Appointment;

import java.io.*;

public class AppointmentFileRepository extends FileRepository<Appointment, Integer> {
    public AppointmentFileRepository(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = br.readLine()) != null)
            {
                String[] tokens = line.split("[,]");
                if (tokens.length != 3)
                    continue;
                Integer id = Integer.parseInt(tokens[0].trim());
                String description = tokens[1].trim();
                String date = tokens[2].trim();
                Appointment a = new Appointment(id, description,date);
                data.put(id, a);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Appointment a: data.values())
            {
                bw.write(a.getId() + "," + a.getDescription() + "," + a.getDate() +"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}