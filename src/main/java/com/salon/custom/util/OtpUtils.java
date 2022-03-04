package com.salon.custom.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

public final class OtpUtils {
    private static final int DEF_COUNT = 6;

    private static final SecureRandom SECURE_RANDOM;

    static {
        SECURE_RANDOM = new SecureRandom();
        SECURE_RANDOM.nextBytes(new byte[64]);
    }

    public OtpUtils() {
    }

    private static String generateRandomNumericString() {
        return RandomStringUtils.random(DEF_COUNT, 0, 0, false, true, null, SECURE_RANDOM);
    }

    public static String generateOtpCode() {
        return generateRandomNumericString();
    }

    public static Date generateExpiredTimeOtpCode(Integer timeExpired) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        return new Date(t + (timeExpired * 60000));
    }
}
