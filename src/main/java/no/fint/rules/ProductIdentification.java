package no.fint.rules;

import no.fint.betaling.model.Claim;
import no.fint.betaling.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductIdentification implements Minion {
    @Override
    public void classify(Claim claim) {

        //Todo create premise for adding to Classes

        Long totalSum = 0L;
        Long totalItems = 0L;

        if (claim.getOrderLines().stream().anyMatch(orderLine -> !claim.getClasses().contains(orderLine.getItemUri().toString()))){

            claim.getClasses().add(String.valueOf(claim.getOrderLines().size()));
            for (OrderLine orderLine : claim.getOrderLines()){
                claim.getClasses().add(orderLine.getItemUri().toString());
                claim.getClasses().add(orderLine.getItemPrice().toString());
                totalSum = totalSum + orderLine.sum();
                totalItems = totalItems + orderLine.getNumberOfItems();
            }
            claim.getClasses().add(totalSum.toString());
            claim.getClasses().add(totalItems.toString());
        }
    }
}