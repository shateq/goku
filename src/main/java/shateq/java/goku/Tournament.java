package shateq.java.goku;

import org.jetbrains.annotations.NotNull;
import shateq.kotlin.goku.Player;
import shateq.kotlin.goku.registry.Performer;

import java.util.*;

public class Tournament {
    public List<Round> rounds = new LinkedList<>();
    private final List<Player> players = new LinkedList<>();
    private Scoring scoring = new Tournament.Scoring(1F, 0.5F, 0F);

    private boolean running = false;
    public final String name;
    private final Comparator<Player> sorter = (o1, o2) -> {
        if (points(o1) > points(o2)) return -1;
        if (points(o1) < points(o2)) return 1;
        return 0;
    };

    public Tournament(String name) { this.name = name; }
    public Tournament(String name, Scoring scoring) {
        this.name = name;
        this.scoring = scoring;
    }

    public void start() {
        if (running) return;
        if (players.size() < 2) {
            System.out.println("At least 2 players are required to start...");
        }

        this.running = true;
        System.out.printf("Tournament %s has been started!\n\n", name);
        newRound();
    }

    public void newRound() {
        Round round = new Round(players, this);
        rounds.add(round);
        round.print();
    }

    public void getTable() {
        players.sort(sorter);

        System.out.printf("* Tournament - %s\nNr | Name | Points, Wins\n", name);
        var i = 0;
        for (Player p : players) {
            i++;
            System.out.printf("%s | %s: %s | %s, %s \n", i, p.index(), p.name(), points(p), p.wins());
        }
    }

    public void finish() {
        if (!running) return;

        System.out.println("Closure  of the tournament.");
        getTable();
        running = false;
    }

    public float points(@NotNull Player p) {
        return (p.wins() * scoring.win) + (p.draws() * scoring.draw);
    }

    public boolean running() { return this.running; }
    // Round
    public Round lastRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }

    public Round getRound(int index) { return this.rounds.get(index); }
    // Players
    public List<Player> players() { return this.players; }

    public void addPlayer(Performer player) {
        Player p = new Player(player.name(), this.players.size() + 1);
        this.players.add(p);
    }
    // Scoring
    record Scoring(float win, float draw, float lose) {}

    public void setScoring(Scoring scoring) { this.scoring = scoring; }
    public Scoring scoring() { return this.scoring; }
}
