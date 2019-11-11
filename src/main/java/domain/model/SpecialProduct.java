package domain.model;

import java.math.BigDecimal;

public class SpecialProduct extends Product {
    private Integer quantity;

    public SpecialProduct(String name, BigDecimal price, Integer quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
