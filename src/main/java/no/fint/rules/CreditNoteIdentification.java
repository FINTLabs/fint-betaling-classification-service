package no.fint.rules;

import no.fint.betaling.model.Claim;
import no.fint.betaling.model.ClaimStatus;
import org.springframework.stereotype.Service;

@Service
public class CreditNoteIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        String creditedClassification = "credited";

        if (!claim.getCreditedAmount().isEmpty() && claim.getClasses().contains(creditedClassification)){
            claim.getClasses().add(creditedClassification);
        }

        claim.getCreditedAmount().forEach(creditNote -> {
            if (!claim.getClasses().contains(creditNote.getAmount().toString())){
                if (!claim.getClasses().contains(creditNote.getId()))
                claim.getClasses().add(creditNote.getId());
                claim.getClasses().add(creditNote.getDate().toString());
                claim.getClasses().add(creditNote.getAmount().toString());
                claim.getClasses().add(creditNote.getComment().trim().toLowerCase());
            }
        });
    }
}
