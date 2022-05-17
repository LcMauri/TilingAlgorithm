module com.example.tilingalgorithm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tilingalgorithm to javafx.fxml;
    exports com.example.tilingalgorithm;
}