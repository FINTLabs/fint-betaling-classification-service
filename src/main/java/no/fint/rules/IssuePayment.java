package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class IssuePayment implements Minion {

    @Override
    public void classify(Claim claim) {

        // TODO: 14/11/2019 Fix it
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        if (!claim.getClasses().contains("issued") && random.nextFloat() < 0.05) {
            claim.getClasses().add("issued");
        }
    }
}
