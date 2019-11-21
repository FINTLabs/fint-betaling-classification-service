package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class CreatedDateIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        LocalDate date = claim.getCreatedDate();
        if (date != null) {
            log.info("Add CreatedDate: " + date.toString());
            addClass(claim, date.toString());
        }
    }
}