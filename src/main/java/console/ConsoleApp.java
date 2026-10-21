package console;

import game.BotManager;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import storage.WordStorage;

public class ConsoleApp {
    public static void main(String[] args) {
        WordStorage storage = new WordStorage();
        BotManager botManager = new BotManager(storage);
        String consoleUserId = "local_console_user";

        System.out.println(botManager.getGeneralWelcomeMessage());
        System.out.println("--------------------------------------------------");

        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                
                if (input.equalsIgnoreCase("\\exit") || input.equalsIgnoreCase("exit")) {
                    System.out.println("До свидания!");
                    break;
                }

                String response = botManager.handleMessage(consoleUserId, input);
                System.out.println(response);
            }
        }
    }
}