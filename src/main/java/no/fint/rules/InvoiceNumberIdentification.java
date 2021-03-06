package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class InvoiceNumberIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getInvoiceNumbers().forEach(invoiceNumber -> {
            if (invoiceNumber != null && invoiceNumber.length() > 0)
                addClass(claim,  invoiceNumber);
        });
    }
}