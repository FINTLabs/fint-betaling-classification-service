package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletion implements Minion {
    @Override
    public void classify(Claim claim) {

        if (claim.getClasses().contains("issued") && !claim.getClasses().contains("paid")) {
            // TODO the work!!!
            claim.getClasses().add("paid");
        }
    }
}
