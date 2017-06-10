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

/**
 * This implementation can be used in scenarios like a "catch all" for all exception processing.
 * 
 * @author Scot P. Floess
 */
public final class UnknownExceptionProcessor implements ExceptionProcessor {   
    /**
     * Default constructor.
     */
    public UnknownExceptionProcessor() {
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean isExceptionApplicable(final Throwable throwable) {
        return true;
    }
}
