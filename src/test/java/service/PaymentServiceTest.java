package service;

import enums.PaymentStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import enums.PaymentMethod;
import model.Order;
import model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import repository.OrderRepository;
import repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("dummyId");
        product1.setProductName ("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("dummyId1", products, 1708560000L, "Sabab Sudrajat");
        orders.add(order1);

        Order order2 = new Order ("dummyId2", products, 1708570000L, "Sabab Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Map<String, String> paymentDataVoucher = new HashMap<>();
        Map<String, String> paymentDataCOD = new HashMap<>();
        paymentDataVoucher.put("VoucherCode", "ESHOP1234ABC5678");
        paymentDataCOD.put("address", "Depok");
        paymentDataCOD.put("deliveryFee", "10");
        Payment payment1 = new Payment(order1.getId(), PaymentMethod.COD.getValue(),  paymentDataCOD);
        payments.add(payment1);
        Payment payment2 = new Payment(order2.getId(), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        payments.add(payment2);
    }

    @Test
    void testSavePayment() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testGetPaymentIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIdNotFound() {
        doReturn(null).when(paymentRepository).getPayment("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List <Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).getAllPayments();
        assertEquals(0, result.size());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        order.setStatus(PaymentStatus.SUCCESS.getValue());
        Payment editedStatusPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.SUCCESS.getValue(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).save(any(Order.class));
        doReturn(order).when(orderRepository).findById(payment.getId());
        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).findById(payment.getId());
        assertEquals(editedStatusPayment.getStatus(), result.getStatus());
        assertEquals(PaymentStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        order.setStatus("FAILED");
        Payment editedStatusPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).save(any(Order.class));
        doReturn(order).when(orderRepository).findById(payment.getId());
        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderRepository, times(1)).findById(payment.getId());
        assertEquals(editedStatusPayment.getStatus(), result.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = payments.get(1);
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "MEOW"));
    }
}