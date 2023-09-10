package chatView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Administrator
 */
public class Client extends Thread {
    private OutputStream out;
    private InputStream in;
    private Socket socket;
    private byte[] bos = new byte[2048];
    private byte[] bis = new byte[2048];

    private String ip;

    private volatile boolean running = true; // Flag to control the while loop

    public Client(String ip) {
        this.ip = ip;
    }

    public void stopClient() {
        running = false; // Set the flag to false to stop the while loop
    }

    public void run() {
        try {
            TargetDataLine targetDataLine = AudioUtils.getTargetDataLine();
            SourceDataLine sourceDataLine = AudioUtils.getSourceDataLine();
            // 这里需要根据自己的ip修改
            socket = new Socket(this.ip, 9000);

            out = socket.getOutputStream();
            System.out.println("客户端:连接成功");
            // 保持通讯
            in = socket.getInputStream();

            System.out.println(running);
            try {
                while (running) {
                    // 获取音频流
                    int writeLen = targetDataLine.read(bos, 0, bos.length);
                    // 发送
                    if (bos != null) {
                        out.write(bos, 0, writeLen);
                    }
                    System.out.println("Success");
                    // 接收
                    int readLen = in.read(bis);
                    if (bis != null) {
                        // 播放对方发送来的音频
                        sourceDataLine.write(bis, 0, readLen);
                    }
                }
            } finally {
                // 关闭资源
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

                if (targetDataLine != null) {
                    targetDataLine.close();
                }
                if (sourceDataLine != null) {
                    sourceDataLine.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args)
    {
        Client client = new Client("localhost");
        client.start();

        // Wait for some time or perform some actions

        client.stopClient(); // Stop the client thread
    }
}