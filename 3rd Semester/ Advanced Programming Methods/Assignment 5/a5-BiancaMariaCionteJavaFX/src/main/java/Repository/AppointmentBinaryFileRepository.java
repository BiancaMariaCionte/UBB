package Repository;

import Domain.Appointment;
import Domain.Patient;
import Domain.ValidationException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AppointmentBinaryFileRepository extends FileRepository<Appointment, Integer> implements Serializable {

    public AppointmentBinaryFileRepository(String filename)
    {
        super(filename);
    }
    @Override
    public void readFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            Map<Integer,Appointment > emptyMap = new HashMap<>();
            //data = new HashMap<>();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(emptyMap);
                //oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            data = (Map<Integer, Appointment>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(Integer id) throws ValidationException {
        if (!data.containsKey(id)) {
            throw new ValidationException("The element doesn't exist");
        } else {
            data.remove(id);
            writeToFile();  // Save the updated map to the file
        }
    }
}

