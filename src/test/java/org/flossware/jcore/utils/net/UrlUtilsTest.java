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

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the FileUtils utility class.
 *
 * @author Scot P. Floess
 */
public class UrlUtilsTest {

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = UrlUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Tests creating a URL with a null.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_null() {
        UrlUtils.createUrl(null);
    }

    /**
     * Tests creating a URL with an empty string.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_emptyString() {
        UrlUtils.createUrl("");
    }

    /**
     * Tests creating a URL with a bad string.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_badString() {
        UrlUtils.createUrl("ht://foo.com");
    }

    /**
     * Test creating a URL.
     *
     * @throws IOException
     */
    @Test
    public void test_createUrl() throws IOException {
        final URL toCompare = UrlUtils.createUrl("http://foo.com:50/foo");

        Assert.assertNotNull("Should have created a url", toCompare);
        Assert.assertEquals("Should be the correct protocol", "http", toCompare.getProtocol());
        Assert.assertEquals("Should be the correct host", "foo.com", toCompare.getHost());
        Assert.assertEquals("Should be the correct port", 50, toCompare.getPort());
        Assert.assertEquals("Should be the correct path", "/foo", toCompare.getPath());
    }

    /**
     * Test computing a URL string with a null protocol.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_protocol_null() {
        UrlUtils.computeUrlString(null, TestUtils.generateUniqueStr());
    }

    /**
     * Test computing a URL string with a blank protocol.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_protocol_blank() {
        UrlUtils.computeUrlString("   ", TestUtils.generateUniqueStr());
    }

    /**
     * Test computing a URL string with an empty protocol.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_protocol_empty() {
        UrlUtils.computeUrlString("", TestUtils.generateUniqueStr());
    }

    /**
     * Test computing a URL string with a null host.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_host_null() {
        UrlUtils.computeUrlString("http", null);
    }

    /**
     * Test computing a URL string with a blank host.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_host_blank() {
        UrlUtils.computeUrlString("ftp:", "    ");
    }

    /**
     * Test computing a URL string with an empty host.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeUrlString_host_empty() {
        UrlUtils.computeUrlString("https", "");
    }

    /**
     * Test computing a URL string.
     */
    @Test
    public void test_computeUrlString() {
        final String host = TestUtils.generateUniqueStr("foo");

        final String urlString = UrlUtils.computeUrlString("https", host);

        Assert.assertEquals("Should be correct URL", "https://" + host, urlString);
    }

    /**
     * Test computing a protocol and host string with a null url.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeProtocolAndHostString_url_null() {
        UrlUtils.computeProtocolAndHostString((URL) null);
    }

    /**
     * Test computing a protocol and host string.
     */
    @Test
    public void test_computeProtocolAndHostString_url() throws MalformedURLException {
        final URL url = new URL("http://foo.com:80/alpha/beta/theta");
        final String toCompare = UrlUtils.computeProtocolAndHostString(url);

        Assert.assertEquals("Should be the correct string", "http://foo.com", toCompare);
    }

    /**
     * Tests computing the host URL with a null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeProtocolAndHostString_rawUrlStr_null() {
        UrlUtils.computeProtocolAndHostString((String) null);
    }

    /**
     * Tests computing the host URL with an empty string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeProtocolAndHostString_rawUrlStr_empty() {
        UrlUtils.computeProtocolAndHostString("");
    }

    /**
     * Tests computing the host URL with a blank string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeProtocolAndHostString_rawUrlStr_blank() {
        UrlUtils.computeProtocolAndHostString("  ");
    }

    /**
     * Tests computing the host URL with a malformed string.
     */
    @Test(expected = UrlException.class)
    public void test_computeProtocolAndHostString_rawUrlStr_malformed() {
        UrlUtils.computeProtocolAndHostString("ht://foo .com");
    }

    /**
     * Tests computing the host URL.
     */
    @Test
    public void test_computeProtocolAndHostString_rawUrlStr() {
        Assert.assertEquals("Should be the correct url", "http://foo.com", UrlUtils.computeProtocolAndHostString("http://foo.com/alpha/beta/theta.txt"));
    }

    /**
     * Tests computing the host URL with a null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeHostUrl_null() {
        UrlUtils.computeHostUrl(null);
    }

    /**
     * Tests computing the host URL with an empty string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeHostUrl_empty() {
        UrlUtils.computeHostUrl("");
    }

    /**
     * Tests computing the host URL with a blank string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeHostUrl_blank() {
        UrlUtils.computeHostUrl("     ");
    }

    /**
     * Tests computing the host URL with a bad string.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrl_badString() {
        UrlUtils.computeHostUrl("ht://foo!.com");
    }

    /**
     * Test computing the host URL.
     *
     * @throws IOException
     */
    @Test
    public void test_computeHostUrl() throws IOException {
        final URL hostUrl = UrlUtils.computeHostUrl("http://foo.com/bar/alpha");

        Assert.assertNotNull("Should have created a url", hostUrl);
        Assert.assertEquals("Should be the correct protocol", "http", hostUrl.getProtocol());
        Assert.assertEquals("Should be the correct protocol", "foo.com", hostUrl.getHost());
    }
}
