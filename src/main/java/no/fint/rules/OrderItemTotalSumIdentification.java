package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderItem;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrderItemTotalSumIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        Long totalSum = 0L;

        for (OrderItem orderItem : claim.getOrderItems()) {
            totalSum = totalSum + orderItem.sum();
        }
        addClass(claim, "total_sum_of_items: " + totalSum.toString());
    }

}