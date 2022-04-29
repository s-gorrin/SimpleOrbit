/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A singleton sun, the center of the solar system.
 */
public class Sun extends Circle implements Nucleus {

  private static final int SUN_RADIUS = 30;

  private static Sun sun;

  private final ArrayList<Orbiter> orbiters;

  /**
   * Private constructor for a singleton sun.
   */
  private Sun() {
    super();
    orbiters = new ArrayList<>();
    setFill(Color.rgb(250, 175, 15));
    setRadius(SUN_RADIUS);
    setId("Sol");
  }

  /**
   * Singleton instance of the Sun.
   *
   * @return  the instance
   */
  public static Sun instance() {
    if (sun == null) {
      sun = new Sun();
    }

    return sun;
  }

  @Override
  public void addOrbiter(Orbiter orbiter) {
    orbiters.add(orbiter);
  }

  @Override
  public void removeOrbiter(Orbiter orbiter) {
    orbiters.remove(orbiter);
  }

  @Override
  public void notifyOrbiters(long counter) {
    for (Orbiter o : orbiters) {
      o.update(getPosition(), counter);
    }
  }

  /**
   * Get a container for Circle's center points.
   *
   * @return  a Point2D containing the center coordinates of the Sun
   */
  public Point2D getPosition() {
    return new Point2D.Double(getCenterX(), getCenterY());
  }

  /**
   * Use a Point2D as a container for Circle center points.
   *
   * @param position  a grid point in pixels
   */
  public void setPosition(Point2D position) {
    setCenterX(position.getX());
    setCenterY(position.getY());
  }

}
