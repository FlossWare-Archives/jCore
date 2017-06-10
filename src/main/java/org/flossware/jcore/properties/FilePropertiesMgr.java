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

import java.io.File;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.StringUtils;
import org.flossware.jcore.utils.io.FileUtils;

/**
 * Manages properties from a file.
 *
 * @author sfloess
 */
public final class FilePropertiesMgr extends InputStreamPropertiesMgr {
    public FilePropertiesMgr(final File file) {
        super(FileUtils.getFileInputStream(ObjectUtils.ensureObject(file, "Must provide a file for properties")), true);
    }

    public FilePropertiesMgr(final String fileName) {
        this(new File(StringUtils.ensureString(fileName, "Must provide a file name!")));
    }
}
