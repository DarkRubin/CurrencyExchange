package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CurrencyDTO {
    private Long id;
    private String code;
    private String name;
    private String sign;
}
