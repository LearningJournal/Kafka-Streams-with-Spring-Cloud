package guru.learningjournal.examples.kafka.otpvalidation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionStatus {
    @JsonProperty("TransactionID")
    private String transactionID;
    @JsonProperty("Status")
    private String status;
}

