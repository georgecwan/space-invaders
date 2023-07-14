package view

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import model.Model

class TitleScreen(private val model: Model) :
    Scene(BorderPane(), model.getWindowWidth(), model.getWindowHeight()) {

    val logo = ImageView("/images/logo.png")
    val name = Label("Implemented by George Wan (20938342) for CS 349, University of Waterloo, S23")
    val instructions = VBox(
        10.0,
        Label("Instructions").apply {
            style = "-fx-font-size: 50px;"
        },
        Label("\nENTER - Start Game").apply {
            style = "-fx-font-size: 20px;"
        },
        Label("A, D - Move ship left or right").apply {
            style = "-fx-font-size: 20px;"
        },
        Label("SPACE - Fire!").apply {
            style = "-fx-font-size: 20px;"
        },
        Label("Q - Quit Game").apply {
            style = "-fx-font-size: 20px;"
        },
        Label("1 or 2 or 3 - Start game at a specific level").apply {
            style = "-fx-font-size: 20px;"
        }
    )
    val displayBox = BorderPane().apply {
        BorderPane.setAlignment(logo, Pos.CENTER)
        BorderPane.setAlignment(name, Pos.CENTER)
        BorderPane.setMargin(logo, Insets(15.0))
        BorderPane.setMargin(name, Insets(10.0))
        instructions.alignment = Pos.CENTER
        top = logo
        center = instructions
        bottom = name
    }

    init {
        this.root = displayBox
        this.setOnKeyReleased { event ->
            when (event.code) {
                KeyCode.ENTER -> model.startGame(1)
                KeyCode.DIGIT1 -> model.startGame(1)
                KeyCode.DIGIT2 -> model.startGame(2)
                KeyCode.DIGIT3 -> model.startGame(3)
                else -> {
                }
            }
        }
    }
}