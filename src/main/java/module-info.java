module com.example.perfect_cirlce {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.perfect_cirlce to javafx.fxml;
    exports com.example.perfect_cirlce;
}