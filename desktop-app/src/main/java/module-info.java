module com.swedist.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.swedist.desktopapp to javafx.fxml;
    exports com.swedist.desktopapp;
}