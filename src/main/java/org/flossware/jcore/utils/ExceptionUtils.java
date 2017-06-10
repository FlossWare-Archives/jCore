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
package org.flossware.jcore.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception utility class.
 *
 * @author Scot P. Floess
 */
public final class ExceptionUtils {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ExceptionUtils.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Return true if both throwable and containedThrowable are not null (nice short hand method for testing).
     *
     * @param throwable          must be non null to be processable.
     * @param containedThrowable must be non null to be processable.
     *
     * @return true if both throwable and containedThrowable are not null.
     */
    static boolean isContainsProcessable(final Throwable throwable, final Class containedThrowable) {
        final boolean retVal = null != throwable && null != containedThrowable;

        LoggerUtils.log(getLogger(), Level.FINEST, "Contains processable [{0}] for throwable {1} and contained {2}", retVal, throwable, containedThrowable);

        return retVal;
    }

    /**
     * Return true if throwable or any of its root causes is a derived class of containedThrowable.
     *
     * @param <T>                the type of throwable to see if contained.
     *
     * @param throwable          to examine if it or any root causes is a subclass of containedThrowable.
     * @param containedThrowable used to see if is contained within all the causes.
     *
     * @return true if throwable or any of its root causes is a derived class of containedThrowable.
     */
    public static <T extends Throwable> boolean containsThrowable(final Throwable throwable, final Class<T> containedThrowable) {
        if (!isContainsProcessable(throwable, containedThrowable)) {
            LoggerUtils.log(getLogger(), Level.FINEST, "The throwable {0} is NOT contained in {1}", containedThrowable, throwable);

            return false;
        }

        if (containedThrowable.isAssignableFrom(throwable.getClass())) {
            LoggerUtils.log(getLogger(), Level.FINEST, "The throwable {0} IS contained in {1}", containedThrowable, throwable);

            return true;
        }

        LoggerUtils.log(getLogger(), Level.FINEST, "Examinging the cause {0} for containment of {1}", throwable.getCause(), containedThrowable);

        return ExceptionUtils.containsThrowable(throwable.getCause(), containedThrowable);
    }

    /**
     * Return true if throwable or any of its root causes is containedThrowable.
     *
     * @param throwable          to examine if it or any root causes is a subclass of containedThrowable.
     * @param containedThrowable used to see if is contained within all the causes.
     *
     * @return if throwable or its root causes is an contained, or false if not.
     */
    public static boolean containsThrowable(final Throwable throwable, final Throwable containedThrowable) {
        boolean retVal = containsThrowable(throwable, ObjectUtils.ensureObject(containedThrowable, "Must have a throwable to check").getClass());
        
        LoggerUtils.log(getLogger(), Level.FINEST, "Contains [{0}] for the containment of {1} in {2}", retVal, containedThrowable, throwable);
        
        return retVal;
    }

    /**
     * Return true if throwable or any of its root causes is an IOException.
     *
     * @param throwable to examine if being an IOException or any root causes is an IOException.
     *
     * @return if throwable or its root causes is an IOException, or false if not.
     */
    public static boolean containsIOException(final Throwable throwable) {
        return containsThrowable(throwable, IOException.class);
    }

    /**
     * Return true if <code>msg</code> is contained in <code>failure</code>'s getMessage().
     *
     * @param failure is the failure to examine for a message.
     * @param msg the message to examine.
     * 
     * @return true if <code>msg</code> is contained in <code>failure</code>'s getMessage().
     */
    public static boolean isExceptionMsgContained(final Throwable failure, final String msg) {
        LoggerUtils.log(getLogger(), Level.FINEST, "Seeing [{0}] is in {1}", msg, failure);
        
        StringUtils.ensureString(msg, "Cannot determine if a failure contains a blank or null msg");

        if (null == failure) {
            LoggerUtils.log(getLogger(), Level.FINEST, "Failure is null - not containerd in {0}", failure);

            return false;
        }

        if (failure instanceof InvocationTargetException) {
            LoggerUtils.log(getLogger(), Level.FINEST, "Failure is an instance of InvocationTargetException [{0}] - determining target exception containment [{1}]", failure, ((InvocationTargetException) failure).getTargetException());

            return isExceptionMsgContained(((InvocationTargetException) failure).getTargetException(), msg);
        }

        LoggerUtils.log(getLogger(), Level.FINEST, "Checking the string [{0}] to the failure's message [{1}]", msg, failure.getMessage());

        return StringUtils.isContained(failure.getMessage(), msg);
    }

    /**
     * Not allowed.
     */
    private ExceptionUtils() {
    }
}
