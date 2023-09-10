package beautyComponent;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageExample extends JPanel {
    public WebPageExample() {

        JFXPanel jfxPanel = new JFXPanel();
        jfxPanel.setPreferredSize(new Dimension(1685, 975));
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            try {
                // Load local HTML file
                URL url = new File("D:\\Projects\\vCampus\\vCampusClient\\src\\StudentRollview\\whether.html").toURI().toURL();
                webEngine.load(url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            jfxPanel.setScene(new Scene(webView));
        });

        add(jfxPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WebPageExample example = new WebPageExample();
            example.setVisible(true);
            JFrame frame = new JFrame();
            frame.add(example);
            frame.pack();
            frame.setVisible(true);
        });
    }
}