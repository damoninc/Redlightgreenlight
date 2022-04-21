module com.example.redlightgreenlight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.redlightgreenlight to javafx.fxml;
    exports com.example.redlightgreenlight;
}