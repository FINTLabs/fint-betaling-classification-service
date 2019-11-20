package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AddressIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        //Todo Create premise for adding addresses?
        claim.getClasses().add(claim.getCustomer().getPostalAddress());
        claim.getClasses().add(claim.getCustomer().getPostalCode());
        claim.getClasses().add(claim.getCustomer().getCity());
    }
}