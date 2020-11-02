package org.example;

public class Count {
    private int cnt = 0;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "Count{" +
                "cnt=" + cnt +
                '}';
    }
}
