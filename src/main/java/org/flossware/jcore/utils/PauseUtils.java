/*
 * Copyright (C) 2017 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.jcore.utils;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class to pause execution of code. There are two pauses available: pause for a total time and random pause which will
 * pause up to a pause time in millis (but computed via a random number).
 *
 * @author Scot P. Floess
 */
public final class PauseUtils {
    /**
     * When we need a random pause time, use this to generate a random time to wait.
     */
    public static final Random RANDOM_PAUSE = new Random(System.currentTimeMillis());

    /**
     * Our logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PauseUtils.class.getName());

    /**
     * Return the LOGGER.
     */
    private static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Pauses execution for a total of <code>pauseTime</code> milliseconds.
     *
     * @param pauseTime is the time in millis to pause.
     */
    public static void pause(final int pauseTime) {
        try {
            LoggerUtils.log(getLogger(), Level.INFO, "Pausing current thread [{0} ms]...", pauseTime);

            final byte[] lock = new byte[0];

            synchronized (lock) {
                lock.wait(pauseTime);
            }
        } catch (final InterruptedException ex) {
            LoggerUtils.log(getLogger(), Level.WARNING, "Trouble pausing current thread...", ex);
        }
    }

    /**
     * Pauses execution a random amount of time up to and including <code>maxPause</code> milliseconds.
     *
     * @param maxPause the maximum time of a pause in execution.
     */
    public static void randomPause(final int maxPause) {
        pause(RANDOM_PAUSE.nextInt(maxPause));
    }

    /**
     * Default constructor is not allowed.
     */
    private PauseUtils() {
    }
}
