package com.el.unity.log.client;

/**
 * Created by Administrator on 2015/12/8.
 */
public class LogLog {
    /**
     Defining this value makes log4j print log4j-internal debug
     statements to <code>System.out</code>.

     <p> The value of this string is <b>log4j.debug</b>.

     <p>Note that the search for all option names is case sensitive.  */
    public static final String DEBUG_KEY="log4j.debug";


    /**
     Defining this value makes log4j components print log4j-internal
     debug statements to <code>System.out</code>.

     <p> The value of this string is <b>log4j.configDebug</b>.

     <p>Note that the search for all option names is case sensitive.

     @deprecated Use {@link #DEBUG_KEY} instead.
     */
    public static final String CONFIG_DEBUG_KEY="log4j.configDebug";

    protected static boolean debugEnabled = false;

    /**
     In quietMode not even errors generate any output.
     */
    private static boolean quietMode = false;

    private static final String PREFIX = "log4j: ";
    private static final String ERR_PREFIX = "log4j:ERROR ";
    private static final String WARN_PREFIX = "log4j:WARN ";

    static {
        debugEnabled=Boolean.valueOf(System.getProperty("DEBUG_KEY"));
    }

    /**
     Allows to enable/disable log4j internal logging.
     */
    static
    public
    void setInternalDebugging(boolean enabled) {
        debugEnabled = enabled;
    }

    /**
     This method is used to output log4j internal debug
     statements. Output goes to <code>System.out</code>.
     */
    public
    static
    void debug(String msg) {
        if(debugEnabled && !quietMode) {
            System.out.println(PREFIX+msg);
        }
    }

    /**
     This method is used to output log4j internal debug
     statements. Output goes to <code>System.out</code>.
     */
    public
    static
    void debug(String msg, Throwable t) {
        if(debugEnabled && !quietMode) {
            System.out.println(PREFIX+msg);
            if(t != null)
                t.printStackTrace(System.out);
        }
    }


    /**
     This method is used to output log4j internal error
     statements. There is no way to disable error statements.
     Output goes to <code>System.err</code>.
     */
    public
    static
    void error(String msg) {
        if(quietMode)
            return;
        System.err.println(ERR_PREFIX+msg);
    }

    /**
     This method is used to output log4j internal error
     statements. There is no way to disable error statements.
     Output goes to <code>System.err</code>.
     */
    public
    static
    void error(String msg, Throwable t) {
        if(quietMode)
            return;

        System.err.println(ERR_PREFIX+msg);
        if(t != null) {
            t.printStackTrace();
        }
    }

    /**
     In quite mode no LogLog generates strictly no output, not even
     for errors.

     @param quietMode A true for not
     */
    public
    static
    void setQuietMode(boolean quietMode) {
        LogLog.quietMode = quietMode;
    }

    /**
     This method is used to output log4j internal warning
     statements. There is no way to disable warning statements.
     Output goes to <code>System.err</code>.  */
    public
    static
    void warn(String msg) {
        if(quietMode)
            return;

        System.err.println(WARN_PREFIX+msg);
    }

    /**
     This method is used to output log4j internal warnings. There is
     no way to disable warning statements.  Output goes to
     <code>System.err</code>.  */
    public
    static
    void warn(String msg, Throwable t) {
        if(quietMode)
            return;

        System.err.println(WARN_PREFIX+msg);
        if(t != null) {
            t.printStackTrace();
        }
    }
}
