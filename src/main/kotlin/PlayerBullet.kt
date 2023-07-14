import javafx.geometry.Rectangle2D
import javafx.scene.image.Image

class PlayerBullet(var x: Double, var y: Double) {
    val image = Image("/images/player_bullet.png", 9.0, 0.0, true, true)

    fun getBoundary(): Rectangle2D {
        return Rectangle2D(x, y, image.width, image.height)
    }

    fun intersects(other: Enemy): Boolean {
        return this.getBoundary().intersects(other.getBoundary())
    }
}