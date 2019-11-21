package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class CreditNoteAmountIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getCreditedAmount().forEach(creditNote -> {
                    String amount = creditNote.getAmount().toString();
                    if (amount.length() > 0) {
                        log.info("Add CreditNoteAmount: " + "creditedAmount: " + amount);
                        addClass(claim, "creditedAmount: " + amount);
                    }
                }
        );
    }
}
