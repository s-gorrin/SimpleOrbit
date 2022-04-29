/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

/**
 * May Maven have mercy on my soul if this breaks.
 * I have only the most tenuous grasp on how it works.
 */
module SimpleOrbitVisualizer {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  opens edu.bu.met.cs665 to javafx.fxml;
  exports edu.bu.met.cs665;

  opens edu.bu.met.cs665.graphics to javafx.graphics;
  exports edu.bu.met.cs665.graphics;
}
