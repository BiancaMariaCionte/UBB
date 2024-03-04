package src.repository;

import src.domain.Appointment;

import java.util.ArrayList;

public class AppointmentsRepository {
    public ArrayList<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment app)
    {
        appointments.add(app);

    }
    public void cancelAppointment(int gid)
    {
        appointments.removeIf(appointment -> appointment.getId()== gid);
    }
}

