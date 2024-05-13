package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ExchangeRate {
    private final Long id;
    private final Long base;
    private final Long target;
    private final Double rate;
}
