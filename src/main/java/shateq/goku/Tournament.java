package shateq.goku;

import shateq.goku.api.Player;
import shateq.goku.api.Performer;

import java.util.*;

public class Tournament {
    public List<Round> rounds = new LinkedList<>();
    private final List<Player> players = new LinkedList<>();
    private Scoring scoring = new Scoring(1F, 0.5F, 0F);

    public final String name;
    private boolean running = false;
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
        if (running) { return; }
        if (players.size() < 2) {
            System.out.println("At least 2 players are required to start...");
        }

        this.running = true;
        System.out.printf("Tournament %s has been started!\n\n", name);
        newRound();
    }

    public void newRound() {
        if(!lastRound().filled()) {
            System.out.println("May not pair a new round, the last one hasn't been filled yet!");
            return;
        }
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
            System.out.printf("%s | %s: %s | %s, %s \n", i,
                    p.index(), p.name(),
                    points(p), p.wins()
            );
        }
    }

    public void finish() {
        if (!running) { return; }

        System.out.println("Closure  of the tournament.");
        getTable();
        running = false;
    }

    public float points(Player p) {
        return (p.wins() * scoring.win) + (p.draws() * scoring.draw) + ((rounds.size() - p.wins() - p.draws()) * scoring.lose);
    }

    public boolean running() { return this.running; }
    // Round
    public Round lastRound() {
        if(rounds != null && !rounds.isEmpty()) {
            return rounds.get(rounds.size() - 1);
        }
        return rounds.get(0);
    }

    public Round getRound(int index) { return this.rounds.get(index); }
    // Players
    public List<Player> players() { return this.players; }

    public void addPlayer(Performer player) {
        Player p = new Player(player.name(), this.players.size() + 1);
        this.players.add(p);
    }

    public void addPlayer(String string) {
        Player p = new Player(string, this.players.size() + 1) ;
        this.players.add(p);
    }
    // Scoring
    public record Scoring(float win, float draw, float lose) { }
    public void setScoring(Scoring scoring) { this.scoring = scoring; }
    public Scoring scoring() { return this.scoring; }
}
