/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-21
 */

package edu.bu.met.cs665;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Create some demonstration planets to fill until a GUI to add stuff is written.
 */
public class DemoPlanets {

  /**
   * Create some planets to add to the system.
   *
   * @param controller  the controller that will hold the planets
   */
  public static void addPlanets(Controller controller) {
    PlanetBuilder planetBuilder = new PlanetBuilder(Sun.instance());

    planetBuilder.setName("Earth");
    planetBuilder.setColor(Color.DEEPSKYBLUE);
    planetBuilder.setRadius(15);
    planetBuilder.setSpeed(50); // 45
    planetBuilder.setOrbitRadius(150);

    controller.addPlanet(planetBuilder.build());

    planetBuilder.reset(Sun.instance());
    planetBuilder.setName("Mars");
    planetBuilder.setColor(Color.FIREBRICK);
    planetBuilder.setRadius(10);
    planetBuilder.setSpeed(50); // 40
    planetBuilder.setOrbitRadius(300);

    controller.addPlanet(planetBuilder.build());

    planetBuilder.reset(Sun.instance());
    planetBuilder.setName("Neptune");
    planetBuilder.setColor(Color.LIGHTBLUE);
    planetBuilder.setRadius(20);
    planetBuilder.setSpeed(50);
    planetBuilder.setOrbitRadius(410);

    controller.addPlanet(planetBuilder.build());
  }

  /**
   * Create some moons for the demo planets.
   *
   * @param controller  the controller to which the moons are added
   */
  public static void addMoons(Controller controller) {
    ArrayList<Planet> planets = controller.getPlanets();

    if (planets.get(0).getId().equals("Earth")) {
      MoonBuilder moonBuilder = new MoonBuilder(planets.get(0));

      moonBuilder.setName("Luna");
      moonBuilder.setColor(Color.GHOSTWHITE);
      moonBuilder.setRadius(6);
      moonBuilder.setSpeed(50);
      moonBuilder.setOrbitRadius(70);
      controller.addMoon(moonBuilder.build(), 0);
    }

    if (planets.get(1).getId().equals("Mars")) {
      MoonBuilder moonBuilder = new MoonBuilder(planets.get(1));

      moonBuilder.setName("Phobos"); // bigger
      moonBuilder.setColor(Color.BISQUE);
      moonBuilder.setRadius(4);
      moonBuilder.setSpeed(49); // 46
      moonBuilder.setOrbitRadius(35);

      controller.addMoon(moonBuilder.build(), 1);

      moonBuilder.reset(planets.get(1));
      moonBuilder.setName("Deimos");
      moonBuilder.setColor(Color.AZURE);
      moonBuilder.setRadius(3);
      moonBuilder.setSpeed(50); // 48
      moonBuilder.setOrbitRadius(50);

      controller.addMoon(moonBuilder.build(), 1);
    }
  }
}
