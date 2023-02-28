package util;

public class MyUtil {
    public static void myCheckIndex(int index, int length) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index should be bigger than 0");
        } else if (index >= length) {
            throw new IndexOutOfBoundsException("index should be within size");
        }
    }
}
