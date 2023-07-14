package view

import javafx.animation.AnimationTimer
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import model.Model

class GameScreen(private val model: Model) :
    Scene(Pane(), model.getWindowWidth(), model.getWindowHeight()) {
    private val pane = Pane().apply {
        background = Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
    }
    private val canvas = Canvas(model.getWindowWidth(), model.getWindowHeight())
    val animation = object : AnimationTimer() {
        override fun handle(now: Long) {
            canvas.graphicsContext2D.apply {
                fill = Color.WHITE
                font = Font.font("Helvetica", 28.0)
                fillText("Score: " + model.getScore(), 10.0, 30.0)
                fillText("Lives: " + model.getLives(), 750.0, 30.0)
                fillText("Level: " + model.getLevel(), 880.0, 30.0)
            }
        }
    }

    init {
        pane.children.add(canvas)
        root = pane
    }

}