package Domain;

import java.io.Serializable;

public class Patient implements Identifiable<Integer> , Serializable {
    Integer id;
    private String name;
    private String disease;

    //private Appointment[] appointments;



    public Patient(Integer id, String name, String disease) {
        //super(id); //calling the constructor from the base class
        this.id = id;
        this.name = name;
        this.disease = disease;
        //this.appointments = appointments;
    }

    //GETTERS
    public String getName()
    {
        return this.name;
    }

    public String getDisease(){return this.disease;}

    //public Appointment[] getAppointments() {return this.appointments;}

    //public int getId(){return this.id;}

    //SETTERS

    @Override
    public Integer getId(){return id;}

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String name1) {this.name = name1;}
    //public void setId(int id1) {this.id = id1;}


    public void setDisease(String disease) {this.disease = disease;}

    //public void setAppointments(Appointment[] appointments) {this.appointments = appointments;}


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                //", appointments=" + Arrays.toString(appointments) +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Patient))
            return false;
        Patient d = (Patient)o;
        if (d.id==this.id && d.name.equals(this.name))
            return true;
        return false;
    }

}
