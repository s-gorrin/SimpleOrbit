/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-20
 */

package edu.bu.met.cs665;

import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Test the movement utility with JUnit4.
 * Note: these tests were written with a static Movement.Step == 0.05
 */
public class MovementTest {
  private static final double DELTA = 0.01;

  @Test
  public void move() {
    Orbiter moon = new Moon();
    moon.setOrbitCenter(new Point2D.Double(1, 2));
    moon.setPosition(new Point2D.Double(1, 12));
    moon.setOrbitRadius(10);

    for (int i = 0; i < 10; i++) {
      Movement.move(moon, new Point2D.Double(1, 2));
    }
    assertEquals(9.4147, moon.getPosition().getX(), DELTA);
    assertEquals(7.403, moon.getPosition().getY(), DELTA);
  }

  @Test
  public void stay() {
    Orbiter moon = new Moon();
    moon.setOrbitCenter(new Point2D.Double(1, 2));
    moon.setPosition(new Point2D.Double(1, 12));
    moon.setOrbitRadius(10);

    Movement.stay(moon, moon.getOrbitCenter());
    assertEquals(1, moon.getPosition().getX(), DELTA);
    assertEquals(12, moon.getPosition().getY(), DELTA);

    Movement.stay(moon, new Point2D.Double(3, 5));
    assertEquals(3, moon.getPosition().getX(), DELTA);
    assertEquals(15, moon.getPosition().getY(), DELTA);
  }
}