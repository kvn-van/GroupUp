module com.example.groupupcab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.example.groupupcab302 to javafx.fxml;
    exports com.example.groupupcab302;
    exports com.example.groupupcab302.mockDAO;
    exports com.example.groupupcab302.Interfaces;
    opens com.example.groupupcab302.Interfaces to javafx.fxml;
    exports com.example.groupupcab302.Controllers;
    opens com.example.groupupcab302.Controllers to javafx.fxml;
    exports com.example.groupupcab302.DAO;
    opens com.example.groupupcab302.DAO to javafx.fxml;
    exports com.example.groupupcab302.Constants;
    opens com.example.groupupcab302.Constants to javafx.fxml;
    exports com.example.groupupcab302.Objects;
    opens com.example.groupupcab302.Objects to javafx.fxml;
    exports com.example.groupupcab302.misc;
    opens com.example.groupupcab302.misc to javafx.fxml;
}