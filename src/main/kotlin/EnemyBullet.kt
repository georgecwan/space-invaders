import javafx.geometry.Rectangle2D
import javafx.scene.image.Image

class EnemyBullet(var x: Double, var y: Double, type: Int) {
    lateinit var image: Image

    init {
        when (type) {
            1 -> image = Image("/images/bullet1.png", 18.0, 0.0, true, true)
            2 -> image = Image("/images/bullet2.png", 18.0, 0.0, true, true)
            3 -> image = Image("/images/bullet3.png", 18.0, 0.0, true, true)
        }
    }

    fun getBoundary(): Rectangle2D {
        return Rectangle2D(x, y, image.width, image.height)
    }

    fun intersects(other: Player): Boolean {
        return this.getBoundary().intersects(other.getBoundary())
    }
}