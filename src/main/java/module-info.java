module com.example.groupupcab302 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.groupupcab302 to javafx.fxml;
    exports com.example.groupupcab302;
}