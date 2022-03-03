public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * 
     * This class will going to be used to print debug information to the console.
     * 
     */

    private static boolean isEnabled = false;

    public static void enable() {
        isEnabled = true;
    }
    public static void disable() {
        isEnabled = false;
    }

    public static void log(String message) {
        if (isEnabled) {
            System.out.println(message);
        }
    
    }

    //overload with color and message
    public static void log(String color, String message) {

        if(!isEnabled) {
            return;
        }

        switch (color.toLowerCase()) {
            case "red":
                System.out.println(ANSI_RED + message + ANSI_RESET);
                break;
            case "green":
                System.out.println(ANSI_GREEN + message + ANSI_RESET);
                break;
            case "blue":
                System.out.println(ANSI_BLUE + message + ANSI_RESET);
                break;
            case "yellow":
                System.out.println(ANSI_YELLOW + message + ANSI_RESET);
                break;
            case "purple":
                System.out.println(ANSI_PURPLE + message + ANSI_RESET);
                break;
            case "cyan":
                System.out.println(ANSI_CYAN + message + ANSI_RESET);
                break;
            case "white":
                System.out.println(ANSI_WHITE + message + ANSI_RESET);
                break;
            default:
                System.out.println(message);
                break;
        }
    }


}
