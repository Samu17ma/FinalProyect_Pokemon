module dsy.pokemonfinalproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens dsy.pokemonfinalproyect to javafx.fxml;
    exports dsy.pokemonfinalproyect;
}