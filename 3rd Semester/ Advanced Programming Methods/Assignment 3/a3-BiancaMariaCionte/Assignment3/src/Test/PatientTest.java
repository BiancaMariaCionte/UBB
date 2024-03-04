package Test;

import Domain.Appointment;
import Domain.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PatientTest {
    private Patient p;
    private Appointment a;

    @BeforeEach
    public void setUpAppointment() {
        a = new Appointment(2000, "Monthly check for cancer.", "23/06/2024");
    }

    @BeforeEach
    public void setUpPatient() {
        p = new Patient(1000, "Cionte Bianca", "cancer");
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("Cionte Bianca", p.getName());
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals(1000, p.getId());
    }

    @Test
    public void testGetDisease() {
        Assertions.assertEquals("cancer", p.getDisease());
    }

//    @Test
//    public void testGetAppointments() {
//        Assertions.assertTrue(Arrays.equals(p.getAppointments(), new Appointment[]{a}));
//    }

    @Test
    public void testSetName() {
        p.setName("New Name");
        Assertions.assertEquals("New Name", p.getName());
    }

    @Test
    public void testSetDisease() {
        p.setDisease("leukemia");
        Assertions.assertEquals("leukemia", p.getDisease());
    }

//    @Test
//    public void testSetAppointments() {
//        Appointment newAppointment = new Appointment(3000, "Yearly checkup", "01/01/2025");
//        p.setAppointments(new Appointment[]{newAppointment});
//
//        Assertions.assertTrue(Arrays.equals(p.getAppointments(), new Appointment[]{newAppointment}));
//    }

    @Test
    public void testSetId() {
        p.setId(1001);
        Assertions.assertEquals(1001, p.getId());
    }
}