package guru.learningjournal.examples.kafka.otpvalidation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentConfirmation {
    @JsonProperty("TransactionID")
    private String transactionID;
    @JsonProperty("CreatedTime")
    private Long createdTime;
    @JsonProperty("OTP")
    private String OTP;
}
