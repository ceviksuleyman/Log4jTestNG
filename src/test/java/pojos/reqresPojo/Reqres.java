package pojos.reqresPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reqres {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private DataReqres data;
    private SupportReqres support;


}
