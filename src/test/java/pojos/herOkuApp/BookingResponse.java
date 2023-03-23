package pojos.herOkuApp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {

    private Integer bookingid;
    private Booking booking;

    /*
    {
       "bookingid": 9268,
       "booking": {
           "firstname": "Jim",
           "lastname": "Brown",
           "totalprice": 111,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2018-01-01",
               "checkout": "2019-01-01"
           },
           "additionalneeds": "Breakfast"
            }
     }
     */
}
