
package guru.learningjournal.examples.kafka.jsonposgen.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem {

    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("ItemDescription")
    private String itemDescription;
    @JsonProperty("ItemPrice")
    private Double itemPrice;
    @JsonProperty("ItemQty")
    private Integer itemQty;
    @JsonProperty("TotalValue")
    private Double totalValue;

}
