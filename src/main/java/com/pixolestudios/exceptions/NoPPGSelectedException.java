package main.java.com.pixolestudios.exceptions;

public class NoPPGSelectedException extends Exception {
    public NoPPGSelectedException() {
        super("No value for pixels per grid selected");
    }
}
