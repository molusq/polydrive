package xception.unchecked;

class MyUsefulException extends RuntimeException {
    MyUsefulException(String msg) {
        super(msg);
    }
}