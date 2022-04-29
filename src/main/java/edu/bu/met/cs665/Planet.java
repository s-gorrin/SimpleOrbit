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
import javafx.scene.shape.Circle;

/**
 * A planet in a solar system.
 */
public class Planet extends Circle implements Nucleus, Orbiter {

  private final ArrayList<Orbiter> orbiters;

  private Point2D orbitCenter;
  private double orbitRadius;
  private int speed;

  private double travel;

  /**
   * Constructor for a Planet.
   */
  public Planet() {
    super();
    orbiters = new ArrayList<>();
    travel = 0;
  }

  /**
   * Observer pattern add an observer.
   *
   * @param orbiter an orbiter
   */
  @Override
  public void addOrbiter(Orbiter orbiter) {
    orbiters.add(orbiter);
  }

  /**
   * Observer pattern remove an observer.
   *
   * @param orbiter an orbiter
   */
  @Override
  public void removeOrbiter(Orbiter orbiter) {
    orbiters.remove(orbiter);
  }

  /**
   * Observer pattern notify observers.
   *
   * @param counter the universal tick counter
   */
  @Override
  public void notifyOrbiters(long counter) {
    for (Orbiter o : orbiters) {
      o.update(getPosition(), counter);
    }
  }

  /**
   * Update the location of the object that this planet orbits.
   *
   * @param center  the center of this object's orbit
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

  /**
   * Get the center of the planet's orbit.
   *
   * @return  the position of the planet's nucleus
   */
  @Override
  public Point2D getOrbitCenter() {
    return orbitCenter;
  }

  /**
   * Set the center of the planet's orbit.
   *
   * @param orbitCenter  the position of the planet's nucleus
   */
  @Override
  public void setOrbitCenter(Point2D orbitCenter) {
    this.orbitCenter = orbitCenter;
  }

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
