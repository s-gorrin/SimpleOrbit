/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-10
 */

package edu.bu.met.cs665;

/**
 * Interface for a thing which other things can orbit around.
 * It keeps a list of orbiters and notifies with its position using the observer pattern.
 */
public interface Nucleus {

  void addOrbiter(Orbiter orbiter);

  void removeOrbiter(Orbiter orbiter);

  void notifyOrbiters(long counter);
}
