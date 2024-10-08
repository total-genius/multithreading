package com.angubaidullin._18_exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Action> exchanger = new Exchanger<>();
        List<Action> actions1 = new ArrayList<>();
        actions1.add(Action.SCISSORS);
        actions1.add(Action.PAPER);
        actions1.add(Action.SCISSORS);

        List<Action> actions2 = new ArrayList<>();
        actions2.add(Action.PAPER);
        actions2.add(Action.STONE);
        actions2.add(Action.STONE);

        new Player("Ivan", actions1, exchanger);
        new Player("Oleg", actions2, exchanger);
    }
}
