package src.domain;

public class Appointment {
    private int Id;
    private String description;

    private String date;

    public int getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Appointment(int id, String description, String date) {
        this.Id = id;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
