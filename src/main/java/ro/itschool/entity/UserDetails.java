package ro.itschool.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String firstName;
    private String lastName;
    private String passportNumber;
}
