package com.dental.controller;

import java.io.IOException;

import com.dental.dao.AppointmentDAO;
import com.dental.dao.BillDAO;
import com.dental.model.Appointment;
import com.dental.model.Bill;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Billing")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public BillingServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Appointment Search
        String appointmentId = request.getParameter("appointmentId");

        if (appointmentId != null && !appointmentId.trim().isEmpty()) {

            try {

                AppointmentDAO appointmentDAO = new AppointmentDAO();

                Appointment appointment =
                        appointmentDAO.searchAppointment(Integer.parseInt(appointmentId));

                if (appointment != null) {

                    request.setAttribute("appointment", appointment);

                } else {

                    request.setAttribute("error", "Appointment Not Found");

                }

            } catch (Exception e) {

                request.setAttribute("error", "Invalid Appointment ID");

            }

        }

        // Bill Search
        String billId = request.getParameter("billId");

        if (billId != null && !billId.trim().isEmpty()) {

            try {

                BillDAO billDAO = new BillDAO();

                Bill bill = billDAO.searchBill(Integer.parseInt(billId));

                if (bill != null) {

                    request.setAttribute("bill", bill);

                } else {

                    request.setAttribute("error", "Bill Not Found");

                }

            } catch (Exception e) {

                request.setAttribute("error", "Invalid Bill ID");

            }

        }

        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }

    // Save Bill
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bill bill = new Bill();

        bill.setAppointmentId(
                Integer.parseInt(request.getParameter("appointmentId")));

        bill.setPatientName(request.getParameter("patientName"));

        bill.setDentistName(request.getParameter("dentistName"));

        bill.setTreatmentType(request.getParameter("treatmentType"));

        bill.setConsultationFee(
                Double.parseDouble(request.getParameter("consultationFee")));

        bill.setTreatmentFee(
                Double.parseDouble(request.getParameter("treatmentFee")));

        bill.setTotalAmount(
                Double.parseDouble(request.getParameter("totalAmount")));

        bill.setBillDate(request.getParameter("billDate"));

        BillDAO dao = new BillDAO();

        boolean status = dao.saveBill(bill);

        if (status) {

            response.sendRedirect("billing.jsp?success=Bill Saved Successfully");

        } else {

            response.sendRedirect("billing.jsp?error=Failed To Save Bill");

        }
    }
    
    
}