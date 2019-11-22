package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class PaymentStatusIdentification implements Minion {

    @Override
    public void classify(Claim claim) {

        String status = claim.getClaimStatus().getClaimStatus();
        addClass(claim ,ClaimStatus.STORED.toString());

        addClass(claim, status);

        if (claim.getClaimStatus() == ClaimStatus.PAID) {
            addClass(claim, ClaimStatus.SENT.toString());
        }
    }
}
