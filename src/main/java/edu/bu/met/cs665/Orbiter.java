/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;

/**
 * A thing which orbits around a Nucleus.
 * It receives position information from the Nucleus, and can use that information
 *  when defining an orbit.
 * It can be moved, as a façade for the complex action of figuring out where to move.
 */
public interface Orbiter {

  void update(Point2D center, long counter);

  double getTravel();

  void setTravel(double travel);

  Point2D getPosition();

  void setPosition(Point2D position);

  Point2D getOrbitCenter();

  void setOrbitCenter(Point2D orbitCenter);

  double getOrbitRadius();

  void setOrbitRadius(double orbitRadius);
}
