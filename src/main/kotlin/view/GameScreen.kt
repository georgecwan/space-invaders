package view

import EnemyBullet
import PlayerBullet
import javafx.animation.AnimationTimer
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import model.Model
import kotlin.random.Random

class GameScreen(private val model: Model) :
    Scene(Pane(), model.getWindowWidth(), model.getWindowHeight()) {
    private val pane = Pane().apply {
        background = Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))
    }
    private val canvas = Canvas(model.getWindowWidth(), model.getWindowHeight())
    val animation = object : AnimationTimer() {
        override fun handle(now: Long) {
            canvas.graphicsContext2D.apply {
                clearRect(0.0, 0.0, model.getWindowWidth(), model.getWindowHeight())
                fill = Color.WHITE
                font = Font.font("Helvetica", 28.0)
                fillText("Score: " + model.score, 10.0, 30.0)
                fillText("Lives: " + model.lives, model.getWindowWidth() - 250.0, 30.0)
                fillText("Level: " + model.level, model.getWindowWidth() - 120.0, 30.0)
                // Player movement
                model.player.x = minOf(
                    maxOf(model.player.x + (model.player.moveRight - model.player.moveLeft) * model.playerSpeed, 0.0),
                    model.getWindowWidth() - model.player.image.width
                )
                // Player reload
                if (model.player.reloadTimer > 0) {
                    model.player.reloadTimer--
                }
                // Enemy movement
                model.enemySpeed = model.enemyBaseSpeed[model.level]!! + (50 - model.enemies.size) * 0.07
                var enemyHitWall = false
                for (enemy in model.enemies) {
                    enemy.x += model.enemySpeed * model.enemyDirection
                    if (Random.nextInt(15000) <= model.enemyBulletOdds[model.level]!!) {
                        model.enemyBullets.add(EnemyBullet(enemy.x + 15, enemy.y + 10, enemy.type))
                    }
                    if (enemy.x <= 0 || enemy.x >= model.getWindowWidth() - enemy.image.width) {
                        enemyHitWall = true
                    }
                }
                if (enemyHitWall) {
                    model.enemyDirection *= -1
                    for (enemy in model.enemies) {
                        enemy.y += 20.0
                    }
                    val startledEnemy = model.enemies[Random.nextInt(model.enemies.size)]
                    model.enemyBullets.add(EnemyBullet(startledEnemy.x + 15, startledEnemy.y + 10, startledEnemy.type))
                }
                // Player bullet
                for (bullet in model.playerBullets) {
                    for (enemy in model.enemies) {
                        if (bullet.intersects(enemy)) {
                            model.playerBullets.remove(bullet)
                            model.enemies.remove(enemy)
                            model.score += enemy.type * 10
                        }
                    }
                    bullet.y -= model.playerBulletSpeed
                }
                // Enemy bullet
                for (bullet in model.enemyBullets) {
                    if (bullet.intersects(model.player)) {
                        model.enemyBullets.remove(bullet)
                        if (model.lives == 1) {
                            model.setCurrentScene(4)
                        }
                        model.lives--
                    }
                    bullet.y += model.enemyBulletSpeed[model.level]!!
                }
                // Draw objects
                drawImage(model.player.image, model.player.x, model.player.y)
                for (enemy in model.enemies) {
                    drawImage(enemy.image, enemy.x, enemy.y)
                }
                for (bullet in model.playerBullets) {
                    drawImage(bullet.image, bullet.x, bullet.y)
                }
                for (bullet in model.enemyBullets) {
                    drawImage(bullet.image, bullet.x, bullet.y)
                }
            }
        }
    }

    init {
        pane.children.add(canvas)
        root = pane
        this.setOnKeyPressed { event ->
            when (event.code) {
                KeyCode.A -> model.player.moveLeft = 1
                KeyCode.D -> model.player.moveRight = 1
                KeyCode.SPACE -> {
                    if (model.player.reloadTimer == 0) {
                        model.playerBullets.add(PlayerBullet(model.player.x + 15, model.player.y - 20))
                        model.player.reloadTimer = 30
                    }
                }

                else -> {
                }
            }
        }
        this.setOnKeyReleased { event ->
            when (event.code) {
                KeyCode.A -> model.player.moveLeft = 0
                KeyCode.D -> model.player.moveRight = 0
                else -> {
                }
            }
        }
    }

}