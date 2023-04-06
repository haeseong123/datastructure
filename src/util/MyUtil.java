package util;

public class MyUtil {
    /**
     * assert (0 <= index < size)
     */
    public static void checkElementIndex(int index, int size) {
        if (0 > index || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index, size));
        }
    }

    /**
     * assert (0 <= index <= size)
     */
    public static void checkPositionIndex(int index, int size) {
        if (0 > index || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index, size));
        }
    }

    private static String outOfBoundsMsg(int index, int size) {
        return "Index: " + index + ", Size: " + size;
    }
}
