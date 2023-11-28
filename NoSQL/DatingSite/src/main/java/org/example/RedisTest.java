package org.example;

import java.util.HashMap;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    private static final int numbersRegistrants = 20;

    private static final int SLEEP_BETWEEN_REGISTRATION = 10;

    private static final int SLEEP_BETWEEN_CYCLE = 1000;

    private static final int CHANSE = 10;

    private static HashMap<String, Double> removeUsers = new HashMap<>();

    private static void log(String user) {
        String log = String.format("-На главной странице показываем пользователя: %s"
                , user);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();
        for (int regNumber = 1; regNumber <= numbersRegistrants; regNumber++) {
            redis.registrationUser(regNumber);
            Thread.sleep(SLEEP_BETWEEN_REGISTRATION);
        }

        Object[] users = redis.getUsers();
        for (int i = 0; i < users.length; i++) {
            double scorePayingUser;

            int randomCase = (int) (Math.random() * CHANSE);
            int randomUser = (int) (Math.random() * numbersRegistrants);

            if (randomCase == 1 && randomUser > i) {
                scorePayingUser = redis.removePayingUser((String) users[randomUser]);
                out.println(">Пользователь " + users[randomUser] + " оплатил платную услугу");
                log((String) users[randomUser]);
                removeUsers.put((String) users[randomUser], scorePayingUser);
            }
            users = redis.getUsers();

            log((String) users[i]);

            if (i == users.length - 1) {
                redis.addPayingUser(removeUsers);
                i = -1;
                removeUsers.clear();
                Thread.sleep(SLEEP_BETWEEN_CYCLE);
            }
        }
        redis.shutdown();

    }
}
