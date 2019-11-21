package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.springframework.stereotype.Service;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class CreditNoteCommentIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        claim.getCreditedAmount().forEach(creditNote -> {
            String comment = creditNote.getComment();
            if (comment!= null && comment.length()>0){
                    log.info("Add CreditNoteIdComment: " + comment.trim().toLowerCase());
                    addClass(claim, comment.trim().toLowerCase());
            }
                }
        );
    }
}
