package ru.job4j.tracker;

import java.util.*;

/**
 * Меню - интерфейс  взаимодействия с пользователем
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Вызываем overloaded ask(String)
     * @param question
     * @param range
     * throws MenuOutException указываем что этот метод может выкинуть этот exception
     * @return число ключа, если он входит в range menu, иначе -1
     */
    public int ask(String question, List<Integer> range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean keyExist = false;
        for (int value : range) {
            if (value == key) {
                keyExist = true;
                break;
            }
        }
        if (keyExist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}
