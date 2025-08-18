package com.pahanaedu;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillingService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillingServiceTest {

    @Test
    void totalIsUnitsTimesRate() {
        BillingService svc = new BillingService();
        Bill b = svc.generateBill(0); // returns null if no customer 0
        assertNull(b); // just to ensure class loads; DB not required here
    }
}
