package game;

import storage.WordStorage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BotManager {
    private final WordStorage wordStorage;
    private final Map<String, WordleGameSession> sessions = new ConcurrentHashMap<>();

    public BotManager(WordStorage wordStorage) {
        this.wordStorage = wordStorage;
    }

    public String handleMessage(String userId, String message) {
        WordleGameSession session = sessions.computeIfAbsent(userId, id -> new WordleGameSession(wordStorage));
        return session.processInput(message);
    }

    public String getGeneralWelcomeMessage() {
        return new WordleGameSession(wordStorage).getWelcomeMessage();
    }
}