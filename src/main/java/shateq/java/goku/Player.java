package shateq.java.goku;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final Player PAUSE = new Player("PAUSE");

    private final String name;
    private int points;
    private Color lastColor;
    private final List<Player> opponents = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public void setColor(Color color) {
        this.lastColor = color;
    }

    public Color lastColor() {
        return this.lastColor;
    }

    public void addPoints(int num) {
        this.points += num;
    }

    public String name() {
        return name;
    }

    public int points() {
        return points;
    }
}
