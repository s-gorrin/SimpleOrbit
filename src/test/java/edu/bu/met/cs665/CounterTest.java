/**
 * BU MET CS 665 - Spring 2.
 * Project - simple solar system model
 *
 * @author  Seth Gorrin
 * @since   2022-04-24
 */

package edu.bu.met.cs665;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the counter interval and stopping ability.
 */
public class CounterTest {

  @Test
  public void getCount() {
    Controller.instance(); // initializes sun
    Counter counter = new Counter(0L);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long count = counter.getCount();
    assertTrue(count >= 48 && count <= 53); // this is about the max or it loses steps
    // explicitly confirm that this thread can do things while count runs in another thread
    assertEquals(36, "Infinite loop is not in this thread.".length());

    counter.kill();
  }

  @Test
  public void kill() {
    Counter counter = new Counter(0L);
    long count = counter.getCount();

    counter.kill(); // no need to test counter, since it's tested above
    counter.interrupt();

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // counter has been killed, or else these would be different
    assertEquals(count, counter.getCount());
  }
}