package shateq.java.goku;

import shateq.kotlin.goku.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Tournament {
    public List<Round> rounds = new LinkedList<>();
    private List<Player> players = new ArrayList<>();
    private Scoring scoring = new Tournament.Scoring(1F, 0.5F, 0F);

    private boolean running = false;
    public final String name;

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament(String name, Collection<Player> players) {
        this.name = name;
        this.players = new ArrayList<>(players);
    }

    public void start() {
        if (running) {return;}
        if (players.size() < 2) {
            System.out.println("Brak szans na rozpoczecie turnieju. Wymog to min. 2 os. w grze");
        }
        this.running = true;

        Round round = new Round(players);
        rounds.add(round);
        round.print(); // PRINT OVER HERE
    }

    public void getTable() {
        // Patrz #end()
        players.sort((o1, o2) -> {
            if (o1.points() > o2.points()) return -1;
            if (o1.points() < o2.points()) return 1;
            return 0;
        });

        for (Player player : players) {
            System.out.printf("* %s, %s \n", player.name(), player.points());
        }
    }

    public void nextRound() {
        Round round = new Round(players);
        rounds.add(round);
        round.print();
    }

    public void end() {
        if (!running) return;
        // DRODZY JA WIEM, chodzi o efekt tablicy wyników - ale ja też wiem co robię -1 sprawia że obiekt przechodzi do wcześniejszych miotów kolekcji
        players.sort((o1, o2) -> {
            if (o1.points() > o2.points()) return -1;
            if (o1.points() < o2.points()) return 1;
            return 0;
        });

        System.out.printf("Tournament - %s\n", name);
        var i = 0;
        for (Player p : players) {
            i++;
            System.out.printf("%s | %s -> %s\n", i, p.name(), p.points());
        }
        running = false;
    }

    public Round lastRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean started() {
        return running;
    }

    // Scoring
    public void setScoring(Scoring scoring) {this.scoring = scoring;}
    public Scoring scoring() {return this.scoring;}

    static record Scoring(float win, float draw, float lose) {}
}
