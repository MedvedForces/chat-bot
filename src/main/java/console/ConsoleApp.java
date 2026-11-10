package console;

import game.BotManager;
import storage.WordStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.Console;

public class ConsoleApp {
    public static void main(String[] args) throws IOException {
        WordStorage storage = new WordStorage();
        BotManager botManager = new BotManager(storage);
        String consoleUserId = "local_console_user";

        System.out.println(botManager.getGeneralWelcomeMessage());
        System.out.println("--------------------------------------------------");

        Console console = System.console();
        BufferedReader reader = console == null
                ? new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))
                : null;
        String input;
        while ((input = console != null ? console.readLine() : reader.readLine()) != null) {
            if (input.equalsIgnoreCase("\\exit") || input.equalsIgnoreCase("exit")) {
                System.out.println("До свидания!");
                break;
            }

            System.out.println(botManager.handleMessage(consoleUserId, input));
        }
    }
}
