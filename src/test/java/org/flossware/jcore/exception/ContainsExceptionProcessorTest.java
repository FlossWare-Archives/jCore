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
package org.flossware.jcore.exception;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ContainsExceptionProcessor.
 * 
 * @author Scot P. Floess
 */
public class ContainsExceptionProcessorTest {
    /**
     * Test the constructor with a null class.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_null() {
        new ContainsExceptionProcessor(null);
    }
    
    /**
     * Test the constructor with a good class - should be no exception raised.
     */      
    @Test
    public void test_constructor() {
        new ContainsExceptionProcessor(IOException.class);
    }
    
    /**
     * Tests to see if throwables are applicable.
     */
    @Test
    public void test_isExceptionApplicable() {
        Assert.assertFalse("Should not contain the exception", new ContainsExceptionProcessor(NullPointerException.class).isExceptionApplicable(new RuntimeException()));
        Assert.assertFalse("Should not contain the exception", new ContainsExceptionProcessor(NullPointerException.class).isExceptionApplicable(new RuntimeException(new IllegalArgumentException())));
        Assert.assertTrue("Should contain the exception", new ContainsExceptionProcessor(RuntimeException.class).isExceptionApplicable(new RuntimeException()));
        Assert.assertTrue("Should contain the exception", new ContainsExceptionProcessor(RuntimeException.class).isExceptionApplicable(new NullPointerException()));
    }
}
