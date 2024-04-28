package model;

public class ExchangeRate {
    private long id;
    private Currency base;
    private Currency target;
    private double rate;

    public ExchangeRate(long id, Currency base, Currency target, double rate) {
        this.id = id;
        this.base = base;
        this.target = target;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getBase() {
        return base;
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    public Currency getTarget() {
        return target;
    }

    public void setTarget(Currency target) {
        this.target = target;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
