package shateq.java.goku.registry;

import org.jetbrains.annotations.NotNull;
import shateq.java.goku.registry.User;

import java.util.*;

public class Registry {

    private final Map<String, User> players;

    public Registry() {
        this.players = new HashMap<>();
    }

    public void addPlayer(User player) {
        this.players.put(player.name().toLowerCase(), player);
    }

    public User getPlayer(@NotNull String name) {
        return this.players.get(name.toLowerCase());
    }

    public Collection<User> listPlayers() {
        return this.players.values();
    }
}
