package shateq.java.goku;

import shateq.kotlin.goku.Player;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Round {
    private final HashMap<Player, Player> round = new HashMap<>();
    private boolean ended = false;

    public Round(Collection<Player> players) {
        match(players);
    }

    private void match(Collection<Player> players) {
        var list = new ArrayList<>(players);
        Collections.shuffle(list);
        ConcurrentLinkedQueue<Player> queue = new ConcurrentLinkedQueue<>(list);

        while(queue.peek() != null) {
            Player p1 = queue.poll();
            Player p2 = queue.poll();
            if (p2 == null) {
                round.put(p1, Player.PAUSE);
                return;
            }

            round.put(p1, p2);
        }
    }

    public void end() {
        this.ended = true;
    }

    public boolean ended() {
        return ended;
    }

    public HashMap<Player, Player> map() {
        return round;
    }

    public void print() {
        var i = 0;
        for (Map.Entry<Player, Player> key : /* Insert magic here */ map().entrySet()) {
            i++;
            var first = key.getKey();
            var second = key.getValue();
            System.out.printf("%s | %s, %s | vs | %s, %s\n", i, first.name(), first.points(), second.name(), second.points());
        }
    }
}
