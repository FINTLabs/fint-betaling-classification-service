package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class PostalCodeIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        String postalCode = claim.getCustomer().getPostalCode();

        if (postalCode != null && postalCode.length() > 0) {
            String postalCodeWithIdentifier = "postal_code: " + postalCode;
            addClass(claim, postalCodeWithIdentifier);
        }
    }

}