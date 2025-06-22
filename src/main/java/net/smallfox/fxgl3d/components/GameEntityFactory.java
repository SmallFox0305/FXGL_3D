package net.smallfox.fxgl3d.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point3D;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class GameEntityFactory implements EntityFactory {

    @Spawns("3dCube")
    public Entity new3dCube(SpawnData data) {
        Box box = new Box(1, 1, 1);
        box.getTransforms().add(new Rotate(45, Rotate.X_AXIS));
        box.getTransforms().add(new Rotate(45, Rotate.Y_AXIS));
        return FXGL.entityBuilder(data)
                .view(box) // Placeholder for a 3D view
                .build();
    }

}
