package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class CreatedByNameIdentification implements Minion {
    @Override
    public void classify(Claim claim) {
        String fullName = claim.getCreatedBy().getName();
        if (fullName!=null && fullName.length()>0){
            Arrays.stream(
                    StringUtils.split(
                            fullName.
                                    replaceAll("[^\\w\\s]", "")
                    )
            )
                    .forEach(name -> {
                                log.info("Add Name: " + StringUtils.trim(name));
                                claim.getClasses().add(StringUtils.trim(name)
                                );
                            }
                    );
        }
    }
}