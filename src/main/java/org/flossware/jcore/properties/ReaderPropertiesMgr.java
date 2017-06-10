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

import java.io.Reader;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.PropertiesUtils;

/**
 * Using a reader as a start, will load properties from the stream.
 *
 * @author sfloess
 */
public class ReaderPropertiesMgr extends AbstractPropertiesMgr {
    public ReaderPropertiesMgr(final Reader reader, boolean closeStream) {
        super(PropertiesUtils.createProperties(ObjectUtils.ensureObject(reader, "Must provide a reader!"), closeStream));
    }

    public ReaderPropertiesMgr(final Reader reader) {
        this(reader, false);
    }
}
