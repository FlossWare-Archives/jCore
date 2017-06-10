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

import org.flossware.jcore.utils.ExceptionUtils;
import org.flossware.jcore.utils.ObjectUtils;

/**
 * This implementation determines if an exception is contained within an exception.
 * 
 * @author Scot P. Floess
 */
public final class ContainsExceptionProcessor implements ExceptionProcessor {
    /**
     * The class of exception desired.
     */
    private final Class klass;
    
    /**
     * Return the class of exception desired.
     * 
     * @return the class of exception desired.
     */
    private Class getKlass() {
        return klass;
    }
    
    /**
     * Sets the class to be searched.
     * 
     * @param klass to be searched.
     * 
     * @throws IllegalArgumentException if klass null.
     */
    public ContainsExceptionProcessor(final Class klass) {
        this.klass = ObjectUtils.ensureObject(klass, "Cannot have a null class!");
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean isExceptionApplicable(final Throwable throwable) {
        return ExceptionUtils.containsThrowable(throwable, getKlass());
    }
}
