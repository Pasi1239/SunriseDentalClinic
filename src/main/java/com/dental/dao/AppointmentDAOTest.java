package com.dental.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.dental.model.Appointment;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentDAOTest {

    private static final int TEST_ID = 9001;
    private static AppointmentDAO appointmentDAO;

    @BeforeAll
    static void setUp() {
        appointmentDAO = new AppointmentDAO();
    }

    @Test
    @Order(1)
    void testAddAppointment_validData_returnsTrue() {
        Appointment appointment = new Appointment(
            TEST_ID, "JUnit Test Patient", "Colombo 05", "0771234567",
            "Dr. Silva", "Cleaning", "2026-09-10", "10:00:00"
        );

        boolean result = appointmentDAO.addAppointment(appointment);

        assertTrue(result,
            "addAppointment() should return true when a new, valid "
          + "appointment is inserted into the appointments table");
    }

    @Test
    @Order(2)
    void testGetAppointment_existingId_returnsCorrectPatient() {
        Appointment appointment = appointmentDAO.getAppointment(TEST_ID);

        assertNotNull(appointment,
            "getAppointment() should find the record just inserted by "
          + "testAddAppointment_validData_returnsTrue()");
        assertEquals("JUnit Test Patient", appointment.getPatientName(),
            "Patient name returned should match the value that was saved");
        assertEquals("Dr. Silva", appointment.getDentistName(),
            "Dentist name returned should match the value that was saved");
    }

    @Test
    @Order(3)
    void testGetAppointment_nonExistentId_returnsNull() {
        Appointment appointment = appointmentDAO.getAppointment(999999);

        assertNull(appointment,
            "getAppointment() should return null for an appointment_id "
          + "that does not exist in the database");
    }

    @Test
    @Order(4)
    void testSearchAppointment_matchesGetAppointment() {
        Appointment viaSearch = appointmentDAO.searchAppointment(TEST_ID);

        assertNotNull(viaSearch,
            "searchAppointment() should also find the seeded test record");
        assertEquals("JUnit Test Patient", viaSearch.getPatientName(),
            "searchAppointment() should return the same patient name as "
          + "getAppointment() for the same appointment_id");
    }

    @Test
    @Order(5)
    void testUpdateAppointment_changesPersist() {
        Appointment appointment = appointmentDAO.getAppointment(TEST_ID);
        appointment.setTreatmentType("Root Canal");

        boolean updated = appointmentDAO.updateAppointment(appointment);
        assertTrue(updated,
            "updateAppointment() should return true when the update "
          + "affects one existing row");

        Appointment reloaded = appointmentDAO.getAppointment(TEST_ID);
        assertEquals("Root Canal", reloaded.getTreatmentType(),
            "Updated treatment type should be persisted and visible on "
          + "the next read");
    }

    @Test
    @Order(6)
    void testGetAllAppointments_includesTestRecord() {
        var appointments = appointmentDAO.getAllAppointments();

        boolean found = appointments.stream()
            .anyMatch(a -> a.getAppointmentId() == TEST_ID);

        assertFalse(appointments.isEmpty(),
            "getAllAppointments() should return at least one record");
        assertTrue(found,
            "getAllAppointments() should include the test appointment "
          + "created earlier in this test class");
    }

    @Test
    @Order(7)
    void testDeleteAppointment_removesRecord() {
        boolean deleted = appointmentDAO.deleteAppointment(TEST_ID);
        assertTrue(deleted,
            "deleteAppointment() should return true when an existing "
          + "row is removed");

        Appointment afterDelete = appointmentDAO.getAppointment(TEST_ID);
        assertNull(afterDelete,
            "The appointment should no longer be retrievable after "
          + "deleteAppointment() has been called");
    }

    @AfterAll
    static void tearDown() {
        appointmentDAO.deleteAppointment(TEST_ID);
    }
}