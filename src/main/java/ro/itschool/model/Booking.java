package ro.itschool.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Airplane airplane;

    @Embedded
    private TravelDetails travelDetails;

    @Embedded
    private UserInfo userInfo;

    @ManyToOne
    @JsonBackReference
    private LoggedUser loggedUser;

}
