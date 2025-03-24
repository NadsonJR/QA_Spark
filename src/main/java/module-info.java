module com.desafio.desafio {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.desafio.desafio to javafx.fxml;
    exports com.desafio.desafio;
}