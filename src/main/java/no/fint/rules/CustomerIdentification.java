package no.fint.rules;

import no.fint.betaling.model.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        Arrays.stream(
                StringUtils.split(
                        claim.
                                getCustomer().
                                getName().
                                replaceAll("[^\\w\\s]", "")
                )
        )
                .forEach(name -> {
                            claim.getClasses().add(StringUtils.trim(name)
                            );
                        }
                );

        Arrays.stream(
                StringUtils.split(
                        claim.
                                getCreatedBy().
                                getName().
                                replaceAll("[^\\w\\s]", "")
                )
        )
                .forEach(name -> {
                            claim.getClasses().add(StringUtils.trim(name)
                            );
                        }
                );
    }
}