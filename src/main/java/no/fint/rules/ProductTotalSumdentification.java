package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderLine;
import org.springframework.stereotype.Service;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class ProductTotalSumdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        Long totalSum = 0L;

        for (OrderLine orderLine : claim.getOrderLines()){
            totalSum = totalSum + orderLine.sum();
        }
        addClass(claim, "total_sum_of_items: " + totalSum.toString());
    }

}