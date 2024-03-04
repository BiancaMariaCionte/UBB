module com.example.a5biancamariaciontejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.a5biancamariaciontejavafx to javafx.fxml;
    exports com.example.a5biancamariaciontejavafx;
}