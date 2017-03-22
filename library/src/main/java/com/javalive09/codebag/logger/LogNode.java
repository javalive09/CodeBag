package com.javalive09.codebag.logger;

/**
 * Basic interface for a logging system that can output to one or more targets.
 * Note that in addition to classes that will output these logs in some format,
 * one can also implement this interface over a filter and insert that in the chain,
 * such that no targets further down see certain data, or see manipulated forms of the data.
 * You could, for instance, write a "ToHtmlLoggerNode" that just converted all the log data
 * it received to HTML and sent it along to the next node in the chain, without printing it
 * anywhere.
 */
public interface LogNode {

    /**
     * Instructs first LogNode in the list to print the log data provided.
     * @param priority Log level of the data being logged.  Verbose, Error, etc.
     * @param tag Tag for for the log data.  Can be used to organize log statements.
     * @param msg The actual message to be logged. The actual message to be logged.
     * @param tr If an exception was thrown, this can be sent along for the logging facilities
     *           to extract and print useful information.
     */
    public void println(int priority, String tag, String msg, Throwable tr);

}
