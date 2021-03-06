package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrganisationNumberIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        String organisationNumer = claim.getCreatedBy().getOrganisation().getOrganisationNumber();

        if (organisationNumer != null && organisationNumer.length() > 0) {
            addClass(claim, "creater_organisation_number: " + organisationNumer.toLowerCase());
        }
    }
}