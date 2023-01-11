package Exceptions;

import java.io.IOException;

public class DuplicatedKeyException  extends IOException {
    public DuplicatedKeyException(String key_already_exists) {
        super(key_already_exists);
    }
}


