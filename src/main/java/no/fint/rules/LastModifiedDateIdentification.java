package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class LastModifiedDateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        LocalDate date = claim.getLastModifiedDate();
        if (date != null) {
            addClass(claim, date.toString());
        }
    }
}