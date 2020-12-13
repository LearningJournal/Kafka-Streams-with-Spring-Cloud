package guru.learningjournal.examples.kafka.avroposfanout.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HadoopRecord {

    @JsonProperty("InvoiceNumber")
    private String InvoiceNumber;
    @JsonProperty("CreatedTime")
    private Long CreatedTime;
    @JsonProperty("StoreID")
    private String StoreID;
    @JsonProperty("PosID")
    private String PosID;
    @JsonProperty("CustomerType")
    private String CustomerType;
    @JsonProperty("PaymentMethod")
    private String PaymentMethod;
    @JsonProperty("DeliveryType")
    private String DeliveryType;
    @JsonProperty("City")
    private String City;
    @JsonProperty("State")
    private String State;
    @JsonProperty("PinCode")
    private String PinCode;
    @JsonProperty("ItemCode")
    private String ItemCode;
    @JsonProperty("ItemDescription")
    private String ItemDescription;
    @JsonProperty("ItemPrice")
    private Double ItemPrice;
    @JsonProperty("ItemQty")
    private Integer ItemQty;
    @JsonProperty("TotalValue")
    private Double TotalValue;
}
