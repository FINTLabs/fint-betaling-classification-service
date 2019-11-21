package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class PaymentStatusIdentification implements Minion {

    @Override
    public void classify(Claim claim) {

        String status = claim.getClaimStatus().getClaimStatus();
        log.info("Add Status: " + ClaimStatus.STORED.toString());
        addClass(claim ,ClaimStatus.STORED.toString());

        log.info("Add Status: " + status);
        addClass(claim, status);

        if (claim.getClaimStatus() == ClaimStatus.PAID) {
            log.info("Add Status: " + ClaimStatus.SENT.toString());
            addClass(claim, ClaimStatus.SENT.toString());
        }
    }
}
