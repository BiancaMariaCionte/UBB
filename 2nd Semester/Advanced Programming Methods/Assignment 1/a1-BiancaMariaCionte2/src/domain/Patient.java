package src.domain;

public class Patient{
    private int id;
    private String name;
    private String dateOfBirth;



    public Patient(int id, String name, String dateOfBirth)
    {
        this.id =id; //calling the constructor from the base class
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        //this.id = id;
    }

    //GETTERS
    String getName()
    {
        return this.name;
    }
    String getDateOfBirth(){return this.dateOfBirth;}

    public int getId(){return this.id;}

    //SETTERS
    public void setName(String name1) {this.name = name1;}
    public void setId(int id1) {this.id = id1;}
    public void setDateOfBirth(String dateOfBirth1){this.dateOfBirth=dateOfBirth1;}



    @Override
    public String toString() {
        return "Patient(" +
                "id:'" + id + '\'' +
                ", name: " + name + '\'' +
                ", dateOfBirth:" + dateOfBirth + '\'' +
                ')';
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof Patient))
            return false;
        Patient d = (Patient)o;
        if (d.id==this.id && d.name.equals(this.name) &&
                d.dateOfBirth.equals(this.dateOfBirth))
            return true;
        return false;
    }

}
