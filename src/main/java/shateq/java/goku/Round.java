package shateq.java.goku;

import shateq.kotlin.goku.Player;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Round {
    private final HashMap<Player, Player> round = new HashMap<>();
    private final Tournament tournament;
    private boolean filled = false;

    public Round(List<Player> players, Tournament tournament) {
        this.tournament = tournament;
        match(players);
    }

    // TODO some
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

    public void fill() {
        System.out.println("Filling out the results for current round...\n" +
                "Type in '+' (win) or '-' (lose), blank line is equivalent to draw.");
        var i = 0;
        for (Map.Entry<Player, Player> map : round.entrySet()) {
            i++;
            var first = map.getKey();
            var second = map.getValue();
            Verdict verdict;

            System.out.printf("%s | %s: %s VS %s: %s\n", i,
                    first.index(), first.name(),
                    second.index(), second.name()
            );
            String answer = GokuMain.SCANNER.nextLine();

            switch (answer) {
                case "+" -> verdict = Verdict.WIN;
                case "-" -> verdict = Verdict.LOSE;
                default -> verdict = Verdict.DRAW;
            }

            System.out.printf("--> %s: %s | %s | %s: %s\n",
                    first.index(), first.name(),
                    verdict.mark,
                    second.index(), second.name()
            );

            switch (verdict) { // Submitting the verdict
                case WIN -> first.incWins();
                case LOSE -> second.incWins();
                case DRAW -> {
                    first.incDraws();
                    second.incDraws();
                }
            }
        }
        this.filled = true;
        System.out.println("Results filled out, changes may be made later.");
    }

    public HashMap<Player, Player> map() { return round; }

    public void print() {
        var i = 0;
        for (Map.Entry<Player, Player> key : map().entrySet()) {
            i++;
            var first = key.getKey();
            var second = key.getValue();
            System.out.printf("%s | %s: %s, %s | vs | %s: %s, %s\n", i,
                    first.index(), first.name(), tournament.points(first),
                    second.index(), second.name(), tournament.points(second)
            );
        }
    }

    public boolean filled() { return this.filled; }
}
