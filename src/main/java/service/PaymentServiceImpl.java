package service;

import enums.OrderStatus;
import enums.PaymentStatus;
import model.Order;
import model.Payment;
import repository.OrderRepository;
import repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        paymentRepository.save(payment);
        return payment;
    }
    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);
        orderRepository.save(orderRepository.findById(payment.getId()));
        return payment;
    }
    private String checkStatus(String paymentStatus){
        if (paymentStatus.equals(PaymentStatus.SUCCESS.getValue())){
            return OrderStatus.SUCCESS.getValue();
        } else if (paymentStatus.equals(PaymentStatus.REJECTED.getValue())) {
            return OrderStatus.FAILED.getValue();
        }
        else if (paymentStatus.equals(PaymentStatus.CHECKING_PAYMENT.getValue())) {
            return OrderStatus.WAITING_PAYMENT.getValue();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

}