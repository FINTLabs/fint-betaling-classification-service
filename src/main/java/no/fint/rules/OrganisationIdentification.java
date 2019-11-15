package no.fint.rules;

import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrganisationIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        //Todo Needs to be updated to new model, current model doesnt say what organisation is the invoice sender
        if (claim.getClaimStatus().equals(ClaimStatus.STORED)) {
            claim.getClasses().add(claim.getCreatedBy().getOrganisation().getName());
            claim.getClasses().add(claim.getCreatedBy().getOrganisation().getOrganisationNumber());
            claim.getCreatedBy().getOrganisationUnits().forEach(unit -> claim.getClasses().add(unit.getName()));
            claim.getCreatedBy().getOrganisationUnits().forEach(unit -> claim.getClasses().add(unit.getOrganisationNumber()));
        }
    }
}