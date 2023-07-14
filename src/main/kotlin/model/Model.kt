package model

import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.scene.Scene
import javafx.stage.Stage
import view.GameScreen
import view.LossScreen
import view.TitleScreen
import view.VictoryScreen


// Template for model class from "Extended MVC with JavaFX" sample code
object Model : Observable {

    private val views = mutableListOf<InvalidationListener?>()

    /**
     * Add listener to receive notifications about changes in the [Model].
     * @param listener the listener that is added to the [Model]
     */
    override fun addListener(listener: InvalidationListener?) {
        views.add(listener)
    }

    /**
     * Remove listener to stop receiving notifications about changes in the [Model].
     * @param listener the listener that is removed from the [Model]
     */
    override fun removeListener(listener: InvalidationListener?) {
        views.remove(listener)
    }

    private lateinit var stage: Stage

    fun setStage(stage: Stage) {
        Model.stage = stage
    }

    fun getWindowWidth(): Double {
        return 1000.0
    }

    fun getWindowHeight(): Double {
        return 800.0
    }

    // 1 = TitleScreen, 2 = GameScreen, 3 = VictoryScreen, 4 = LossScreen
    private var currentScene: Int = 1;

    private val titleScreen = TitleScreen(this)
    private val gameScreen = GameScreen(this)
    private val victoryScreen = VictoryScreen(this)
    private val lossScreen = LossScreen(this)

    fun getCurrentScene(): Scene {
        return when (currentScene) {
            1 -> {
                titleScreen
            }

            2 -> {
                gameScreen
            }

            3 -> {
                victoryScreen
            }

            else -> { // 4
                lossScreen
            }
        }
    }

    private fun setCurrentScene(newScene: Int) {
        currentScene = newScene
        stage.scene = getCurrentScene()
    }

    fun startGame(level: Int) {
        setCurrentScene(2)
        this.lives = 3
        this.level = level
        gameScreen.animation.start()
    }

    private var score = 0
    private var lives = 3
    private var level = 1

    fun getScore(): Int {
        return score
    }

    fun getLives(): Int {
        return lives
    }

    fun getLevel(): Int {
        return level
    }
}