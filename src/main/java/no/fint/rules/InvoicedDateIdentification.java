package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class InvoicedDateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        LocalDate date = claim.getInvoiceDate();
        if (date != null) {
            log.info("Add InvoicedDate: " + date.toString());
            addClass(claim, date.toString());
        }
    }
}