package model;

public class Currency extends Model {
    private Long id;
    private final String code;
    private String name;
    private String sign;

    public Currency(Long id, String code, String name, String sign) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sign = sign;
    }

    public Currency(String code, String name, String sign) {
        this.code = code;
        this.name = name;
        this.sign = sign;
    }

    public Currency(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public Long getId() {
        return id;
    }
}
