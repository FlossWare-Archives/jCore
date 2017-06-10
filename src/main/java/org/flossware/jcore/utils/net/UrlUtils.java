/*
 * Copyright (C) 2016 Scot P. Floess
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
package org.flossware.jcore.utils.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.utils.LoggerUtils;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.StringUtils;

/**
 *
 * URL utility class.
 *
 * @author sfloess
 *
 */
public class UrlUtils {

    /**
     * Protocol separator.
     */
    public static final String PROTOCOL_SEPARATOR = "://";

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(UrlUtils.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Converts <code>rawURL</code> to a URL.
     *
     * @param rawUrlStr the complete raw URL (including any additional paths).
     *
     * @return a URL.
     *
     * @throws UrlException if computing the URL fails.
     */
    public static URL createUrl(final String rawUrlStr) {
        try {
            URL retVal = new URL(rawUrlStr);

            LoggerUtils.log(getLogger(), Level.FINEST, "URL [{0}] for string [{1}]", retVal, rawUrlStr);

            return retVal;
        } catch (final MalformedURLException malformedUrlException) {
            getLogger().log(Level.SEVERE, "Trouble getting protocol and host [{0}]", malformedUrlException.getMessage());

            throw new UrlException(malformedUrlException);
        }
    }

    /**
     * Using protocol and host, return a URL string in the form of [protocol]://[host].
     *
     * @param protocol the web protocol like https.
     * @param host     the host being called.
     *
     * @return a URL in the form of [protocol]://[host]..
     */
    static String computeUrlString(final String protocol, final String host) {
        StringUtils.ensureString(protocol, "Must provide a protocol!");
        StringUtils.ensureString(host, "Must provide a host!");

        final String retVal = StringUtils.concat(protocol, PROTOCOL_SEPARATOR, host);

        LoggerUtils.log(getLogger(), Level.FINEST, "Computed URL [{0}]", retVal);

        return retVal;
    }

    /**
     * Using <code>url</code>, compute the protocol and host portion as a string.
     *
     * @param url the url to convert.
     *
     * @return the protocol and host portion of <code>url</code> as a string.
     */
    static String computeProtocolAndHostString(final URL url) {
        ObjectUtils.ensureObject(url, "Must provide a url!");

        final String retVal = computeUrlString(url.getProtocol(), url.getHost());

        LoggerUtils.log(getLogger(), Level.FINEST, "Computed URL [{0}]", retVal);

        return retVal;
    }

    /**
     * Taking the <code>rawURL</code>, will return the URL for just the server.
     *
     * @param rawUrlStr The complete raw URL (including any additional paths).
     *
     * @return the server's URL including protocol.
     *
     * @throws IllegalArgumentException if computing the URL fails.
     */
    public static String computeProtocolAndHostString(final String rawUrlStr) {
        StringUtils.ensureString(rawUrlStr, "Must provide a URL string!");

        final String retVal = computeProtocolAndHostString(createUrl(rawUrlStr));

        LoggerUtils.log(getLogger(), Level.FINEST, "String URL [{0}] for raw string [{1}]", retVal, rawUrlStr);

        return retVal;
    }

    /**
     * Using <code>rawUrlStr</code>, convert to protocol and host version.
     *
     * @param rawUrlStr the raw URL to convert.
     *
     * @return a URL representation of only protocol and host.
     */
    public static URL computeHostUrl(final String rawUrlStr) {
        StringUtils.ensureString(rawUrlStr, "Must provide a URL string!");

        final URL retVal = createUrl(computeProtocolAndHostString(rawUrlStr));

        LoggerUtils.log(getLogger(), Level.FINEST, "Host URL [{0}] for raw string [{1}]", retVal, rawUrlStr);

        return retVal;
    }

    /**
     * Default constructor not allowed.
     */
    private UrlUtils() {
    }
}
