package mx.edu.utez.firstAppAgles.services.person;

import mx.edu.utez.firstAppAgles.config.ApiResponse;
import mx.edu.utez.firstAppAgles.models.person.Person;
import mx.edu.utez.firstAppAgles.models.person.PersonRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    String vocales = "";
    String consonantes = "";

    String[] estados = {"aguascalientes", "Morelos", "Guerrero", "Mexico"};
    String[] abreviaciones = {"AS", "MS", "GR", "MX"};

    //encontrar todos
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(
                new ApiResponse(personRepository.findAll(), HttpStatus.OK)
                , HttpStatus.OK);
    }
    //metodo para crear la curp

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> save(Person person) {
        //se creo el curp en base a otro curp de otro pproyecto
        String nombre = person.getName();
        String ape1 = person.getSurame();
        String ape2 = person.getLastname();
        String añoNac = person.getBirthdate().toString();
        String sexo = person.getSexo();
        String estado2letras = person.getEstado();
        String consonante = person.getSurame();
        Random random = new Random();
        int numero = random.nextInt(100);
        System.out.println(numero);
        String curp =
                ape1.charAt(0)+"" + vocales(ape1) +""+
                ape2.charAt(0) +""+
                nombre.charAt(0)+""
                + añoNac.substring(2, 4)
                + añoNac.substring(5, 7)
                + añoNac.substring(8, 10)
                + sexo
                + SaberAbreviacion(estado2letras)
                + consonantes(ape1)
                + consonantes(ape2)
                + consonantes(nombre)
                + numero;
        System.out.println(curp);
        person.setCurp(curp);

        //se guarda la persona pero validadno que sí este
        Optional<Person> foundPerson = personRepository.findByCurp(person.getCurp());
        System.out.println(foundPerson);
        if (foundPerson.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,
                    true,
                    "RecordAlreadyExists")
                    , HttpStatus.BAD_REQUEST);

        person = personRepository.saveAndFlush(person);

        return new ResponseEntity<>(
                new ApiResponse(personRepository.saveAndFlush(person),
                        HttpStatus.OK),
                HttpStatus.OK);

    }


    //encontrar por curp
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> FindByCurp(String person) {
        Optional<Person> foundPerson = personRepository.findByCurp(person);
        System.out.println(foundPerson);
        if (foundPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,
                    true,
                    "CurpNoFound")
                    , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new ApiResponse(personRepository.findByCurp(person),
                        HttpStatus.OK),
                HttpStatus.OK);
    }

    public String SaberAbreviacion(String estado) {
        for (int i = 0; i < estados.length; i++) {
            if (estado.equals(estados[i])) {
                return abreviaciones[i];
            }
        }
        return "No se encontro el estado";
    }

    private static char vocales(String palabra) {
        String vocales = "aeiouAEIOU";
        for (char letra : palabra.toCharArray()) {
            if (vocales.indexOf(letra) != -1) {
                return letra;
            }
        }
        return 'X';
    }


    private static char consonantes(String palabra) {
        String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        for (char letra : palabra.toCharArray()) {
            if (consonantes.indexOf(letra) != -1) {
                return letra;
            }
        }
        return 'X';
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            personRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "borrado"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "id no registrado"), HttpStatus.NOT_FOUND);
        }
    }
}


