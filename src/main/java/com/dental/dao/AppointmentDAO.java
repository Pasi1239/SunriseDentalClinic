package com.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dental.model.Appointment;
import com.dental.util.DBConnection;

import java.util.ArrayList;
import java.util.List;




public class AppointmentDAO {

    // Add Appointment
    public boolean addAppointment(Appointment appointment) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO appointments "
                    + "(appointment_id, patient_name, address, contact, dentist_name, treatment_type, appointment_date, appointment_time) "
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, appointment.getAppointmentId());
            ps.setString(2, appointment.getPatientName());
            ps.setString(3, appointment.getAddress());
            ps.setString(4, appointment.getContact());
            ps.setString(5, appointment.getDentistName());
            ps.setString(6, appointment.getTreatmentType());
            ps.setString(7, appointment.getAppointmentDate());
            ps.setString(8, appointment.getAppointmentTime());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Search Appointment
    public Appointment getAppointment(int id) {

        Appointment appointment = null;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM appointments WHERE appointment_id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                appointment = new Appointment();

                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setPatientName(rs.getString("patient_name"));
                appointment.setAddress(rs.getString("address"));
                appointment.setContact(rs.getString("contact"));
                appointment.setDentistName(rs.getString("dentist_name"));
                appointment.setTreatmentType(rs.getString("treatment_type"));
                appointment.setAppointmentDate(rs.getString("appointment_date"));
                appointment.setAppointmentTime(rs.getString("appointment_time"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointment;
    }




public Appointment searchAppointment(int appointmentId) {

    Appointment appointment = null;

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM appointments WHERE appointment_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, appointmentId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            appointment = new Appointment();

            appointment.setAppointmentId(rs.getInt("appointment_id"));
            appointment.setPatientName(rs.getString("patient_name"));
            appointment.setAddress(rs.getString("address"));
            appointment.setContact(rs.getString("contact"));
            appointment.setDentistName(rs.getString("dentist_name"));
            appointment.setTreatmentType(rs.getString("treatment_type"));
            appointment.setAppointmentDate(rs.getString("appointment_date"));
            appointment.setAppointmentTime(rs.getString("appointment_time"));

        }

        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return appointment;

}

//Update Appointment
public boolean updateAppointment(Appointment appointment) {

 boolean status = false;

 try {

     Connection con = DBConnection.getConnection();

     String sql = "UPDATE appointments SET patient_name=?, address=?, contact=?, dentist_name=?, treatment_type=?, appointment_date=?, appointment_time=? WHERE appointment_id=?";

     PreparedStatement ps = con.prepareStatement(sql);

     ps.setString(1, appointment.getPatientName());
     ps.setString(2, appointment.getAddress());
     ps.setString(3, appointment.getContact());
     ps.setString(4, appointment.getDentistName());
     ps.setString(5, appointment.getTreatmentType());
     ps.setString(6, appointment.getAppointmentDate());
     ps.setString(7, appointment.getAppointmentTime());
     ps.setInt(8, appointment.getAppointmentId());

     status = ps.executeUpdate() > 0;

     con.close();

 } catch (Exception e) {

     e.printStackTrace();

 }

 return status;
}




public boolean deleteAppointment(int appointmentId) {

    boolean status = false;

    try {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM appointments WHERE appointment_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, appointmentId);

        status = ps.executeUpdate() > 0;

        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return status;
}


// Get ALL appointments (used by the REST web service / reports)
public List<Appointment> getAllAppointments() {

    List<Appointment> list = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM appointments ORDER BY appointment_id";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Appointment appointment = new Appointment();

            appointment.setAppointmentId(rs.getInt("appointment_id"));
            appointment.setPatientName(rs.getString("patient_name"));
            appointment.setAddress(rs.getString("address"));
            appointment.setContact(rs.getString("contact"));
            appointment.setDentistName(rs.getString("dentist_name"));
            appointment.setTreatmentType(rs.getString("treatment_type"));
            appointment.setAppointmentDate(rs.getString("appointment_date"));
            appointment.setAppointmentTime(rs.getString("appointment_time"));

            list.add(appointment);
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


}
