package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceUriIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        log.info("Add InvoiceUri: " + claim.getInvoiceUri().toString());
        claim.getClasses().add(claim.getInvoiceUri().toString());
    }
}