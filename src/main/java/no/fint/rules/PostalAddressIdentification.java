package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class PostalAddressIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        String postalAddres = claim.getCustomer().getPostalAddress();

        if (postalAddres != null && postalAddres.length() > 0) {
            addClass(claim, postalAddres);
        }
    }
}