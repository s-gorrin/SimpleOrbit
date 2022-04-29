/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-25
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;

/**
 * Calculate movement for Orbiters.
 */
public class Movement {

  private static final double ROTATION = Math.PI * 2;

  /**
   * Calculate the new position of an orbiting object.
   * This method is called if the object determines it is time to move.
   *
   * @param orbiter   the thing being moved
   * @param newCenter the new location of the object's nucleus
   */
  public static void move(Orbiter orbiter, Point2D newCenter) {
    stay(orbiter, newCenter); // update starting point with new center position
    double travel = orbiter.getTravel() + (1 / orbiter.getOrbitRadius()); // replaces STEP = 0.01

    // keep the numbers within a single revolution of the circle
    if (travel > ROTATION) {
      travel -= ROTATION;
    }

    double newX = orbiter.getOrbitRadius() * Math.sin(travel) + newCenter.getX();
    double newY = orbiter.getOrbitRadius() * Math.cos(travel) + newCenter.getY();

    orbiter.setTravel(travel);
    orbiter.setPosition(new Point2D.Double(newX, newY));
  }

  /**
   * Calculate the relative same position of an orbiting object.
   * This method is called if the object determines it needs to stay in the same place,
   *    relative to its nucleus.
   *
   * @param orbiter   the thing being calculated for
   * @param newCenter the new location of the object's nucleus
   */
  public static void stay(Orbiter orbiter, Point2D newCenter) {
    double translationX = newCenter.getX() - orbiter.getOrbitCenter().getX();
    double translationY = newCenter.getY() - orbiter.getOrbitCenter().getY();

    Point2D newPosition = new Point2D.Double(orbiter.getPosition().getX() + translationX,
        orbiter.getPosition().getY() + translationY);

    // update object directly with new values
    orbiter.setPosition(newPosition);
    orbiter.setOrbitCenter(newCenter);
  }

}
