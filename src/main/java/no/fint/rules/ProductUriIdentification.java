package no.fint.rules;

import lombok.extern.slf4j.Slf4j;
import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

import static no.fint.utils.Utils.addClass;

@Service
@Slf4j
public class ProductUriIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        List<OrderLine> orderLines = claim.getOrderLines();

        orderLines.stream()
                .filter(orderLine -> orderLine.getItemUri() != null && orderLine.getItemUri().toString().length() > 0)
                .forEach( orderLine -> {
            addClass(claim, orderLine.getItemUri().toString());
        });
    }
}