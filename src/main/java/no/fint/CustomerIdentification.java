package no.fint;

import org.springframework.stereotype.Service;

@Service
public class CustomerIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        claim.getClasses().add("bjarne");
        claim.getClasses().add("isaksen");
    }
}
