/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-25
 */

package edu.bu.met.cs665.graphics;

import edu.bu.met.cs665.Controller;
import edu.bu.met.cs665.DemoPlanets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * Create the primary scene for the JavaFX app.
 */
public class SceneBuilder {

  public static final String BG_COLOR = "#140028";
  public static final Color BUTTON_TEXT = Color.rgb(50, 180, 170);

  /**
   * Create an exit button.
   *
   * @return  the button
   */
  private static Button exitButton() {
    Button exitButton = new Button();
    exitButton.setText("exit");
    exitButton.setOnAction(a -> {
      Controller.instance().stop();
      System.exit(0);
    });
    exitButton.setStyle("-fx-background-color: " + BG_COLOR + "; ");
    exitButton.setTextFill(BUTTON_TEXT);
    exitButton.setTranslateX(10);
    exitButton.setTranslateY(10);

    return exitButton;
  }

  /**
   * Build the main scene for the orbit visualizer.
   *
   * @return  a javafx scene
   */
  public static Scene getScene() {
    Controller controller = Controller.instance();
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: " + BG_COLOR + "; ");

    DemoPlanets.addPlanets(controller);
    DemoPlanets.addMoons(controller);
    pane.getChildren().addAll(controller.getCircles().toList());
    pane.getChildren().add(exitButton());

    controller.start();

    return new Scene(pane, controller.getWidth(), controller.getHeight());
  }
}
