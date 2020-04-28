package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class DueDateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        LocalDate date = claim.getPaymentDueDate();

        if (date != null) {
            addClass(claim, date.toString());
        }
    }
}