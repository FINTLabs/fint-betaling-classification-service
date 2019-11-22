package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class IsCreditedIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        final String creditedClassification = "credited";
        int numberOfCreditNotes = claim.getCreditedAmount().size();

        if (numberOfCreditNotes >0){
            addClass(claim, creditedClassification);
        }
    }
}
