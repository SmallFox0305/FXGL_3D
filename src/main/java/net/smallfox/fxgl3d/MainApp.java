package net.smallfox.fxgl3d;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.input.KeyCode;
import net.smallfox.fxgl3d.components.GameEntityFactory;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApp extends GameApplication {

    Camera3D camera3D;

    private double speed = 3;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.set3D(true);
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.UP, () -> camera3D.getTransform().setRotationX(camera3D.getTransform().getRotationX() + speed / 2));
        onKey(KeyCode.DOWN, () -> camera3D.getTransform().setRotationX(camera3D.getTransform().getRotationX() - speed / 2));
        onKey(KeyCode.RIGHT, () -> camera3D.getTransform().setRotationY(camera3D.getTransform().getRotationY() + speed / 2));
        onKey(KeyCode.LEFT, () -> camera3D.getTransform().setRotationY(camera3D.getTransform().getRotationY() - speed / 2));

        onKey(KeyCode.W, () -> moveCameraLocal(0, 0, speed));
        onKey(KeyCode.S, () -> moveCameraLocal(0, 0, -speed));
        onKey(KeyCode.A, () -> moveCameraLocal(-speed, 0, 0));
        onKey(KeyCode.D, () -> moveCameraLocal(speed, 0, 0));

        onKey(KeyCode.Q, () -> moveCameraLocal(0, speed, 0));
        onKey(KeyCode.E, () -> moveCameraLocal(0, -speed, 0));
    }

    private void moveCameraLocal(double dx, double dy, double dz) {
        double yaw = Math.toRadians(camera3D.getTransform().getRotationY());

        // 前后移动（Z轴）
        double forwardX = Math.sin(yaw);
        double forwardZ = Math.cos(yaw);

        // 左右移动（X轴）
        double rightX = Math.cos(yaw);
        double rightZ = -Math.sin(yaw);

        double moveX = dx * rightX + dz * forwardX;
        double moveZ = dx * rightZ + dz * forwardZ;

        camera3D.getTransform().setX(camera3D.getTransform().getX() + moveX);
        camera3D.getTransform().setZ(camera3D.getTransform().getZ() + moveZ);

        camera3D.getTransform().setY(camera3D.getTransform().getY() + dy);
    }

    @Override
    protected void initGame() {
        camera3D = getGameScene().getCamera3D();
        camera3D.getTransform().setZ(-230);
        camera3D.getTransform().setX(150);

        getGameWorld().addEntityFactory(new GameEntityFactory());
//        spawn("3dCube", 0, 0);
        for (int i = 0; i < 10; i++) {
            spawn("maimai",
                    new SpawnData(0, -100, i * 220)
                            .put("rotateX", 180)
            );
        }

        for (int i = 0; i < 10; i++) {
            spawn("maimai",
                    new SpawnData(300, -100, i * 220)
                            .put("rotateX", 180)
                            .put("rotateZ", 180)
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
