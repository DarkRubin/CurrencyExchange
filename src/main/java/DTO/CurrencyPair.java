package DTO;

public record CurrencyPair(String base, String target) {
    @Override
    public String target() {
        return target;
    }

    @Override
    public String base() {
        return base;
    }
}
