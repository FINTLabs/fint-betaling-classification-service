package no.fint.rules;

import no.fint.betaling.model.Claim;

public interface Minion {

    void classify(Claim claim);
}
