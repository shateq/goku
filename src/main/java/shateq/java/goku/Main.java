package shateq.java.goku;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static boolean work = true;

    public static void main(String[] args) {
        System.out.printf("Swiss Tournament Terminal Manager\nOptions: %s, %s, %s, %s\n", "list", "start", "next", "results");
        Scanner s = new Scanner(System.in);
        Tournament tournament = new Tournament("Testowy");
        tournament.addPlayer(new Player("Andrzej"));
        tournament.addPlayer(new Player("Tomek"));
        tournament.addPlayer(new Player("Jan"));
        tournament.addPlayer(new Player("Julia"));
        tournament.addPlayer(new Player("Albert"));
        tournament.addPlayer(new Player("Anna"));

        while (work) {
            String r = s.nextLine();
            if (r.equalsIgnoreCase("exit")) System.exit(0);

            if (r.equalsIgnoreCase("list")) {
                tournament.getTable();
                continue;
            }

            if (r.equalsIgnoreCase("round")) {
                if (!tournament.started() || tournament.lastRound().ended()) {
                    System.out.printf("Round that you are trying to insert scoring to has just ended.\nOptions: %s, %s, %s, %s\n", "list", "start", "next", "results");
                    continue;
                }
                System.out.println("Filling out points for the current round.");
                var i = 0;
                for (Map.Entry<Player, Player> map : tournament.lastRound().map().entrySet()) {
                    i++;
                    var first = map.getKey();
                    var second = map.getValue();
                    var pseudoFirst = 0;
                    var pseudoSecond = 0;
                    // TODO: red flag
                    System.out.printf("%s | %s, %s | vs | %s, %s\nType in '+' or '-' (leave blank in case of draw): ", i, first.name(), first.points(), second.name(), second.points());
                    String answer = s.nextLine();
                    if (answer.equalsIgnoreCase("+")) {
                        pseudoFirst += tournament.scoring().win();
                    } else if (answer.equalsIgnoreCase("-")) {
                        pseudoSecond += tournament.scoring().win();
                    } else {
                        pseudoFirst += tournament.scoring().draw();
                        pseudoSecond += tournament.scoring().draw();
                    }

                    System.out.printf("%s | %s, %s | vs | %s, %s", i, first.name(), first.points() + pseudoFirst, second.name(), second.points() + pseudoSecond);
                }
                System.out.print("Is this OK? It is impossible to revert changes after confirmation.\nType in OK or press ENTER (type in 'cancel' to insert again): ");
                String c =  s.nextLine();
                if (c.equalsIgnoreCase("ok") || c.isBlank()) {
                    System.out.println("Understandable.");
                } else {
                    System.out.println("Working");
                }
                continue;
            }

            if (r.equalsIgnoreCase("next")) {
                if(!tournament.started()) continue;
                System.out.println("Parowanie...");
                tournament.nextRound();
                continue;
            }

            if (r.equalsIgnoreCase("start")) {
                System.out.println("Starting...");
                tournament.start();
                continue;
            }

            if (r.equalsIgnoreCase("results")) {
                if (!tournament.started()) continue;
                System.out.println("Ending...");
                tournament.end();
                continue;
            }

            if(!tournament.started()) {
                if(!r.isEmpty()) {
                    tournament.addPlayer(new Player(r));
                    System.out.printf("New player '%s' included.\n", r);
                }
            } else {
                System.out.printf("Tournament already started.\nOptions: %s, %s, %s, %s\n", "list", "start", "next", "results");
            }
        }
    }

//    public static void window(String name) {
//        JFrame frame = new JFrame(name);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setVisible(true);
//    }
}
