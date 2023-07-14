package model

import Enemy
import EnemyBullet
import Player
import PlayerBullet
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
                gameScreen.animation.stop()
                titleScreen
            }

            2 -> {
                gameScreen
            }

            3 -> {
                gameScreen.animation.stop()
                victoryScreen.refresh()
                victoryScreen
            }

            else -> { // 4
                gameScreen.animation.stop()
                lossScreen.refresh()
                lossScreen
            }
        }
    }

    fun setCurrentScene(newScene: Int) {
        currentScene = newScene
        stage.scene = getCurrentScene()
    }

    var score = 0
    var lives = 3
    var level = 1
    val enemies = mutableListOf<Enemy>()

    // 1 = right, -1 = left
    var enemyDirection = 1
    val enemyBullets = mutableListOf<EnemyBullet>()
    val player = Player(0.0, 0.0)
    val playerBullets = mutableListOf<PlayerBullet>()
    val playerSpeed = 2.2
    val playerBulletSpeed = 4.0
    var enemySpeed = 0.0

    // Maps level to values
    val enemyBaseSpeed = mapOf(1 to 0.5, 2 to 1.3, 3 to 2.1)
    val enemyBulletSpeed = mapOf(1 to 3.0, 2 to 3.5, 3 to 4.0)
    val enemyBulletOdds = mapOf(1 to 1, 2 to 2, 3 to 3)

    fun startGame(level: Int) {
        setCurrentScene(2)
        this.level = level
        lives = 3
        score = 0
        enemies.clear()
        enemyBullets.clear()
        playerBullets.clear()
        for (i in 0 until 10) {
            enemies.add(Enemy(200.0 + i * 60.0, 100.0, 3))
            enemies.add(Enemy(200.0 + i * 60.0, 155.0, 2))
            enemies.add(Enemy(200.0 + i * 60.0, 200.0, 2))
            enemies.add(Enemy(200.0 + i * 60.0, 245.0, 1))
            enemies.add(Enemy(200.0 + i * 60.0, 288.0, 1))
        }
        player.x = 475.0
        player.y = 760.0
        gameScreen.animation.start()
    }

    fun nextLevel() {
        gameScreen.animation.stop()
        this.level++
        enemies.clear()
        enemyBullets.clear()
        playerBullets.clear()
        for (i in 0 until 10) {
            enemies.add(Enemy(200.0 + i * 60.0, 100.0, 3))
            enemies.add(Enemy(200.0 + i * 60.0, 155.0, 2))
            enemies.add(Enemy(200.0 + i * 60.0, 200.0, 2))
            enemies.add(Enemy(200.0 + i * 60.0, 245.0, 1))
            enemies.add(Enemy(200.0 + i * 60.0, 288.0, 1))
        }
        player.x = 475.0
        player.y = 760.0
        gameScreen.animation.start()
    }
}