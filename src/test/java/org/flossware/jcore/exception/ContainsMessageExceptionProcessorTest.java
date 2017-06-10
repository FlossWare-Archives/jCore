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

import java.lang.reflect.InvocationTargetException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ContainsMessageExceptionProcessor.
 * 
 * @author Scot P. Floess
 */
public class ContainsMessageExceptionProcessorTest {
    /**
     * Test the constructor with a null message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_null() {
        new ContainsMessageExceptionProcessor(null);
    }
    
    /**
     * Test the constructor with a blank message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_blank() {
        new ContainsMessageExceptionProcessor("   ");
    }
    
    /**
     * Test the constructor with an empty message.
     */       
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_empty() {
        new ContainsMessageExceptionProcessor("");
    }
    
    /**
     * Test the constructor with a good message - should be no exception raised.
     */      
    @Test
    public void test_constructor() {
        new ContainsMessageExceptionProcessor("foo");
    }
    
    /**
     * Test to see if a null throwable is applicable (will raise an exception).
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_isExceptionApplicable_null() {
        new ContainsMessageExceptionProcessor("foo").isExceptionApplicable(null);
    }
    
    /**
     * Tests to see if throwables are applicable.
     */
    @Test
    public void test_isExceptionApplicable() {
        Assert.assertFalse("Should not contain the message", new ContainsMessageExceptionProcessor("foo").isExceptionApplicable(new NullPointerException("alpha")));
        Assert.assertTrue("Should contain the message", new ContainsMessageExceptionProcessor("alpha").isExceptionApplicable(new NullPointerException("alpha")));
        
        final InvocationTargetException ite = new InvocationTargetException(new RuntimeException("beta"), "alpha");
        Assert.assertFalse("Should not contain the message", new ContainsMessageExceptionProcessor("alpha").isExceptionApplicable(ite));
        Assert.assertTrue("Should contain the message", new ContainsMessageExceptionProcessor("beta").isExceptionApplicable(ite));
    }
}
