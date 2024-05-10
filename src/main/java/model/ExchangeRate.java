package model;

public class ExchangeRate extends Model {
    private final Long id;
    private final Long base;
    private final Long target;
    private final Double rate;

    public ExchangeRate(Long id, Long base, Long target, Double rate) {
        this.id = id;
        this.base = base;
        this.target = target;
        this.rate = rate;
    }


    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", base=" + base +
                ", target=" + target +
                ", rate=" + rate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Long getBase() {
        return base;
    }

    public Long getTarget() {
        return target;
    }

    public Double getRate() {
        return rate;
    }

}
