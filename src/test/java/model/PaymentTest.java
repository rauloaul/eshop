package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    @Test
    public void testVoucherPaymentDefault() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1", "VOUCHER", paymentData);

        assertTrue(payment.getPaymentData().containsKey("VoucherCode"));
        assertEquals("1", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("VoucherCode"));
        assertEquals("CHECKING_PAYMENT", payment.getStatus());
    }

    @Test
    public void testCODPaymentDefault(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "5");
        paymentData.put("address", "Depok");
        Payment payment = new Payment("dummyId", "CASH", paymentData);

        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertEquals("CHECKING_PAYMENT", payment.getStatus());
        assertEquals("dummyId", payment.getId());
        assertEquals("CASH", payment.getMethod());
        assertEquals("Depok", payment.getPaymentData().get("address"));
        assertEquals("5", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    public void testCreatePaymentSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("dummyId", "VOUCHER", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testCreatePaymentInvalidStatus(){
        assertThrows (IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("VoucherCode", "ESHOP1234ABC5678");
            Payment payment = new Payment("dummyId", "VOUCHER", "INVALID_STATUS" ,paymentData);
        });
    }

    @Test
    public void testPaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("dummyId", "VOUCHER", paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }

    @Test
    public void testPaymentRejectStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("dummyId", "VOUCHER", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}