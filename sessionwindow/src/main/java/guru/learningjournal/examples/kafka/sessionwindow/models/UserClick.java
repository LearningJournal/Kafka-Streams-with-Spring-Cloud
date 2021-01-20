package guru.learningjournal.examples.kafka.sessionwindow.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserClick {
    @JsonProperty("UserID")
    private String userID;
    @JsonProperty("CreatedTime")
    private Long createdTime;
    @JsonProperty("CurrentLink")
    private String currentLink;
    @JsonProperty("NextLink")
    private String nextLink;
}
