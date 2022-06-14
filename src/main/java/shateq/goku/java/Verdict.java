package shateq.goku.java;

public enum Verdict {
    WIN("1 - 0"), LOSE("0 - 1"), DRAW("0.5");

    public final String mark;

    Verdict(String mark) {
        this.mark = mark;
    }
}