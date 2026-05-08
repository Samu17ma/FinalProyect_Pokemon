module dsy.pokemonfinalproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens dsy.pokemonfinalproyect to javafx.fxml;
    exports dsy.pokemonfinalproyect;

    opens dsy.pokemonfinalproyect.shapes.types to javafx.base, javafx.fxml;
    exports dsy.pokemonfinalproyect.shapes.types;
}