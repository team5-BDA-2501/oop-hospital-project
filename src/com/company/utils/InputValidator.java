package com.company.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputValidator {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean validUsername(String u) {
        return !isBlank(u) && u.length() >= 3;
    }

    public static boolean validPassword(String p) {
        return !isBlank(p) && p.length() >= 3;
    }

    public static boolean validDateTime(String dt) {
        try {
            LocalDateTime.parse(dt, FMT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String dateTimeFormatHint() {
        return "Format must be: yyyy-MM-dd HH:mm (example: 2026-02-05 13:00)";
    }
}