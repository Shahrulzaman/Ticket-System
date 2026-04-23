import enums.PaymentMethod;
import exceptions.PaymentException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a payment transaction for a ticket booking.
 */
public class Payment {
    private static int paymentCounter = 5000;
    private int paymentId;
    private double amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDateTime;
    private String transactionId;
    private boolean isSuccessful;

    public Payment(double amount, PaymentMethod paymentMethod) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Payment amount must be positive");
        }
        if (paymentMethod == null) {
            throw new PaymentException("Payment method cannot be null");
        }

        this.paymentId = ++paymentCounter;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDateTime = LocalDateTime.now();
        this.transactionId = generateTransactionId();
        this.isSuccessful = false;
    }

    private String generateTransactionId() {
        return "TXN" + paymentId + System.currentTimeMillis();
    }

    public boolean processPayment() {
        // Simulate payment processing
        this.isSuccessful = true;
        return true;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return paymentDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Payment ID: " + paymentId +
               " | Amount: RM" + String.format("%.2f", amount) +
               " | Method: " + paymentMethod.getDisplayName() +
               " | Status: " + (isSuccessful ? "SUCCESS" : "PENDING");
    }
}
