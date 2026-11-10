package console;

import game.BotManager;
import storage.WordStorage;

public class ConsoleApp {
    public static void main(String[] args) {
        WordStorage storage = new WordStorage();
        BotManager botManager = new BotManager(storage);
        String consoleUserId = "local_console_user";

        System.out.println(botManager.getGeneralWelcomeMessage());
        System.out.println("--------------------------------------------------");

        if (System.console() == null) {
            System.err.println("Запустите программу в обычном терминале VS Code, чтобы вводить слова.");
            return;
        }
        String input;
        while ((input = System.console().readLine()) != null) {
                
            if (input.equalsIgnoreCase("\\exit") || input.equalsIgnoreCase("exit")) {
                System.out.println("До свидания!");
                break;
            }

            String response = botManager.handleMessage(consoleUserId, input);
            System.out.println(response);
        }
    }
}
