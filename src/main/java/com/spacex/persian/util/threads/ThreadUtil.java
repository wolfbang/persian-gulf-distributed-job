package com.spacex.persian.util.threads;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {
    public static void sleep(Long millisSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
