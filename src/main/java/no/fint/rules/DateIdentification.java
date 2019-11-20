package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        //Todo create premise for adding to Classes

        claim.getClasses().add(claim.getRequestedNumberOfDaysToPaymentDeadline());
        claim.getClasses().add(claim.getCreatedDate().toString());
        claim.getClasses().add(claim.getLastModifiedDate().toString());

        if (claim.getInvoiceDate() != null) {
            claim.getClasses().add(claim.getInvoiceDate().toString());
        }
        if (claim.getPaymentDueDate() != null) {
            claim.getClasses().add(claim.getPaymentDueDate().toString());
        }
    }
}