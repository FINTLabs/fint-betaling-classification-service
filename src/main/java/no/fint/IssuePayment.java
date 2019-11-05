package no.fint;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class IssuePayment implements Minion {

    @Override
    public void classify(Claim claim) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        if (!claim.getClasses().contains("issued") && random.nextFloat() < 0.05) {
            claim.getClasses().add("issued");
        }
    }
}
