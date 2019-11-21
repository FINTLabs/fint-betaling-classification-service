package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

import static no.fint.utils.AddClass.addClass;

@Service
@Slf4j
public class ProductPriceIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        List<OrderLine> orderLines = claim.getOrderLines();

        orderLines.stream()
                .filter(orderLine -> orderLine.getItemPrice() != null && orderLine.getItemPrice().toString().length() > 0)
                .forEach(orderLine -> {
                    addClass(claim, "single_product_price: " + orderLine.getItemPrice().toString());
                });
    }
}