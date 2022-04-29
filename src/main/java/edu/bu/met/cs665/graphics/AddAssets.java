/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-25
 */

package edu.bu.met.cs665.graphics;

import edu.bu.met.cs665.Builder;
import edu.bu.met.cs665.PlanetBuilder;
import edu.bu.met.cs665.Sun;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/*
 *  TODO: create resources that add-item screens can use.
 *    Give Builder min/max methods for the ranges so that a builder
 *    can be passed in and each asset can get range values from that.
 */


/**
 * The user interface to add a planet to the system.
 */
public class AddAssets {

  /**
   * The name field of the planet.
   *
   * @return  a javaFX text field to get the name
   */
  public TextField textField() {
    TextField field = new TextField();
    field.setPromptText("Name");

    return field;
  }

  /**
   * A slider for the radius of the thing.
   *
   * @param builder the type of object this slider builds
   * @return        the slider
   */
  public Slider radius(Builder builder) {
    Slider slider = new Slider();
    slider.setOrientation(Orientation.HORIZONTAL);

    return slider;
  }
}
