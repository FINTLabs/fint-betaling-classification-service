package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class PostalCityIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        String postalCity = claim.getCustomer().getCity();

        if (postalCity != null && postalCity.length() > 0) {
            addClass(claim, postalCity);
        }
    }
}