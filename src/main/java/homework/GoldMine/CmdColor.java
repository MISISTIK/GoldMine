package homework.GoldMine;

public enum CmdColor {
    ANSI_RESET("\u001B[0m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_WHITE("\u001b[37m"),
    ANSI_YELLOW("\u001b[33m");

    private String color;

    public String getColor() {
        return color;
    }

    CmdColor(String color) {
        this.color = color;
    }

}
