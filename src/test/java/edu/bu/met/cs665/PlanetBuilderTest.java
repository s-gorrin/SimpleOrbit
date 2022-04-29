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
import javafx.scene.paint.Color;

import static org.junit.Assert.*;

/**
 * Test the PlanetBuilder class with JUnit4.
 */
public class PlanetBuilderTest {
  private static final double DELTA = 0.01;

  @Test
  public void build() {
    Controller controller = Controller.instance(); // fastest way to initialize sun
    PlanetBuilder builder = new PlanetBuilder(Sun.instance());
    builder.setName("Planet B\nthere is no planet b");
    builder.setColor(Color.rgb(190, 20, 170));
    builder.setRadius(25); // tested below
    builder.setOrbitRadius(150); // tested below
    builder.setSpeed(2); // should get 58 with conversion

    Planet planet = builder.build();
    assertEquals("Planet B", planet.getId());
    assertEquals(25, planet.getRadius(), DELTA);
    assertEquals(58, planet.getSpeed());
    assertEquals(150, planet.getOrbitRadius(), DELTA);
    assertEquals(new Point2D.Double(450, 600), planet.getPosition());
  }

  @Test
  public void setRadius() {
    Controller controller = Controller.instance(); // fastest way to initialize sun
    PlanetBuilder builder = new PlanetBuilder(Sun.instance());
    builder.setRadius(25);
    builder.setOrbitRadius(150);

    Planet planet = builder.build();
    assertEquals(25, planet.getRadius(), DELTA);
    assertEquals(150, planet.getOrbitRadius(), DELTA);
    assertEquals(new Point2D.Double(450, 450), planet.getOrbitCenter());

    builder.reset(Sun.instance());
    builder.setRadius(10000);
    builder.setOrbitRadius(10);

    planet = builder.build();
    assertEquals(25, planet.getRadius(), DELTA); // limited by sun size
    assertEquals(65, planet.getOrbitRadius(), DELTA); // min radius

    builder.reset(Sun.instance());
    builder.setRadius(-10);
    builder.setOrbitRadius(100000);

    planet = builder.build();
    assertEquals(5, planet.getRadius(), DELTA); // min size
    assertEquals(445, planet.getOrbitRadius(), DELTA); // max radius
  }
}