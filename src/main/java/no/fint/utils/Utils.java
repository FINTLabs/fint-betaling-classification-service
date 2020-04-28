package no.fint.utils;

import no.fint.betaling.model.Claim;

public class Utils {

    public static boolean addClass(Claim claim, String classToAdd){
        return claim.getClasses().add(classToAdd);
    }
}
