package src.repository;

import src.domain.Patient;

import java.io.IOError;
import java.util.ArrayList;
import java.util.Iterator;

public class Repository implements RepoInterface{

    //private ArrayList<T> patients = new ArrayList<T>();
    private ArrayList<Patient> patients = new ArrayList<>();

   // @Override
//    public void addPatients(T patient) {
//        for (T object: patients)
//            if(patient.getId()==object.getId())
//                   return;
//        patients.add(patient);
//    }
    @Override
    public void addPatients(Patient patient) {
        patients.add(patient);

    }
    @Override
    public void deletePatient(int patientId) {
        //Iterator<T> iterator = patients.iterator();
        Iterator<Patient> iterator = patients.iterator();

        try
        {
            while (iterator.hasNext()) {
                //T patient = iterator.next();
                Patient patient = iterator.next();
                if (patient.getId() == patientId) {
                    iterator.remove();
                }
            }
        }catch(IOError e) {
            System.out.println("Something went wrong.");
        }



    }


    @Override
    public ArrayList<Patient> getAllPatients() {

        return this.patients;
    }



    @Override
    public void updatePatient(Patient updatedPatient)
    {
        for(int i =0;i< patients.size();i++)
        {
            Patient patient = patients.get(i);
            if(patient.getId()==updatedPatient.getId())
            {
                patients.set(i, updatedPatient);
                break;
            }
        }
    }

    public static void main(String[] args) {
        RepoInterface repoInterface = new Repository();
        //RepoInterface repo = new AmRepo();
    }
}