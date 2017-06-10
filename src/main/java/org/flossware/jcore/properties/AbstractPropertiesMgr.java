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
package org.flossware.jcore.properties;

import java.util.Properties;
import org.flossware.jcore.AbstractCommonBase;
import org.flossware.jcore.utils.ObjectUtils;

/**
 * Abstract base class for property management.
 *
 * @author sfloess
 */
public abstract class AbstractPropertiesMgr extends AbstractCommonBase implements PropertiesMgr {
    /**
     * Our properties we manage.
     */
    private final Properties properties;

    /**
     * This constructor sets the properties to manage.
     *
     * @param properties the properties to manage.
     *
     * @throws IllegalArgumentException if <code>properties</code> is null.
     */
    protected AbstractPropertiesMgr(final Properties properties) {
        this.properties = ObjectUtils.ensureObject(properties, "Must provide properties!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties() {
        return properties;
    }
}
