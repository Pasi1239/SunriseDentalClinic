package com.dental.controller;

import java.io.IOException;

import com.dental.dao.AppointmentDAO;
import com.dental.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Appointment")
public class AppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AppointmentServlet() {
        super();
    }

    // SEARCH APPOINTMENT
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("appointmentId");

        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect("appointment.jsp?error=Please Enter Appointment ID");
            return;
        }

        int appointmentId = Integer.parseInt(id);

        AppointmentDAO dao = new AppointmentDAO();
        Appointment appointment = dao.searchAppointment(appointmentId);

        if (appointment == null) {
            response.sendRedirect("appointment.jsp?error=Appointment Not Found");
            return;
        }

        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher("appointment.jsp").forward(request, response);
    }

    // SAVE / UPDATE / DELETE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.trim().isEmpty()) {
            response.sendRedirect("appointment.jsp?error=Invalid Action");
            return;
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(Integer.parseInt(request.getParameter("appointmentId")));
        appointment.setPatientName(request.getParameter("patientName"));
        appointment.setAddress(request.getParameter("address"));
        appointment.setContact(request.getParameter("contact"));
        appointment.setDentistName(request.getParameter("dentistName"));
        appointment.setTreatmentType(request.getParameter("treatmentType"));
        appointment.setAppointmentDate(request.getParameter("appointmentDate"));
        appointment.setAppointmentTime(request.getParameter("appointmentTime"));

        AppointmentDAO dao = new AppointmentDAO();

        boolean status = false;

        switch (action) {

        case "save":
            status = dao.addAppointment(appointment);
            break;

        case "update":
            status = dao.updateAppointment(appointment);
            break;

        case "delete":
            status = dao.deleteAppointment(appointment.getAppointmentId());
            break;

        default:
            response.sendRedirect("appointment.jsp?error=Invalid Action");
            return;
        }

        if (status) {

            switch (action) {

            case "save":
                response.sendRedirect("appointment.jsp?success=Appointment Saved Successfully");
                break;

            case "update":
                response.sendRedirect("appointment.jsp?success=Appointment Updated Successfully");
                break;

            case "delete":
                response.sendRedirect("appointment.jsp?success=Appointment Deleted Successfully");
                break;
            }

        } else {

            response.sendRedirect("appointment.jsp?error=Operation Failed");

        }
    }
}