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
import jakarta.servlet.http.HttpSession;

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
 * SECURITY:
 *   The scenario requires "Only authorized staff can use the system".
 *   This endpoint now checks for a valid logged-in session (the same
 *   session created by LoginServlet) before returning any patient
 *   data. An unauthenticated request receives HTTP 401 Unauthorized
 *   with a small JSON error body instead of the appointment list.
 *
 * How to test it:
 *   1. Run the project on the server (e.g. http://localhost:8080/SunriseDentalClinics/)
 *   2. Log in through login.jsp first (this creates the session).
 *   3. Open this URL in the SAME browser session:
 *          http://localhost:8080/SunriseDentalClinics/api/appointments
 *      -> You will see the appointment list returned as JSON text.
 *   4. Open the same URL in a private/incognito window (no login):
 *      -> You will get a 401 Unauthorized JSON error instead of data.
 */
@WebServlet("/api/appointments")
public class AppointmentRestService extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // ---- Authentication check ----
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            PrintWriter out = response.getWriter();
            out.print("{\"error\":\"Unauthorized. Please log in to access this resource.\"}");
            out.flush();
            return;
        }
        // ---- End authentication check ----

        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointments = dao.getAllAppointments();

        String json = toJsonArray(appointments);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

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

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}