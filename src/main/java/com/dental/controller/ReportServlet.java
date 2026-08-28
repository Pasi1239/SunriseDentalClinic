package com.dental.controller;

import java.io.IOException;
import java.util.List;

import com.dental.dao.AppointmentDAO;
import com.dental.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ReportServlet
 * ------------------------------------------------------------------
 * Generates the "All Appointments Report".
 *
 * This satisfies the assessment requirement (Task B):
 *   "Come up with a suitable set of reports, which you think add
 *    more value to your system"
 *
 * It reuses the EXISTING AppointmentDAO.getAllAppointments() method
 * (the same one used by the REST web service), so no duplicate
 * database logic is introduced. It simply fetches the list and
 * forwards it to report.jsp for display.
 *
 * This is completely SEPARATE from AppointmentServlet / BillingServlet,
 * so none of the existing functionality is touched.
 */
@WebServlet("/Report")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointmentList = dao.getAllAppointments();

        request.setAttribute("appointmentList", appointmentList);

        request.getRequestDispatcher("report.jsp").forward(request, response);
    }
}
