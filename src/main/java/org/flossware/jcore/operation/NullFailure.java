/*
 * Copyright (C) 2014 Scot P. Floess
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
package org.flossware.jcore.operation;

import java.util.logging.Level;
import org.flossware.jcore.AbstractCommonBase;

/**
 * Null implementation of a failure.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of object used when a failure arose.
 */
public class NullFailure<V> extends AbstractCommonBase implements Failure<V> {

    /**
     * Default constructor.
     */
    public NullFailure() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void failed(final V obj, final Throwable failure) {
        log(Level.FINEST, "Failed obj [{0}] failure {1}", obj, failure);
    }
}
