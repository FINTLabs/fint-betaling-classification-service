package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderItem;
import org.springframework.stereotype.Service;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class TotalOrderItemsIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        Long totalItems = 0L;

        for (OrderItem orderItem : claim.getOrderItems()) {
            totalItems = totalItems + orderItem.getItemQuantity();
        }

        System.out.println("Add OrderLinesTotalItems: " + totalItems.toString());
        addClass(claim, "total_number_of_items: " + totalItems.toString());
    }
}