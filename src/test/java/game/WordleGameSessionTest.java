package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.WordStorage;
import static org.junit.jupiter.api.Assertions.*;

class WordleGameSessionTest {

    private WordleGameSession session;

    @BeforeEach
    void setUp() {
        WordStorage storage = new WordStorage();
        session = new WordleGameSession(storage);
    }

    @Test
    void testHelpCommandReturnsWelcomeMessage() {
        String response = session.processInput("\\help");
        assertTrue(response.contains("Привет! Я бот для игры в Wordle"));
    }

    @Test
    void testInvalidLengthInput() {
        String response = session.processInput("кот");
        assertTrue(response.contains("Слово должно состоять ровно из 5 букв"));
    }
}