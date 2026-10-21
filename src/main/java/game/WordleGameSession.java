package game;

import storage.WordStorage;

public class WordleGameSession {
    private final WordStorage wordStorage;
    private String secretWord;
    private int attemptsLeft;
    private boolean isGameOver;

    public WordleGameSession(WordStorage wordStorage) {
        this.wordStorage = wordStorage;
        startNewGame();
    }

    public void startNewGame() {
        this.secretWord = wordStorage.getRandomWord();
        this.attemptsLeft = 6;
        this.isGameOver = false;
    }

    public String getWelcomeMessage() {
        return "Привет! Я бот для игры в Wordle.\n" +
               "Правила: я загадал слово из 5 букв. У тебя есть 6 попыток угадать его.\n" +
               "Обозначения в ответах:\n" +
               "  [буква] — буква на своем месте\n" +
               "  (буква) — буква есть в слове, но не на своем месте\n" +
               "  _ — буквы нет в слове\n\n" +
               "Команды:\n" +
               "  \\help — показать эту справку\n" +
               "  \\new — начать новую игру\n" +
               "Введи слово из 5 букв, чтобы сделать первую попытку!";
    }

    public String processInput(String input) {
        if (input == null) {
            return "Пожалуйста, введите слово.";
        }
        String trimmed = input.trim().toLowerCase();

        if (trimmed.equals("\\help")) {
            return getWelcomeMessage();
        }

        if (trimmed.equals("\\new")) {
            startNewGame();
            return "Начата новая игра! Я загадал новое слово из 5 букв. Жду твою попытку:";
        }

        if (isGameOver) {
            return "Игра окончена! Введите \\new, чтобы начать новую игру.";
        }

        if (trimmed.length() != 5) {
            return "Слово должно состоять ровно из 5 букв! Попробуй еще раз:";
        }

        attemptsLeft--;
        if (trimmed.equals(secretWord)) {
            isGameOver = true;
            return "Поздравляю! Ты угадал слово: " + secretWord + "! Введи \\new для новой игры.";
        }

        String feedback = evaluateGuess(trimmed, secretWord);
        if (attemptsLeft <= 0) {
            isGameOver = true;
            return "Попытки закончились. Я загадал слово: " + secretWord + ". Введи \\new для новой игры.";
        }

        return "Результат: " + feedback + "\nОсталось попыток: " + attemptsLeft + ". Жду следующую попытку:";
    }

    private String evaluateGuess(String guess, String secret) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char gChar = guess.charAt(i);
            char sChar = secret.charAt(i);
            if (gChar == sChar) {
                sb.append("[").append(gChar).append("] ");
            } else if (secret.indexOf(gChar) >= 0) {
                sb.append("(").append(gChar).append(") ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }
}