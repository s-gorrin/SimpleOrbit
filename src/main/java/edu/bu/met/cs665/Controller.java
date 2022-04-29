/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-25
 */

package edu.bu.met.cs665;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.scene.shape.Circle;

/**
 * Controller/mediator class for the solar system.
 * Entry point to propagating movement/updates, store grid size.
 */
public class Controller {
  private static final int DEFAULT_HEIGHT = 900;
  private static final int DEFAULT_WIDTH = 900;

  private Counter counter;
  private long saveCount;
  private double height;
  private double width;

  private static Controller controller;
  private final Sun sun;
  private final ArrayList<Planet> planets;
  private final ArrayList<Moon> moons;

  /**
   * Constructor - fill initial values.
   */
  private Controller() {
    sun = Sun.instance();
    planets = new ArrayList<>();
    moons = new ArrayList<>();

    saveCount = 0;
    height = DEFAULT_HEIGHT;
    width = DEFAULT_WIDTH;
    sun.setPosition(new Point2D.Double(width / 2.0, height / 2.0));
  }

  /**
   * Get the instance of the singleton controller.
   *
   * @return  the instance of the controller
   */
  public static Controller instance() {
    if (controller == null) {
      controller = new Controller();
    }

    return controller;
  }

  /**
   * Propagate updates across the system.
   * This should only ever be called by Counter in production.
   */
  public void updateSystem(long count) {
    sun.notifyOrbiters(count);  // sun notifies planets

    for (Planet planet : planets) {
      planet.notifyOrbiters(count); // planets notify moons
    }
  }

  /**
   * Start solar system updates.
   */
  public void start() {
    counter = new Counter(saveCount);
  }

  /**
   * Stop solar system updates.
   */
  public void stop() {
    counter.kill();
    saveCount = counter.getCount();
    counter.interrupt();
  }

  /**
   * Get the height of the grid.
   *
   * @return  the height of the grid in pixels
   */
  public double getHeight() {
    return height;
  }

  /**
   * Get the width of the grid.
   *
   * @return  the width of the grid in pixels
   */
  public double getWidth() {
    return width;
  }

  /**
   * Get updated dimensions from the window in case of resizing.
   *
   * @param height  vertical dimension
   * @param width   horizontal dimension
   */
  public void setDimensions(double height, double width) {
    this.height = height;
    this.width = width;
  }

  /**
   * Add a planet.
   *
   * @param planet  a fully-built planet
   */
  public void addPlanet(Planet planet) {
    sun.addOrbiter(planet);
    planets.add(planet);
  }

  /**
   * Add a moon to the moons list. This does not add it to a planet, which happens elsewhere.
   *
   * @param moon          a fully-built moon
   * @param indexOfPlanet the index of the planet to add the moon to, via the getPlanets method
   */
  public void addMoon(Moon moon, int indexOfPlanet) {
    moons.add(moon);
    planets.get(indexOfPlanet).addOrbiter(moon);
  }

  /**
   * Get planets to add moons to them.
   *
   * @return  a list of planets
   */
  public ArrayList<Planet> getPlanets() {
    return planets;
  }

  /**
   * Get a stream of all drawable objects in the Controller.
   *
   * @return  a stream of Circle objects
   */
  public Stream<Circle> getCircles() {
    return Stream.concat(Stream.concat(Stream.of(sun), planets.stream()), moons.stream());
  }
}
