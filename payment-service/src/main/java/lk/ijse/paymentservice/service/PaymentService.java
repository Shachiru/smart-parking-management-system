package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentDTO;

import java.util.UUID;

public interface PaymentService {
    PaymentDTO initiatePayment(UUID userId, UUID parkingSpaceId, Double amount, String paymentMethod);

    PaymentDTO simulateTransaction(UUID paymentId, String transactionStatus);

    PaymentDTO generateReceipt(UUID paymentId);
}
