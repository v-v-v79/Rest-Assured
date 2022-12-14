package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @Data
    //@JsonIgnoreProperties(value = "links", allowSetters = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Regions {

        private int region_id;
        private String region_name;
    }

}
