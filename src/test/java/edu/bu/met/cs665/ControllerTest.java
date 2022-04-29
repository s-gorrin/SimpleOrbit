/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-20
 */

package edu.bu.met.cs665;

import org.junit.Before;
import org.junit.Test;
import javafx.scene.paint.Color;

import static org.junit.Assert.*;

/**
 * Test the Controller class with JUnit4.
 */
public class ControllerTest {

  /**
   * Test the instance and also adding a planet and adding a moon.
   * The updateSystem() test will confirm that the planet and moon were added correctly.
   */
  @Before
  @Test
  public void instance() {
    Controller controller = Controller.instance();
    PlanetBuilder planetBuilder = new PlanetBuilder(Sun.instance());
    planetBuilder.setName("Planet B\nthere is no planet b");
    planetBuilder.setColor(Color.rgb(190, 20, 170));
    planetBuilder.setRadius(75); // tested below
    planetBuilder.setOrbitRadius(150); // tested below
    planetBuilder.setSpeed(2); // should get 58 with conversion

    Planet planet = planetBuilder.build();
    MoonBuilder moonBuilder = new MoonBuilder(planet);

    moonBuilder.setName("Mooney McMoon\nactually not a moon"); // should trim to before newline
    moonBuilder.setRadius(100); // should give max size
    moonBuilder.setColor(Color.rgb(40, 180, 220));
    moonBuilder.setOrbitRadius(70); // tested below
    moonBuilder.setSpeed(300); // should give max speed

    Moon moon = moonBuilder.build();
    controller.addPlanet(planet);

    // In production, getPlanets() is used to get a list of planets for a dropdown menu
    // where a planet will be selected and its index will be sent back into the Controller.
    // This will only be used when adding moons.
    Planet viaController = controller.getPlanets().get(0);
    assertEquals(planet.getId(), viaController.getId());

    controller.addMoon(moon, 0);
  }

  @Test
  public void updateSystem() {
    assertEquals(1, Controller.instance().getPlanets().size()); // confirm the @Before has run
    for (int i = 0; i < 10; i++) {
      Controller.instance().updateSystem(0);
    }
    // proves that they've moved
    assertNotEquals(0, Controller.instance().getPlanets().get(0).getTravel());
  }
}