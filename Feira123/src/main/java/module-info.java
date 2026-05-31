module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    // Exporta e abre todos os pacotes para o JavaFX funcionar
    opens org.example to javafx.fxml;
    opens org.example.Controller to javafx.fxml;
    opens org.example.Model to javafx.fxml;
    opens org.example.DAO to javafx.fxml;

    exports org.example;
    exports org.example.Controller;
}