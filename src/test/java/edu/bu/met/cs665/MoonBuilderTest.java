/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import org.junit.Test;

import java.awt.geom.Point2D;
import javafx.scene.paint.Color;

import static org.junit.Assert.*;

/**
 * Test the MoonBuilder class with JUnit 4.
 */
public class MoonBuilderTest {

  private static final double DELTA = 0.01;

  @Test
  public void build() {
    Planet planet = new Planet();
    planet.setRadius(25);
    planet.setPosition(new Point2D.Double(500, 200));

    MoonBuilder builder = new MoonBuilder(planet);

    builder.setName("Mooney McMoon\nactually not a moon"); // should trim to before newline
    builder.setRadius(100); // should give max size
    builder.setColor(Color.rgb(40, 180, 220));
    builder.setOrbitRadius(70); // tested below
    builder.setSpeed(300); // should give max speed

    Moon moon = builder.build();
    assertEquals(70, moon.getOrbitRadius(), DELTA);
    assertEquals(new Point2D.Double(500, 270), moon.getPosition());
    assertEquals(new Point2D.Double(500, 200), moon.getOrbitCenter());
    assertEquals(1, moon.getSpeed()); // flips speed from user entry
    assertEquals(20, moon.getRadius(), DELTA); // must be smaller than planet
    assertEquals("Mooney McMoon", moon.getId());
  }

  @Test
  public void setRadius() {
    int orbitRadius = 50;
    Point2D planetLocation = new Point2D.Double(Controller.instance().getWidth() / 2.0,
        (Controller.instance().getHeight() / 2.0) - 300);

    Planet planet = new Planet();
    planet.setRadius(30);
    planet.setPosition(planetLocation);

    MoonBuilder builder = new MoonBuilder(planet);
    builder.setOrbitRadius(orbitRadius);
    Moon moon = builder.build();
    assertEquals(orbitRadius, moon.getOrbitRadius(), DELTA);
    assertEquals(planetLocation, moon.getOrbitCenter());

    builder.reset(planet);
    builder.setOrbitRadius(0);
    moon = builder.build();
    assertEquals(35, moon.getOrbitRadius(), DELTA); // min radius for a moon around a planet of size 60

    builder.reset(planet);
    builder.setRadius(20);
    builder.setOrbitRadius(10000000);
    moon = builder.build();
    assertEquals(125, moon.getOrbitRadius(), DELTA); // max radius for conditions
  }
}