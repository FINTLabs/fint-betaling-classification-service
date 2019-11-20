package no.fint.rules;

import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentStatusIdentification implements Minion {

    @Override
    public void classify(Claim claim) {

        String status = claim.getClaimStatus().getClaimStatus();

        if (!claim.getClasses().contains(ClaimStatus.STORED.toString())) {
            claim.getClasses().add(ClaimStatus.STORED.toString());
        }

        if (!claim.getClasses().contains(status)) {
            claim.getClasses().add(status);
        }

        if (claim.getClaimStatus() == ClaimStatus.PAID) {
            claim.getClasses().add(ClaimStatus.SENT.toString());
        }
    }
}
