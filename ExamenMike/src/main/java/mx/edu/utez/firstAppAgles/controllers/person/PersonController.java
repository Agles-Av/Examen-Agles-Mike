package mx.edu.utez.firstAppAgles.controllers.person;

import jakarta.validation.Valid;
import mx.edu.utez.firstAppAgles.config.ApiResponse;
import mx.edu.utez.firstAppAgles.controllers.person.dto.PersonDto;
import mx.edu.utez.firstAppAgles.models.person.Person;
import mx.edu.utez.firstAppAgles.services.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse>  gettAll(){
        return personService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(
           @Valid @RequestBody PersonDto personDto
            ){
        return personService.save(personDto.toEntity());
    }

    @GetMapping("/{curp}")
    public ResponseEntity<ApiResponse> getByCurp(@PathVariable String curp){
        return personService.FindByCurp(curp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return personService.delete(id);
    }
}
