
package guru.learningjournal.examples.kafka.advertclicks.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClicksByNewsType {

    @JsonProperty("NewsType")
    private String newsType;
    @JsonProperty("Clicks")
    private Long clicks;

}
