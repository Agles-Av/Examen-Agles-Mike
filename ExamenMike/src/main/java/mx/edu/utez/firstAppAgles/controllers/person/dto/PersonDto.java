package mx.edu.utez.firstAppAgles.controllers.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstAppAgles.models.person.Person;


import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class PersonDto {
    Long id;
    private String name;
    private String surname;
    private String lastName;
    private LocalDate birthDate;
    private String estado;
    private String sexo;
    private String curp;


    public Person toEntity() {
        return new Person(id, name, surname, lastName, birthDate, estado, sexo, curp);
    }

}
