package value;

public class Amount {
    private int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void add(int extra) {
        value += extra;
    }

    public void subtract(int minus) {
        value -= minus;
    }

    @Override
    public String toString() {
        return value + "원";
    }
}
