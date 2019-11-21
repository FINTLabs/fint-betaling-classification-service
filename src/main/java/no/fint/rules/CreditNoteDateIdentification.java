package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class CreditNoteDateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getCreditedAmount().forEach(creditNote -> {
                    LocalDate date = creditNote.getDate();
                    if (date != null) {
                        log.info("Add CreditNoteDate: " + date.toString());
                        addClass(claim, date.toString());
                    }
                }
        );
    }
}
