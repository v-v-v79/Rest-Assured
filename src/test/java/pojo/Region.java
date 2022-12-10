package pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
public class Region {

    private int region_id;
    private String region_name;
    private List<Link> links;

}
