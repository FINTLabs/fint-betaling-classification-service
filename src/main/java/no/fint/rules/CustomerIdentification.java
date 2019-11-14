package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

@Service
public class CustomerIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        String[] names = claim.getCustomer().getName().split(",", 2);
        claim.getClasses().add(names[0]);
        claim.getClasses().add(names[1]);
    }
}