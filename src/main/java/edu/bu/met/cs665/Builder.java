/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Builder interface for the builder design pattern.
 * Can build Planets and Moons.
 */
public interface Builder {

  void reset(Circle nucleus);

  void setRadius(double radius);

  void setColor(Color color);

  void setOrbitRadius(double orbitRadius); // distance from Nucleus

  void setSpeed(int speed); // frequency of movement

  void setName(String name);
}
