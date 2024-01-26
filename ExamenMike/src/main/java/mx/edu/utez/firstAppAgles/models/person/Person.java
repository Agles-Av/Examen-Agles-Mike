package mx.edu.utez.firstAppAgles.models.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "People")
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String surame;
    @Column(length = 50)
    private String lastname;
    @Column(columnDefinition = "DATE",nullable = false)
    @Temporal(TemporalType.DATE) //no de error a la hora de get los datos
    private LocalDate birthdate;
    @Column(length = 50, nullable = false)
    private String estado;
    @Column(length = 18,nullable = false)
    private String sexo;
    @Column(length = 18,nullable = false,unique = true)
    private String curp;

    public Person(Long id, String name, String surame, String lastname, LocalDate birthdate, String estado, String sexo, String curp) {
        this.id = id;
        this.name = name;
        this.surame = surame;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.estado = estado;
        this.sexo = sexo;
        this.curp = curp;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surame='" + surame + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", estado='" + estado + '\'' +
                ", sexo='" + sexo + '\'' +
                ", curp='" + curp + '\'' +
                '}';
    }
}
