package com.dental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dental.model.Bill;
import com.dental.util.DBConnection;
import java.sql.ResultSet;

public class BillDAO {

    // Save Bill
    public boolean saveBill(Bill bill) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO bills (appointment_id, patient_name, dentist_name, treatment_type, consultation_fee, treatment_fee, total_amount, bill_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bill.getAppointmentId());
            ps.setString(2, bill.getPatientName());
            ps.setString(3, bill.getDentistName());
            ps.setString(4, bill.getTreatmentType());
            ps.setDouble(5, bill.getConsultationFee());
            ps.setDouble(6, bill.getTreatmentFee());
            ps.setDouble(7, bill.getTotalAmount());
            ps.setString(8, bill.getBillDate());

            status = ps.executeUpdate() > 0;

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    
 // Search Bill
    public Bill searchBill(int billId) {

        Bill bill = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM bills WHERE bill_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, billId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                bill = new Bill();

                bill.setBillId(rs.getInt("bill_id"));
                bill.setAppointmentId(rs.getInt("appointment_id"));
                bill.setPatientName(rs.getString("patient_name"));
                bill.setDentistName(rs.getString("dentist_name"));
                bill.setTreatmentType(rs.getString("treatment_type"));
                bill.setConsultationFee(rs.getDouble("consultation_fee"));
                bill.setTreatmentFee(rs.getDouble("treatment_fee"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setBillDate(rs.getString("bill_date"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bill;
    }
}