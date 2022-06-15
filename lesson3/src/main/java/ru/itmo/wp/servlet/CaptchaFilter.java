package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (request.getMethod().equals("GET") && session.getAttribute("accept") == null && !request.getRequestURI().equals("/favicon.ico")) {
            String correct = (String) session.getAttribute("correct");
            String attempt = request.getParameter("attempt");
            if (correct == null || !correct.equals(attempt)) {
                String random = Integer.toString(new Random().nextInt(900) + 100);
                session.setAttribute("correct", random);
                request.setAttribute("captcha", Base64.getEncoder().encodeToString(ImageUtils.toPng(random)));
                response.setContentType("text/html");
//                System.out.printf("%s %s %s %s\n", request.getMethod(), request.getRequestURI(), request.getProtocol(), random);
                request.getRequestDispatcher("/dynamic/jsp/auth.jsp").forward(request, response);
            } else {
                session.setAttribute("accept", "accept");
                response.sendRedirect(request.getRequestURI());
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
