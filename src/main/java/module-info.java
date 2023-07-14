module ui.george.spaceinvaders {
    requires javafx.controls;
    requires javafx.fxml;
                requires kotlin.stdlib;
    
                            
    opens ui.george.spaceinvaders to javafx.fxml;
    exports ui.george.spaceinvaders;
}