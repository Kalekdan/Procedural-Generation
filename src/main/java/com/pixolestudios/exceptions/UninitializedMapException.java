package main.java.com.pixolestudios.exceptions;

public class UninitializedMapException extends Exception {
    public UninitializedMapException() {
        super("Map has not been initialized");
    }
}
