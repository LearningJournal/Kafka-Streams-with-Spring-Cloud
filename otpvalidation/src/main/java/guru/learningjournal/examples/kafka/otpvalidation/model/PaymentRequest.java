package guru.learningjournal.examples.kafka.otpvalidation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequest {

    @JsonProperty("TransactionID")
    private String transactionID;
    @JsonProperty("CreatedTime")
    private Long createdTime;
    @JsonProperty("SourceAccountID")
    private String sourceAccountID;
    @JsonProperty("TargetAccountID")
    private String targetAccountID;
    @JsonProperty("Amount")
    private Double amount;
    @JsonProperty("OTP")
    private String OTP;
}
