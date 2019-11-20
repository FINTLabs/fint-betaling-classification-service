package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

@Service
public class InvoiceIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        //Todo Create premise for adding to classes?

        claim.getClasses().add(claim.getOriginalAmountDue().toString());
        claim.getClasses().add(claim.getInvoiceNumber());
        claim.getClasses().add(claim.getInvoiceUri().toString());
    }
}