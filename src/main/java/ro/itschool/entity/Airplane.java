package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Setter
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {

    private String planeNumber;
    private String seatNumber;

}
