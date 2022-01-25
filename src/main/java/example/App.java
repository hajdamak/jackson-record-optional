package example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Optional;

public class App {

    public static void main(final String[] args) throws JsonProcessingException {
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
            "type" : "Solaris"
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
