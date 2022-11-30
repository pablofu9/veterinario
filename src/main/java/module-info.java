module com.example.primeraentrea {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.primeraentrea to javafx.fxml;
    exports com.example.primeraentrea;
}