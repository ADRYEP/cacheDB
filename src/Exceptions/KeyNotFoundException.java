package Exceptions;

public class KeyNotFoundException extends Throwable {
    public KeyNotFoundException(String key_does_not_exist) {
        super(key_does_not_exist);
    }
    public KeyNotFoundException() {
        super();
    }
}
