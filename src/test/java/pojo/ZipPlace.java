package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class ZipPlace {

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("latitude")
    private String latitude;
}
