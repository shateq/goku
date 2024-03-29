package shateq.goku.cli;

import shateq.goku.GokuMainKt;
import shateq.goku.Tournament;
import shateq.goku.api.Performer;

public class GokuCli {

    public void loop() {
        System.out.println("Swiss Tournament Command Line Manager");
        options();

        Tournament tournament = new Tournament("Testowy");
        tournament.addPlayer(new Performer("Andrzej"));
        tournament.addPlayer(new Performer("Tomek"));
        tournament.addPlayer(new Performer("Albert"));
        tournament.addPlayer(new Performer("Anna"));

        // While
        while (true) {
            String line = GokuMainKt.SCANNER.nextLine();
            if (line.equalsIgnoreCase("exit")) { System.exit(0); }

            if (line.equalsIgnoreCase("table")) {
                tournament.getTable();
                continue;
            }

            if (line.equalsIgnoreCase("start")) {
                System.out.println("Opening...");
                tournament.start();
                continue;
            }

            // Condition
            if(tournament.running()) {
                if (line.equalsIgnoreCase("next")) {
                    System.out.println("Matching...");
                    tournament.newRound();
                    continue;
                }

                if (line.equalsIgnoreCase("fill")) {
                    tournament.lastRound().fill();
                    continue;
                }

                if (line.equalsIgnoreCase("end")) {
                    tournament.finish();
                    continue;
                }

                options();
                continue;
            }

            if(!line.isEmpty()) {
                tournament.addPlayer(new Performer(line));
                System.out.printf("New player '%s' added.\n", line);
            }
        }

    }

    public static void options() {
        System.out.printf("Options: %s, %s, %s, %s, %s, %s\n",
                "exit", "table", "start", "next", "fill", "end"
        );
    }
}
