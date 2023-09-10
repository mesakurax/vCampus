package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPV4Helper{
    public static String ipv4() {
        try {
            // 执行ipconfig指令
            String command = "ipconfig";
            Process process = Runtime.getRuntime().exec(command);

            // 读取命令行输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 提取IPv4地址
            String ipv4Pattern = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
            Pattern pattern = Pattern.compile(ipv4Pattern);
            Matcher matcher = pattern.matcher(output.toString());

            int matchCount = 0; // 匹配成功的计数器
            while (matcher.find()) {
                matchCount++;
                if (matchCount == 5) {
                    String ipv4Address = matcher.group();
                    return ipv4Address;
                }
            }

            if (matchCount < 3) {
                System.out.println("No third IPv4 Address found.");
            }

            // 等待命令执行完毕
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}

