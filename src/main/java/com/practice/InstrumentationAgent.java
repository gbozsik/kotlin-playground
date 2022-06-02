package com.practice;

import java.lang.instrument.Instrumentation;

public class InstrumentationAgent {
  private static volatile Instrumentation globalInstrumentation;

  public static void premain(final String agentArgs, final Instrumentation inst) {
    globalInstrumentation = inst;
  }

  /**
   * Gets the daily performance history by id map.
   *
   * @param object        the site
   * @return the daily performance history by id map
   * @throws Exception the exception
   */
  public static long getObjectSize(final Object object) {
    if (globalInstrumentation == null) {
      throw new IllegalStateException("Agent not initialized.");
    }
    return globalInstrumentation.getObjectSize(object);
  }
}