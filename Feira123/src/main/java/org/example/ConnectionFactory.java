package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Properties props = new Properties();

            // Tenta os dois caminhos possíveis
            InputStream input = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("org/example/db.properties");

            if (input == null) {
                input = ConnectionFactory.class
                        .getResourceAsStream("/org/example/db.properties");
            }

            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado!");
            }

            props.load(input);

            Class.forName(props.getProperty("db.driver"));

            return DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao Supabase: " + e.getMessage(), e);
        }
    }
}