/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Concrete builder class for a Moon.
 */
public class MoonBuilder implements Builder {

  private static final int MIN_RADIUS = 3;
  private static final int MAX_SPEED = 50;
  private static final int SIZE_DIFF = 5;
  private static final int RAD_OFFSET = 5;

  private Moon moon;
  private Circle nucleus;

  /**
   * Constructor - reset the class.
   *
   * @param nucleus the thing the Moon orbits
   */
  public MoonBuilder(Circle nucleus) {
    this.reset(nucleus);
  }

  /**
   * Reset the class with a new moon to build.
   *
   * @param nucleus the thing it orbits, by drawable because it has everything this needs
   */
  @Override
  public void reset(Circle nucleus) {
    this.nucleus = nucleus;
    moon = new Moon();
    moon.setOrbitCenter(new Point2D.Double(nucleus.getCenterX(), nucleus.getCenterY()));
  }

  /**
   * Return a fully-featured Moon object.
   *
   * @return  the moon that was built
   */
  public Moon build() {
    return moon;
  }

  /**
   * Set radius.
   * Limit moon size to be smaller than its planet.
   *
   * @param radius  how big it is
   */
  @Override
  public void setRadius(double radius) {
    if (radius < MIN_RADIUS) {
      moon.setRadius(MIN_RADIUS);
    } else {
      moon.setRadius(Double.min(radius, nucleus.getRadius() - SIZE_DIFF));
    }
  }

  /**
   * Set a color for the planet.
   *
   * @param color a color
   */
  @Override
  public void setColor(Color color) {
    moon.setFill(color);
  }

  /**
   * Set the orbit radius and the initial position from orbit radius.
   *
   * @param orbitRadius  how far the moon is from its nucleus
   */
  @Override
  public void setOrbitRadius(double orbitRadius) {
    double maxOrbitRadius = maxOrbitRadius();

    if (orbitRadius > maxOrbitRadius) {
      moon.setOrbitRadius(maxOrbitRadius);
    } else {
      moon.setOrbitRadius(Double.max(minOrbitRadius(), orbitRadius));
    }
    moon.setCenterX(moon.getOrbitCenter().getX());
    moon.setCenterY(moon.getOrbitCenter().getY() + orbitRadius);
    /*
    moon.setPosition(new Point2D.Double(moon.getOrbitCenter().getX(),
        moon.getOrbitCenter().getY() + orbitRadius));
     */
  }

  /**
   * Calculate the maximum possible orbital radius for a moon.
   *
   * @return  the max radius of a moon
   */
  public double maxOrbitRadius() {
    Point2D sunLocation = new Point2D.Double(Controller.instance().getHeight() / 2.0,
        Controller.instance().getWidth() / 2.0);
    double nucleusRad = sunLocation.distance(
        new Point2D.Double(nucleus.getCenterX(), nucleus.getCenterY()));
    double maxRadHeight = (Controller.instance().getHeight() - (2 * nucleusRad)) / 2;
    double maxRadWidth = (Controller.instance().getWidth() - (2 * nucleusRad)) / 2;

    return Double.min(maxRadHeight, maxRadWidth) - RAD_OFFSET - (moon.getRadius());
  }

  /**
   * Calculate the minimum possible orbital radius for a moon.
   *
   * @return the min radius of a moon
   */
  public double minOrbitRadius() {
    return (nucleus.getRadius()) + (moon.getRadius()) + RAD_OFFSET;
  }

  /**
   * Set the speed, inverting from user-view to moon-speed.
   *
   * @param speed how fast the moon moves
   */
  @Override
  public void setSpeed(int speed) {
    if (speed >= MAX_SPEED) {
      moon.setSpeed(1);
    } else {
      moon.setSpeed(MAX_SPEED - Math.max(speed, -MAX_SPEED));
    }
  }

  @Override
  public void setName(String name) {
    if (name.contains("\n")) {
      name = name.substring(0, name.indexOf("\n"));
    }
    moon.setId(name);
  }
}
