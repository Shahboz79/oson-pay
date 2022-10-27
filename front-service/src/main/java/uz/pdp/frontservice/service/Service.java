package uz.pdp.frontservice.service;

import java.util.Optional;

public class Service {
    public static int getPage(Optional<Integer> i) {
        return i.orElse(1);
    }

    public static boolean isEmpty(int i) {
        return i > 10 ? false : true;
    }
}
