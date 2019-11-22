package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrderItemDescriptionIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        List<OrderItem> orderItems = claim.getOrderItems();

        orderItems.stream()
                .filter(orderItem -> orderItem.getLineitem().getDescription() != null && orderItem.getLineitem().getDescription().length() > 0)
                .forEach( orderItem -> {
            addClass(claim, orderItem.getLineitem().getDescription().toLowerCase());
        });
    }
}