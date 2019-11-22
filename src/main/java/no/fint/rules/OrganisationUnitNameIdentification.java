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
public class OrganisationUnitNameIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        //Todo Needs to be updated to new model, current model doesnt say what organisation is the invoice sender

        Organisation organisationUnit = claim.getOrganisationUnit();

        if (organisationUnit.getName() != null && organisationUnit.getName().length() >0){
            addClass(claim, organisationUnit.getName());
        }
    }
}