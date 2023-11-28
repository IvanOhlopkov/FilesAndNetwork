package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.HashMap;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;

    private RKeys rKeys;

    private RScoredSortedSet<String> registeredUsers;

    private final static String KEY = "REGISTERED_USERS";

    private double getTs() {
        return (double) new Date().getTime() / 1000;
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        registeredUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void registrationUser(int user_id) {
        registeredUsers.add(getTs(), String.valueOf(user_id));
    }

    Object[] getUsers() {
        return registeredUsers.toArray();
    }

    double removePayingUser(String payingUser) {
        double scorePayingUser = registeredUsers.getScore(payingUser);
        registeredUsers.remove(payingUser);
        return scorePayingUser;

    }

    void addPayingUser(HashMap<String, Double> removeUsers) {
        registeredUsers.addAll(removeUsers);
    }
}
