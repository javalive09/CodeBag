package com.javalive09.codebag.log;

/**
 * Created by peter on 2017/3/20.
 */

public interface Logger {

    /**
     * @param priority Log level of the data being logged.  Verbose, Error, etc.
     * @param tag      Tag for for the log data.  Can be used to organize log statements.
     * @param msg      The actual message to be logged. The actual message to be logged.
     * @param tr       If an exception was thrown, this can be sent along for the logging facilities
     *                 to extract and print useful information.
     */
    void println(int priority, String tag, String msg, Throwable tr);

}
