package com.angubaidullin._18_exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Player extends Thread {
    private String playerName;
    private List<Action> actions;
    private Exchanger<Action> exchanger;

    public Player(String playerName, List<Action> actions, Exchanger<Action> exchanger) {
        this.playerName = playerName;
        this.actions = actions;
        this.exchanger = exchanger;
        this.start();
    }

    private void whoWins(Action myAction, Action opponentAction) {
        if ((myAction == Action.STONE && opponentAction == Action.SCISSORS) ||
                (myAction == Action.SCISSORS && opponentAction == Action.PAPER) ||
                (myAction == Action.PAPER && opponentAction == Action.STONE)) {
            System.out.println("Player " + playerName + " wins!");

        } else if ((myAction == Action.STONE && opponentAction == Action.STONE) ||
                (myAction == Action.SCISSORS && opponentAction == Action.SCISSORS) ||
                (myAction == Action.PAPER && opponentAction == Action.PAPER)) {
            System.out.println("draw");
        }

    }

    @Override
    public void run() {
        Action reply;
        for (Action action : actions) {
            try {
                reply = exchanger.exchange(action);
                whoWins(action, reply);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
