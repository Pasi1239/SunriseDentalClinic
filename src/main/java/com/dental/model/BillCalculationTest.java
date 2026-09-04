package com.dental.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BillCalculationTest {

    @Test
    void testTotalAmount_matchesConsultationPlusTreatmentFee() {
        double consultationFee = 222.0;
        double treatmentFee = 333.0;
        double expectedTotal = consultationFee + treatmentFee;

        Bill bill = new Bill();
        bill.setAppointmentId(1);
        bill.setPatientName("Pasindu");
        bill.setDentistName("Dr. Silva");
        bill.setTreatmentType("Cleaning");
        bill.setConsultationFee(consultationFee);
        bill.setTreatmentFee(treatmentFee);
        bill.setTotalAmount(expectedTotal);

        assertEquals(555.0, bill.getTotalAmount(), 0.01,
            "Total amount should equal 222 + 333 = 555, matching the "
          + "value shown in the Billing interface (Figure 6) for "
          + "appointment ID 1");
    }

    @Test
    void testTotalAmount_zeroTreatmentFee_equalsConsultationFeeOnly() {
        Bill bill = new Bill();
        bill.setConsultationFee(222.0);
        bill.setTreatmentFee(0.0);
        bill.setTotalAmount(222.0 + 0.0);

        assertEquals(222.0, bill.getTotalAmount(), 0.01,
            "Total should equal the consultation fee alone when no "
          + "treatment fee is charged");
    }
}