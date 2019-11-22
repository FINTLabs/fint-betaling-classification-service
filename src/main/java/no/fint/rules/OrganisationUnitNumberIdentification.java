package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.Organisation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrganisationUnitNumberIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        Organisation organisationUnit = claim.getOrganisationUnit();

        if (organisationUnit.getOrganisationNumber() != null && organisationUnit.getOrganisationNumber().length() >0){
            addClass(claim, organisationUnit.getOrganisationNumber());
        }
    }
}