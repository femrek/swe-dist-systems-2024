module com.swedist.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.swedist.desktopapp to javafx.fxml;
    exports com.swedist.desktopapp;
    exports com.swedist.desktopapp.controller;
    opens com.swedist.desktopapp.controller to javafx.fxml;
}