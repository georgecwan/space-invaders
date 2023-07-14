import javafx.application.Application
import javafx.application.Platform
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import model.Model

class SpaceInvaders : Application() {
    override fun start(stage: Stage) {
        val myModel = Model
        myModel.setStage(stage)

        stage.apply {
            title = "Space Invaders"
            scene = myModel.getCurrentScene()
            isResizable = false
            show()
            addEventHandler(KeyEvent.KEY_PRESSED) { event ->
                when (event.code) {
                    KeyCode.DIGIT1 -> myModel.level = 1
                    KeyCode.DIGIT2 -> myModel.level = 2
                    KeyCode.DIGIT3 -> myModel.level = 3
                    KeyCode.Q -> Platform.exit()
                    else -> {
                    }
                }
            }
        }
    }
}

fun main() {
    Application.launch(SpaceInvaders::class.java)
}