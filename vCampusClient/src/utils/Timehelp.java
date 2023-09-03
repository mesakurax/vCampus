package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timehelp{
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static void main(String[] args) {
        String currentTime = getCurrentTime();
        System.out.println("当前时间：" + currentTime);
    }
}
