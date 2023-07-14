package view

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import model.Model

class LossScreen(private val model: Model) :
    Scene(VBox(), model.getWindowWidth(), model.getWindowHeight()) {
    private val imageCounter = Label()
    private val currentImage = Label()

    init {
        // Initialize
    }

}