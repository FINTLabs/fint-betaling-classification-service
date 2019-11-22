package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrganisationNameIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        String organisationName = claim.getCreatedBy().getOrganisation().getName();

        if (organisationName != null && organisationName.length() > 0) {
            addClass(claim, organisationName.toLowerCase());
        }
    }
}