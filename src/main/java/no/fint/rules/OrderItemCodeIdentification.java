package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class OrderItemCodeIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        List<OrderItem> orderItems = claim.getOrderItems();

        orderItems.stream()
                .filter(orderItem -> orderItem.getLineitem().getItemCode() != null && orderItem.getLineitem().getItemCode().length() > 0)
                .forEach( orderItem -> {
            addClass(claim, orderItem.getLineitem().getItemCode());
        });
    }
}