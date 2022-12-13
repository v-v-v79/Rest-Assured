package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Students {

    private String firstName;
    private String batch;
    private String major;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contacts {

        private String emailAddress;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Company {

        private String companyName;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Address {

            private String street;
            private String zipCode;
        }
    }
}

