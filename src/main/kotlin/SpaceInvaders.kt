import javafx.application.Application
import javafx.application.Platform
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.media.AudioClip
import javafx.stage.Stage
import model.Model

class SpaceInvaders : Application() {
    override fun start(stage: Stage) {
        val myModel = Model
        myModel.setStage(stage)

        val sound = javaClass.classLoader.getResource("sounds/explosion.wav").toString()
        val clip = AudioClip(sound)
//        clip.play()

        stage.apply {
            title = "Space Invaders"
            scene = myModel.getCurrentScene()
            isResizable = false
            show()
            addEventHandler(KeyEvent.KEY_PRESSED) { event ->
                if (event.code == KeyCode.Q) {
                    Platform.exit()
                }
            }
        }
    }
}

fun main() {
    Application.launch(SpaceInvaders::class.java)
}