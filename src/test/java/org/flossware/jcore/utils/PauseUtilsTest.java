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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the PauseUtils utility class.
 *
 * @author Scot P. Floess
 */
public class PauseUtilsTest {
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = PauseUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test a pause.
     */
    @Test
    public void testPause() {
        long startTime = System.currentTimeMillis();

        int pauseTime = 250;

        PauseUtils.pause(pauseTime);

        Assert.assertTrue("Should have paused correct time", System.currentTimeMillis() >= startTime + pauseTime);

        startTime = System.currentTimeMillis();

        pauseTime = 500;

        PauseUtils.pause(pauseTime);

        Assert.assertTrue("Should have paused correct time", System.currentTimeMillis() >= startTime + pauseTime);
    }

    /**
     * Test a random pause.
     */
    @Test
    public void testRandomPause() {
        long startTime = System.currentTimeMillis();

        int pauseTime = 500;

        PauseUtils.randomPause(pauseTime);

        Assert.assertTrue("Should have paused correct time", System.currentTimeMillis() <= startTime + pauseTime);

        startTime = System.currentTimeMillis();

        pauseTime = 750;

        PauseUtils.randomPause(pauseTime);

        Assert.assertTrue("Should have paused correct time", System.currentTimeMillis() <= startTime + pauseTime);
    }
}
