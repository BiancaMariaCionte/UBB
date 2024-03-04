package Repository;

import Domain.Patient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PatientJSONRepository extends FileRepository<Patient, Integer> {

    public PatientJSONRepository(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try (FileReader reader = new FileReader(filename)) {
            JSONParser jsonParser = new JSONParser(); //Create a JSONParser object to parse JSON content
            JSONArray patientsArray = (JSONArray) jsonParser.parse(reader); //Parse the JSON content from the file into a JSONArray

            for (Object obj : patientsArray) // Iterate over each element in the JSON array
            {
                JSONObject patientObject = (JSONObject) obj; // Cast each element to a JSONObject
                int id = Math.toIntExact((Long) patientObject.get("id")); //Retrieve the "id" field from the JSON object and converts it to an integer
                String name = (String) patientObject.get("name"); //Retrieve the "name" field from the JSON object and convert it to a string
                String disease = (String) patientObject.get("disease");

                Patient patient = new Patient(id, name, disease); //Create a Patient object using the extracted information
                data.put(id, patient); //Adds the Patient object to the map
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        JSONArray patientsArray = new JSONArray(); //Create a new JSONArray to store patient information
        for (Patient patient : data.values()) // Iterate over Patient objects stored in the map
        {
            JSONObject patientObject = new JSONObject(); //Create a JSONObject for each patient
            patientObject.put("id", patient.getId()); //Add the patient's ID to the JSON object
            patientObject.put("name", patient.getName()); //Add the patient's name to the JSON object
            patientObject.put("disease", patient.getDisease());

            patientsArray.add(patientObject); //Add the patient's JSON object to the array
        }

        try (FileWriter fileWriter = new FileWriter(filename)) //Create a FileWriter to write to the specified filename
        {
            fileWriter.write(patientsArray.toJSONString()); //Write the JSON array as a string to the file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}