module com.example.operating_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.operating_system to javafx.fxml;
    exports com.example.operating_system;
}