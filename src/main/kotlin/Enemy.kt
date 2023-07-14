import javafx.geometry.Rectangle2D
import javafx.scene.image.Image

class Enemy(var x: Double, var y: Double, val type: Int) {
    lateinit var image: Image

    init {
        when (type) {
            1 -> image = Image("/images/enemy1.png", 50.0, 0.0, true, true)
            2 -> image = Image("/images/enemy2.png", 50.0, 0.0, true, true)
            3 -> image = Image("/images/enemy3.png", 50.0, 0.0, true, true)
        }
    }

    fun getBoundary(): Rectangle2D {
        return Rectangle2D(x, y, image.width, image.height)
    }
}