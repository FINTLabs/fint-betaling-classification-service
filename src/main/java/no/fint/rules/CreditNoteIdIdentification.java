package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class CreditNoteIdIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getCreditedAmount().forEach(creditNote -> {
                    String id = creditNote.getId();
                    if (id != null && id.length() > 0) {
                        log.info("Add CreditNoteId: " + id);
                        addClass(claim, id);
                    }
                }
        );
    }
}