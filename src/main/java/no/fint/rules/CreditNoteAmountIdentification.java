package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class CreditNoteAmountIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getCreditNotes().forEach(creditNote -> {
                    String amount = creditNote.getAmount().toString();
                    if (amount.length() > 0) {
                        addClass(claim, "creditedAmount: " + amount);
                    }
                }
        );
    }
}
