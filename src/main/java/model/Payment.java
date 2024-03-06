package model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method,Map<String, String> paymentData){
        this.id = id;
        this.setMethod(method);
        this.status = PaymentStatus.CHECKING_PAYMENT.getValue();
        this.paymentData = paymentData;
    }

    public Payment(String id, String method,String status, Map<String, String> paymentData){
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (status.equals(PaymentStatus.SUCCESS.getValue()) || status.equals(PaymentStatus.REJECTED.getValue())){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setMethod(String method) {
        if (method.equals(PaymentMethod.COD.getValue()) || method.equals(PaymentMethod.VOUCHER.getValue())){
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
    }
}