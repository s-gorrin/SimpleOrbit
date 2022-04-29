/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-25
 */

package edu.bu.met.cs665.graphics;

import edu.bu.met.cs665.Controller;
import edu.bu.met.cs665.Sun;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A main for graphics.
 */
public class MainFx extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Create listeners to notify the controller if the window size changes to keep the sun centered.
   *
   * @param scene       the javafx scene
   * @param controller  the solar system controller
   */
  private void dimensionChangeListeners(Scene scene, Controller controller) {
    scene.widthProperty().addListener((observableValue, number, t1) -> {
      controller.setDimensions(number.doubleValue(), controller.getHeight());
      Sun.instance().setCenterX(number.doubleValue() / 2);
    });

    scene.heightProperty().addListener((observableValue, number, t1) -> {
      controller.setDimensions(controller.getWidth(), number.doubleValue());
      Sun.instance().setCenterY(number.doubleValue() / 2);
    });
  }

  @Override
  public void start(Stage stage) {
    Controller controller = Controller.instance();
    Scene scene = SceneBuilder.getScene();

    dimensionChangeListeners(scene, controller);
    stage.setTitle("Simple Orbits");
    stage.setScene(scene);
    stage.show();
  }

}
