/*
 * Copyright (C) 2015 sfloess
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
package org.flossware.jcore.collections.map;

import java.util.Map;
import org.flossware.jcore.utils.collections.MapUtils;

/**
 * Default implementation of a functional map.
 *
 * @author sfloess
 */
public class DefaultFunctionalMap<K, V> extends AbstractFunctionalMap<K, V> {

    /**
     * Our map.
     */
    private final Map<K, V> map;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<K, V> getMap() {
        return map;
    }

    /**
     * This sets the map to be used.
     *
     * @param map the map to use.
     *
     * @throws IllegalArgumentException if <code>map</code> is null.
     */
    public DefaultFunctionalMap(final Map<K, V> map) {
        this.map = MapUtils.ensureMap(map, 0, "Map cannot be null!");
    }
}
