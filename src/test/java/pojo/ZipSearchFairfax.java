package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZipSearchFairfax {

    private List<ZipPlace> places;

    private String country;

    @JsonProperty("place name")
    private String  placeName;
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private int totalElements;
}
