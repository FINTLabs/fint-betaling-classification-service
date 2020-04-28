package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class RequestedNumberOfDaysToPaymentIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        String days = claim.getRequestedNumberOfDaysToPaymentDeadline();
        if (days != null && days.length() > 0) {
            addClass(claim, "requested_number_of_days_to_payment_deadline: " + days);
        }
    }
}