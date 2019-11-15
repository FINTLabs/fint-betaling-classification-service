package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        //TODO Need to use library for more effective split.
        // apache commons lang3 StringUtils
        // string tokenizer

        StringUtils
        Arrays.stream(claim.getCustomer().getName().trim().split(","))
                .forEach(name -> Arrays.stream(name.trim().split("\\s+"))
                        .forEach(singleName -> claim.getClasses().add(singleName)));
        Arrays.stream(claim.getCreatedBy().getName().trim().split(","))
                .forEach(name -> Arrays.stream(name.trim().split("\\s+"))
                        .forEach(singleName -> claim.getClasses().add(singleName)));
    }
}