package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelDetails {

    private String origin;

    private String destination;

//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime departureDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime returnDate;

}
