package lk.ijse.paymentservice.repo;

import lk.ijse.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,UUID> {
    Optional<Payment> findByPaymentId(UUID paymentId);

    Optional<Payment> findByUserIdAndParkingSpaceId(UUID userId, UUID parkingSpaceId);
}
