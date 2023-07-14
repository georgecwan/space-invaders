import javafx.geometry.Rectangle2D
import javafx.scene.image.Image

class Player(var x: Double, var y: Double) {
    val image = Image("/images/player.png", 50.0, 0.0, true, true)

    // 0 = stationary, 1 = moving
    var moveLeft = 0
    var moveRight = 0

    var reloadTimer = 0

    fun getBoundary(): Rectangle2D {
        return Rectangle2D(x, y, image.width, image.height)
    }
}