module com.anumag.affine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anumag.affine to javafx.fxml;
    exports com.anumag.affine;
}