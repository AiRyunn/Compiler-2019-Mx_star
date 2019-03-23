package logging;

public class Logging {
    static int level = 0;

    public static void debug(String msg) {
        if (level > 0) {
            return;
        }
        String classname = new Exception().getStackTrace()[1].getClassName();
        String method_name = new Exception().getStackTrace()[1].getMethodName();
        System.err.println("[debug]: " + classname + "." + method_name + " > " + msg);
    }

    public static void info(String msg) {
        if (level > 1) {
            return;
        }
        String classname = new Exception().getStackTrace()[1].getClassName();
        String method_name = new Exception().getStackTrace()[1].getMethodName();
        System.err.println("[info]: " + classname + "." + method_name + " > " + msg);
    }

    public static void warning(String msg) {
        if (level > 2) {
            return;
        }
        String classname = new Exception().getStackTrace()[1].getClassName();
        String method_name = new Exception().getStackTrace()[1].getMethodName();
        System.err.println("[warning]: " + classname + "." + method_name + " > " + msg);
    }

    public static void error(String msg) {
        if (level > 3) {
            return;
        }
        String classname = new Exception().getStackTrace()[1].getClassName();
        String method_name = new Exception().getStackTrace()[1].getMethodName();
        System.err.println("[error]: " + classname + "." + method_name + " > " + msg);
    }
}