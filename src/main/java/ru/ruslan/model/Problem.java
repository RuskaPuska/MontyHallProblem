package ru.ruslan.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Problem {
    @Min(value = 3, message = "Число дверей должно быть от 3 и выше")
    @Max(value = 1000, message = "Больше 1000 дверей не дадут наглядности по числам (всегда будет 0% b 100%)")
    private int doorsAmount;
    @Min(value = 40, message = "От 40 игр, иначе результат будет неточным")
    private int gamesAmount;
    private boolean isBogdan;

    public Problem() {
    }

    public Problem(int doorsAmount) {
        this.doorsAmount = doorsAmount;
    }

    public int getDoorsAmount() {
        return doorsAmount;
    }

    public void setDoorsAmount(int doorsAmount) {
        this.doorsAmount = doorsAmount;
    }

    public int getGamesAmount() {
        return gamesAmount;
    }

    public void setGamesAmount(int gamesAmount) {
        this.gamesAmount = gamesAmount;
    }

    public boolean isBogdan() {
        return isBogdan;
    }

    public double chanceWithoutChanging() { // Ситуации, в которых мы не идем на поводу у ВЕДУЩЕГО и не меняем выбор после открытия дверей
        int winCount = 0; // Заводим счетчик побед

        for (int i = 0; i < gamesAmount; i++) { // Запускаем цикл заданное пользователем количество раз (для точности)
            int pickedDoor = new Random().nextInt(doorsAmount) + 1; // Рандомно выбираем дверь от 1 до n, где n - общее количество дверей
            int victoryDoor = new Random().nextInt(doorsAmount) + 1; // Также рандомно ставим приз за дверь от 1 до n, где n - общее количество дверей

            if (pickedDoor == victoryDoor) { // Если мы с первой попытки угадали дверь
                ++winCount; // То мы выиграли, + к количеству побед
            }
        }
        return (double) winCount / gamesAmount * 100; // Считаем общий шанс победить при таком раскладе, деля количество побед на количество игр
    }

    public double chanceWithChanging() { // Ситуации, в которых мы идем на поводу у ВЕДУЩЕГО и меняем выбор после открытия дверей
        int winCount = 0; // Заводим счетчик побед

        for (int i = 0; i < gamesAmount; i++) { // Запускаем цикл заданное пользователем количество раз (для точности)
            int pickedDoor = new Random().nextInt(doorsAmount) + 1; // Рандомно выбираем дверь от 1 до n, где n - общее количество дверей
            int victoryDoor = new Random().nextInt(doorsAmount) + 1; // Также рандомно ставим приз за дверь от 1 до n, где n - общее количество дверей

            if (pickedDoor != victoryDoor) { // Если мы с первой попытки НЕ угадали дверь, а потом поменяли выбор на оставшуюся дверь, то мы попадаем на победную дверь
                ++winCount; // И соответственно мы выиграли, + к количеству побед
            }
        }
        return (double) winCount / gamesAmount * 100; // Считаем общий шанс победить при таком раскладе, деля количество побед на количество игр
    }
}
