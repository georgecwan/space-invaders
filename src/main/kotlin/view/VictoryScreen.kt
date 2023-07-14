package view

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import model.Model

class VictoryScreen(private val model: Model) :
    Scene(VBox(), model.getWindowWidth(), model.getWindowHeight()) {
    private val label1 = Label("Hello")

    val displayBox = VBox().apply {
        children.add(label1)
    }

    init {
        this.root = displayBox
    }
}