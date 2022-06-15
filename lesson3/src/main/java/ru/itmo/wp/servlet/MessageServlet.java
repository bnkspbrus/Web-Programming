package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MessageServlet extends HttpServlet {

    private static final String UTF_8 = StandardCharsets.UTF_8.name();

    private static class Message {
        private final String user;
        private final String text;

        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }

        public String getUser() {
            return user;
        }

        public String getText() {
            return text;
        }
    }

    private final List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding(UTF_8);
//        response.setCharacterEncoding(UTF_8);
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        switch (uri) {
            case "/message/auth": {
                String user = request.getParameter("user");
                if (user == null || user.isBlank()) user = "";
                if (!user.isEmpty()) {
                    session.setAttribute("user", user);
                }
                print(user, response);
                break;
            }
            case "/message/findAll": {
                print(messages, response);
                break;
            }
            case "/message/add": {
                String text = request.getParameter("text");
                Object user = session.getAttribute("user");
                if (text != null && !text.isBlank() && user != null) {
                    messages.add(new Message(user.toString(), text));
                }
                break;
            }
        }
        response.setContentType("application/json");
    }

    private void print(Object objectToConvert, HttpServletResponse response) throws IOException {
        String json = new Gson().toJson(objectToConvert);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
        response.getWriter().print(utf8EncodedString);
        response.getWriter().flush();
    }
}
