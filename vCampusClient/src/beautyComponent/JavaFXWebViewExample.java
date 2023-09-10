package beautyComponent;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class JavaFXWebViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 加载 HTML 代码
        String htmlCode = "<html>\n" +
                "\n" +
                "<head></head>\n" +
                "\n" +
                "<body>\n" +
                "    <form action=\"\" class=\"search-bar\">\n" +
                "        <input type=\"search\" name=\"search\" pattern=\".*\\S.*\" required=\"\">\n" +
                "        <button class=\"search-btn\" type=\"submit\" onclick=\"change()\">\n" +
                "            <span>Search</span>\n" +
                "        </button>\n" +
                "    </form>\n" +
                "    <style>\n" +
                "        * {\n" +
                "          border: 0;\n" +
                "          box-sizing: border-box;\n" +
                "          margin: 0;\n" +
                "          padding: 0;\n" +
                "        }\n" +
                "        :root {\n" +
                "          font-size: calc(16px + (24 - 16)*(100vw - 320px)/(1920 - 320));\n" +
                "        }\n" +
                "        body, button, input {\n" +
                "          font: 1em Hind, sans-serif;\n" +
                "          line-height: 1.5em;\n" +
                "        }\n" +
                "        body, input {\n" +
                "          color: #171717;\n" +
                "        }\n" +
                "        body, .search-bar {\n" +
                "          display: flex;\n" +
                "        }\n" +
                "        body {\n" +
                "          background: #f1f1f1;\n" +
                "          height: 100vh;\n" +
                "        }\n" +
                "        .search-bar input,\n" +
                "        .search-btn, \n" +
                "        .search-btn:before, \n" +
                "        .search-btn:after {\n" +
                "          transition: all 0.25s ease-out;\n" +
                "        }\n" +
                "        .search-bar input,\n" +
                "        .search-btn {\n" +
                "          width: 3em;\n" +
                "          height: 3em;\n" +
                "        }\n" +
                "        .search-bar input:invalid:not(:focus),\n" +
                "        .search-btn {\n" +
                "          cursor: pointer;\n" +
                "        }\n" +
                "        .search-bar,\n" +
                "        .search-bar input:focus,\n" +
                "        .search-bar input:valid  {\n" +
                "          width: 100%;\n" +
                "        }\n" +
                "        .search-bar input:focus,\n" +
                "        .search-bar input:not(:focus) + .search-btn:focus {\n" +
                "          outline: transparent;\n" +
                "        }\n" +
                "        .search-bar {\n" +
                "          margin: auto;\n" +
                "          padding: 1.5em;\n" +
                "          justify-content: center;\n" +
                "          max-width: 30em;\n" +
                "        }\n" +
                "        .search-bar input {\n" +
                "          background: transparent;\n" +
                "          border-radius: 1.5em;\n" +
                "          box-shadow: 0 0 0 0.4em #171717 inset;\n" +
                "          padding: 0.75em;\n" +
                "          transform: translate(0.5em,0.5em) scale(0.5);\n" +
                "          transform-origin: 100% 0;\n" +
                "          -webkit-appearance: none;\n" +
                "          -moz-appearance: none;\n" +
                "          appearance: none;\n" +
                "        }\n" +
                "        .search-bar input::-webkit-search-decoration {\n" +
                "          -webkit-appearance: none;\n" +
                "        }\n" +
                "        .search-bar input:focus,\n" +
                "        .search-bar input:valid {\n" +
                "          background: #fff;\n" +
                "          border-radius: 0.375em 0 0 0.375em;\n" +
                "          box-shadow: 0 0 0 0.1em #d9d9d9 inset;\n" +
                "          transform: scale(1);\n" +
                "        }\n" +
                "        .search-btn {\n" +
                "          background: #171717;\n" +
                "          border-radius: 0 0.75em 0.75em 0 / 0 1.5em 1.5em 0;\n" +
                "          padding: 0.75em;\n" +
                "          position: relative;\n" +
                "          transform: translate(0.25em,0.25em) rotate(45deg) scale(0.25,0.125);\n" +
                "          transform-origin: 0 50%;\n" +
                "        }\n" +
                "        .search-btn:before, \n" +
                "        .search-btn:after {\n" +
                "          content: \"\";\n" +
                "          display: block;\n" +
                "          opacity: 0;\n" +
                "          position: absolute;\n" +
                "        }\n" +
                "        .search-btn:before {\n" +
                "          border-radius: 50%;\n" +
                "          box-shadow: 0 0 0 0.2em #f1f1f1 inset;\n" +
                "          top: 0.75em;\n" +
                "          left: 0.75em;\n" +
                "          width: 1.2em;\n" +
                "          height: 1.2em;\n" +
                "        }\n" +
                "        .search-btn:after {\n" +
                "          background: #f1f1f1;\n" +
                "          border-radius: 0 0.25em 0.25em 0;\n" +
                "          top: 51%;\n" +
                "          left: 51%;\n" +
                "          width: 0.75em;\n" +
                "          height: 0.25em;\n" +
                "          transform: translate(0.2em,0) rotate(45deg);\n" +
                "          transform-origin: 0 50%;\n" +
                "        }\n" +
                "        .search-btn span {\n" +
                "          display: inline-block;\n" +
                "          overflow: hidden;\n" +
                "          width: 1px;\n" +
                "          height: 1px;\n" +
                "        }\n" +
                "        \n" +
                "        /* Active state */\n" +
                "        .search-bar input:focus + .search-btn,\n" +
                "        .search-bar input:valid + .search-btn {\n" +
                "          background: #2762f3;\n" +
                "          border-radius: 0 0.375em 0.375em 0;\n" +
                "          transform: scale(1);\n" +
                "        }\n" +
                "        .search-bar input:focus + .search-btn:before, \n" +
                "        .search-bar input:focus + .search-btn:after,\n" +
                "        .search-bar input:valid + .search-btn:before, \n" +
                "        .search-bar input:valid + .search-btn:after {\n" +
                "          opacity: 1;\n" +
                "        }\n" +
                "        .search-bar input:focus + .search-btn:hover,\n" +
                "        .search-bar input:valid + .search-btn:hover,\n" +
                "        .search-bar input:valid:not(:focus) + .search-btn:focus {\n" +
                "          background: #0c48db;\n" +
                "        }\n" +
                "        .search-bar input:focus + .search-btn:active,\n" +
                "        .search-bar input:valid + .search-btn:active {\n" +
                "          transform: translateY(1px);\n" +
                "        }\n" +
                "        \n" +
                "        @media screen and (prefers-color-scheme: dark) {\n" +
                "          body, input {\n" +
                "            color: #f1f1f1;\n" +
                "          }\n" +
                "          body {\n" +
                "            background: #171717;\n" +
                "          }\n" +
                "          .search-bar input {\n" +
                "            box-shadow: 0 0 0 0.4em #f1f1f1 inset;\n" +
                "          }\n" +
                "          .search-bar input:focus,\n" +
                "          .search-bar input:valid {\n" +
                "            background: #3d3d3d;\n" +
                "            box-shadow: 0 0 0 0.1em #3d3d3d inset;\n" +
                "          }\n" +
                "          .search-btn {\n" +
                "            background: #f1f1f1;\n" +
                "          }\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        // 注册 JavaFX 和 JavaScript 之间的接口
        JSObject jsObject = (JSObject) webEngine.executeScript("window");
        jsObject.setMember("javaConnector", new JavaConnector());

        webEngine.loadContent(htmlCode);

        // 等待页面加载完成
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                // 页面加载完成后，执行其他操作
                System.out.println("页面加载完成");
            }
        });

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Java 和 JavaScript 之间的接口类
    public class JavaConnector {
        public void notifyValueChange(String value) {
            System.out.println("文本框内容已改变：" + value);
        }
    }
}