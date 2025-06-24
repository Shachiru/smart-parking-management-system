package lk.ijse.paymentservice.api;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentDTO> initiatePayment(@RequestParam UUID userId, @RequestParam UUID parkingSpaceId,
                                                      @RequestParam Double amount, @RequestParam String paymentMethod) {
        PaymentDTO payment = paymentService.initiatePayment(userId, parkingSpaceId, amount, paymentMethod);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/simulate/{paymentId}")
    public ResponseEntity<PaymentDTO> simulateTransaction(@PathVariable UUID paymentId, @RequestParam String transactionStatus) {
        PaymentDTO payment = paymentService.simulateTransaction(paymentId, transactionStatus);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/generate-receipt/{paymentId}")
    public ResponseEntity<PaymentDTO> generateReceipt(@PathVariable UUID paymentId) {
        PaymentDTO payment = paymentService.generateReceipt(paymentId);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.badRequest().build();
    }
}
