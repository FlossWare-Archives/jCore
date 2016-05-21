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
package org.flossware.jcore.collections.collection;

import java.util.Collection;
import org.flossware.jcore.utils.collections.CollectionUtils;

/**
 * Default implementation of a functional collection.
 *
 * @author sfloess
 */
public class DefaultFunctionalCollection<V> extends AbstractFunctionalCollection<V> {

    /**
     * Our set.
     */
    private final Collection<V> collection;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<V> getCollection() {
        return collection;
    }

    /**
     * This constructor sets the set to be used.
     *
     * @param collection the collection to use.
     *
     * @throws IllegalArgumentException if <code>collection</code> is null.
     */
    public DefaultFunctionalCollection(final Collection<V> collection) {
        this.collection = CollectionUtils.ensureCollection(collection, 0, "Collection cannot be null!");
    }
}
