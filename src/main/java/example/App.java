package example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@SpringBootApplication
public class App {

    public static void main(final String[] args) {
        final var app = new SpringApplicationBuilder(App.class).build();
        app.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        final var json1 = """
        {
            "color" : null,
             "type" : "Solaris"
        }
        """;
        System.out.println("Color is null : " + mapper.readValue(json1, Car.class));

        final var json2 = """
        { 
            "type" : "Soraris"
        }
        """;
        System.out.println("Color undefined : " + mapper.readValue(json2, Car.class));
    }

}

record Car (
        Optional<String> color,
        String type
)
    {}
