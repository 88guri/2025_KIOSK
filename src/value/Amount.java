package value;

public class Amount {
    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "원";
    }
}
