package com.dmma.diploma.common;

import java.time.LocalDateTime;

public class wer {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        String name = now.format(Constants.DATE_TIME_FORMATTER);
        System.out.println(name);

    }
}
