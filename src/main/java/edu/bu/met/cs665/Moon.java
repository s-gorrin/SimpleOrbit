/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;
import javafx.scene.shape.Circle;

/**
 * A satellite of a Planet.
 */
public class Moon extends Circle implements Orbiter {

  private Point2D orbitCenter;
  private double orbitRadius;
  private int speed;

  private double travel = 0;

  /**
   * Get the most recent position of the Nucleus that this orbits.
   * Tell the moon to move, but let it decide if it's time yet, based on speed.
   *
   * @param center  the location of the Nucleus
   * @param counter the universal tick counter that the program keeps
   */
  @Override
  public void update(Point2D center, long counter) {
    if (counter % speed == 0) {
      Movement.move(this, center);
    } else {
      Movement.stay(this, center);
    }
  }

  /**
   * Get a container for Circle's center points.
   *
   * @return  a Point2D containing the center coordinates of the Planet
   */
  @Override
  public Point2D getPosition() {
    return new Point2D.Double(getCenterX(), getCenterY());
  }

  /**
   * Use a Point2D as a container for Circle center points.
   *
   * @param position  a grid point in pixels
   */
  @Override
  public void setPosition(Point2D position) {
    setCenterX(position.getX());
    setCenterY(position.getY());
  }

  @Override
  public double getTravel() {
    return travel;
  }

  @Override
  public void setTravel(double travel) {
    this.travel = travel;
  }

  @Override
  public Point2D getOrbitCenter() {
    return orbitCenter;
  }

  /**
   * Set the center of the Moon's orbit.
   *
   * @param orbitCenter the Point2D around which the moon orbits
   */
  @Override
  public void setOrbitCenter(Point2D orbitCenter) {
    this.orbitCenter = orbitCenter;
  }

  /**
   * Get the center of the Moon's orbit.
   *
   * @return  the Point2D around which the moon orbits
   */
  @Override
  public double getOrbitRadius() {
    return orbitRadius;
  }

  @Override
  public void setOrbitRadius(double orbitRadius) {
    this.orbitRadius = orbitRadius;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

}
