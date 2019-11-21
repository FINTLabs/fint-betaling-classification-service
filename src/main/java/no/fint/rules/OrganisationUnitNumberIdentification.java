package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import no.fint.betaling.model.Organisation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class OrganisationUnitNumberIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        //Todo Needs to be updated to new model, current model doesnt say what organisation is the invoice sender

        List<Organisation> organisationUnits = claim.getCreatedBy().getOrganisationUnits();

        organisationUnits.stream()
                .filter(obj -> Objects.nonNull(obj) && obj.getOrganisationNumber() != null && obj.getOrganisationNumber().length() > 0)
                .forEach(unit -> {
                            log.info("Add organisation unit number: " + unit.getOrganisationNumber());
                            addClass(claim, "organisation_unit_number: " + unit.getOrganisationNumber());
                        }
                );
    }
}