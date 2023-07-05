module de.iav.studentdbfrontend {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens de.iav.studentdbfrontend to javafx.fxml;
    exports de.iav.studentdbfrontend;
}