package storage; 

import java.util.List;
import java.util.Random;

public class WordStorage {
    private final List<String> dictionary = List.of(
        "драфт", "книга", "полет", "лампа", "трава", 
        "ручка", "дверь", "огонь", "скала", "песок"
    );
    private final Random random = new Random();

    public String getRandomWord() {
        if (dictionary.isEmpty()) {
            return "тест";
        }
        return dictionary.get(random.nextInt(dictionary.size()));
    }

    public boolean isValidWord(String word) {
        if (word == null) {
            return false;
        }
        return dictionary.contains(word.trim().toLowerCase());
    }
}