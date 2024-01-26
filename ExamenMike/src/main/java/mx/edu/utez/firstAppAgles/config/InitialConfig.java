package mx.edu.utez.firstAppAgles.config;

import mx.edu.utez.firstAppAgles.models.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;
@Configuration
public class InitialConfig implements CommandLineRunner {

    private final PersonRepository personrepository;

    public InitialConfig(PersonRepository personrepository) {

        this.personrepository = personrepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola mundo");
        System.out.println(personrepository.findAll());
    }
}
