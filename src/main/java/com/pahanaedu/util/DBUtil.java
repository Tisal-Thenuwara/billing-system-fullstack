package com.pahanaedu.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DBUtil implements ServletContextListener {

    private static String url;
    private static String user;
    private static String pass;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        url = System.getenv().getOrDefault("PAHANA_DB_URL",
                ctx.getInitParameter("DB_URL"));
        user = System.getenv().getOrDefault("PAHANA_DB_USER",
                ctx.getInitParameter("DB_USER"));
        pass = System.getenv().getOrDefault("PAHANA_DB_PASS",
                ctx.getInitParameter("DB_PASS"));

        try {
            // Explicitly registering the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}