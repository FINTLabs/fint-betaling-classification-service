package no.fint;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class Claim {

    @Id
    private String id;
    private Long timestamp;
    private Set<String> classes;

}
