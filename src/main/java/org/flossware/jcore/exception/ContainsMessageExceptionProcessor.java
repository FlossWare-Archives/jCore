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
import org.flossware.jcore.utils.StringUtils;

/**
 * This implementation determins if a message is contained within a throwable.
 * 
 * @author Scot P. Floess
 */
public final class ContainsMessageExceptionProcessor implements ExceptionProcessor {
    /**
     * The message to be searched in an exception.
     */
    private final String message;
    
    /**
     * Return the message.
     * 
     * @return the message.
     */
    private String getMessage() {
        return message;
    }
    
    /**
     * Sets the message to be searched in an exception.
     * 
     * @param message to be searched in an exception.
     * 
     * @throws IllegalArgumentException if message is blank or null.
     */
    public ContainsMessageExceptionProcessor(final String message) {
        this.message = StringUtils.ensureString(message, "Cannot have a blank message!");
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean isExceptionApplicable(final Throwable throwable) {        
        return ExceptionUtils.isExceptionMsgContained(ObjectUtils.ensureObject(throwable, "Cannot determine applicability using a null Throwable!"), getMessage());
    }
}
