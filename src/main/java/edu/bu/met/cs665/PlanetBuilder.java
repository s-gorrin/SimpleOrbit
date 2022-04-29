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
 * Concrete builder class for a Planet.
 */
public class PlanetBuilder implements Builder {
  private static final int MIN_RADIUS = 5;
  private static final int MAX_SPEED = 60;
  private static final int RAD_OFFSET = 10;
  private static final int SIZE_DIFF = 5;

  private Planet planet;
  private Circle nucleus;

  /**
   * Constructor - reset the class.
   *
   * @param nucleus the thing it orbits
   */
  public PlanetBuilder(Circle nucleus) {
    this.reset(nucleus);
  }

  /**
   * Reset the builder.
   *
   * @param nucleus the thing it orbits
   */
  @Override
  public void reset(Circle nucleus) {
    this.nucleus = nucleus;
    planet = new Planet();
    planet.setOrbitCenter(new Point2D.Double(nucleus.getCenterX(), nucleus.getCenterY()));
  }

  /**
   * Get the finished Planet object.
   *
   * @return  a finished Planet
   */
  public Planet build() {
    return planet;
  }

  /**
   * Set appearance properties.
   *
   * @param radius  the size, constrained here
   */
  @Override
  public void setRadius(double radius) {
    if (radius <= MIN_RADIUS) {
      planet.setRadius(MIN_RADIUS);
    } else {
      planet.setRadius(Double.min(radius, nucleus.getRadius() - SIZE_DIFF));
    }
  }

  /**
   * Set a color for the planet.
   *
   * @param color a color
   */
  @Override
  public void setColor(Color color) {
    planet.setFill(color);
  }

  /**
   * Set orbital radius and initial position from orbital radius.
   *
   * @param orbitRadius  how far the planet is from its nucleus
   */
  @Override
  public void setOrbitRadius(double orbitRadius) {
    if (orbitRadius < minOrbitRadius()) {
      planet.setOrbitRadius(minOrbitRadius());
    } else {
      // prevent planets from going out of bounds by limiting where it can start
      planet.setOrbitRadius(Double.min(orbitRadius, maxOrbitRadius()));
    }

    planet.setPosition(new Point2D.Double(planet.getOrbitCenter().getX(),
        planet.getOrbitCenter().getY() + orbitRadius));
  }

  /**
   * Get the maximum orbital radius of a planet.
   *
   * @return  the max radius of a planet's orbit
   */
  public double maxOrbitRadius() {
    return Double.min(
        Controller.instance().getWidth() - nucleus.getCenterX() - planet.getRadius(),
        Controller.instance().getHeight() - nucleus.getCenterY() - planet.getRadius());
  }

  /**
   * Get the minimum orbital radius of a planet.
   *
   * @return  the min radius of a planet's orbit
   */
  public double minOrbitRadius() {
    return (planet.getRadius()) + (nucleus.getRadius()) + RAD_OFFSET;
  }

  /**
   * Set the speed, inverting from user-view speed to class-view speed.
   *
   * @param speed how fast the planet moves
   */
  @Override
  public void setSpeed(int speed) {
    if (speed >= MAX_SPEED) {
      planet.setSpeed(1);
    } else {
      planet.setSpeed(MAX_SPEED - Math.max(speed, 1));
    }
  }

  /**
   * Set a name for the planet that does not contain newline characters.
   *
   * @param name  a name
   */
  @Override
  public void setName(String name) {
    if (name.contains("\n")) {
      name = name.substring(0, name.indexOf("\n"));
    }
    planet.setId(name);
  }
}
