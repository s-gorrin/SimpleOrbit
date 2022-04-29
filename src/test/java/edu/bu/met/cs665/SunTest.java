/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Test the Sun class with JUnit4.
 */
public class SunTest {
  private static final double DELTA = 0.01;

  @Before
  @Test
  public void instance() {
    Sun sun = Sun.instance();
    sun.setPosition(new Point2D.Double(50, 50));
    assertEquals(30, sun.getRadius(), DELTA);
    assertEquals("Sol", sun.getId());
  }

  @Test
  public void notifyOrbiters() {
    Planet planet = new Planet(); // completely generic, not testing builder here
    planet.setOrbitRadius(10);
    planet.setSpeed(1);
    planet.setPosition(new Point2D.Double(0, 10));
    planet.setOrbitCenter(Sun.instance().getPosition());

    double before = planet.getTravel();
    Sun.instance().addOrbiter(planet);
    Sun.instance().notifyOrbiters(1);

    assertNotEquals(before, planet.getTravel());
  }
}