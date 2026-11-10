package storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordStorageTest {

    @Test
    void testGetRandomWordReturnsNonEmpty() {
        WordStorage storage = new WordStorage();
        String word = storage.getRandomWord();
        assertNotNull(word);
        assertEquals(5, word.length());
    }

    @Test
    void testIsValidWord() {
        WordStorage storage = new WordStorage();
        assertTrue(storage.isValidWord("книга"));
        assertFalse(storage.isValidWord("неизвестноеслово"));
        assertFalse(storage.isValidWord(null));
    }
}