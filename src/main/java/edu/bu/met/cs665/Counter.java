/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-24
 */

package edu.bu.met.cs665;

import java.time.Instant;

/**
 * A class to keep a counter independent of the rest of the program.
 */
public class Counter extends Thread {

  public static final int DELAY = 20;

  private long counter;
  private boolean kill = false;

  /**
   * Start a counter with a starting count.
   *
   * @param count counter
   */
  public Counter(long count) {
    counter = count;
    start();
  }

  /**
   * Keep count by incrementing counter on a given millisecond.
   */
  @Override
  public void run() {
    super.run();
    while (!kill) {
      // with 20, should be getting 50 ticks per second
      if (Instant.now().toEpochMilli() % DELAY == 0) {
        counter++;
        // the Controller is the entry into the program, so it should always exist here
        Controller.instance().updateSystem(counter);

        try {
          //noinspection BusyWait
          sleep(1); // prevent multiple checks per millisecond
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Get the current count.
   *
   * @return  the current count
   */
  public long getCount() {
    return counter;
  }

  /**
   * A switch to kill the counter loop.
   */
  public void kill() {
    kill = true;
  }
}
