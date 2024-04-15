module org.agc.proyecto_m06_m09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;

    opens org.agc.proyecto_m06_m09 to javafx.fxml;
    exports org.agc.proyecto_m06_m09;
    exports org.agc.proyecto_m06_m09.bbdd;
    opens org.agc.proyecto_m06_m09.bbdd to javafx.fxml;
    exports org.agc.proyecto_m06_m09.fx;
    opens org.agc.proyecto_m06_m09.fx to javafx.fxml;
}