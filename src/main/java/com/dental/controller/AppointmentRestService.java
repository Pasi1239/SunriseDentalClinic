package com.dental.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.dental.dao.AppointmentDAO;
import com.dental.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AppointmentRestService
 * ------------------------------------------------------------------
 * A lightweight REST-style web service that exposes appointment data
 * as JSON. This fulfils the assessment requirement:
 *      "Your program must be a distributed application with web services"
 *
 * It is completely SEPARATE from AppointmentServlet (which powers the
 * JSP-based UI), so the existing login / appointment / billing pages
 * are untouched and continue to work exactly as before.
 *
 * How to test it:
 *   1. Run the project on the server (e.g. http://localhost:8080/SunriseDentalClinics/)
 *   2. Open this URL directly in a browser:
 *          http://localhost:8080/SunriseDentalClinics/api/appointments
 *   3. You will see the appointment list returned as JSON text -
 *      this is your "web service" response, consumable by any client
 *      (browser, mobile app, another system) - not just this JSP UI.
 *      This demonstrates the "distributed application" aspect, since
 *      any external client/device on the network could call this URL
 *      and consume the data without using the HTML pages at all.
 */
@WebServlet("/api/appointments")
public class AppointmentRestService extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tell the client this response is JSON (standard web service practice)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointments = dao.getAllAppointments();

        String json = toJsonArray(appointments);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    /**
     * Converts a list of Appointment objects into a JSON array string.
     * Built manually (no external library) so no new dependency is
     * introduced into the project.
     */
    private String toJsonArray(List<Appointment> appointments) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < appointments.size(); i++) {

            Appointment a = appointments.get(i);

            sb.append("{");
            sb.append("\"appointmentId\":").append(a.getAppointmentId()).append(",");
            sb.append("\"patientName\":\"").append(escape(a.getPatientName())).append("\",");
            sb.append("\"address\":\"").append(escape(a.getAddress())).append("\",");
            sb.append("\"contact\":\"").append(escape(a.getContact())).append("\",");
            sb.append("\"dentistName\":\"").append(escape(a.getDentistName())).append("\",");
            sb.append("\"treatmentType\":\"").append(escape(a.getTreatmentType())).append("\",");
            sb.append("\"appointmentDate\":\"").append(escape(a.getAppointmentDate())).append("\",");
            sb.append("\"appointmentTime\":\"").append(escape(a.getAppointmentTime())).append("\"");
            sb.append("}");

            if (i < appointments.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    // Escapes double quotes/backslashes so the JSON stays valid
    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
