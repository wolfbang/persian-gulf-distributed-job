package com.spacex.persian.util;

public class DistributedLockLuaScript {
    public static String getRateLimiterScript() {
        StringBuilder builder = new StringBuilder();
        builder.append("local key = KEYS[1] ");
        builder.append("local limit = tonumber(ARGV[1]) ");
        builder.append("local expiredTime = tostring(ARGV[2]) ");
        builder.append("local current = tonumber(redis.call('get', key) or \"0\") "); // lua return type number
        builder.append("if current + 1 > limit then ");
        builder.append("    return 0 ");
        builder.append("else ");
        builder.append("    redis.call(\"INCRBY\", key,\"1\") ");
        builder.append("    redis.call(\"expire\", key, expiredTime) ");
        builder.append("    return 1 ");
        builder.append("end ");
        return builder.toString();

    }

    public static String getReleaseDistributedLockScript() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        return script;
    }
}
