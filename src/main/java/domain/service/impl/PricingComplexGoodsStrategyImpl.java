package domain.service.impl;


import domain.model.Product;
import domain.service.api.PricingGoodsStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingComplexGoodsStrategyImpl implements PricingGoodsStrategy {

    private List<Product> simplePriceList;
    private List<Product> complexPriceList;

    public PricingComplexGoodsStrategyImpl(List<Product> simplePriceList, List<Product> complexPriceList) {
        this.simplePriceList = simplePriceList;
        this.complexPriceList = complexPriceList;
    }

    @Override
    public BigDecimal getTotalPrice(Product product, int quantity) {
        Product complexProduct = complexPriceList.stream().filter(p -> product.getName().equals(p.getName())).findAny()
                .orElse(null);
        if (complexProduct != null) {
            Product simpleProduct = simplePriceList.stream().filter(p -> p.getName().equals(product.getName())).findAny()
                    .orElse(null);
            if (quantity > complexProduct.getQuantity()) {

            return  ((simpleProduct.getPrice()).multiply(new BigDecimal(quantity % complexProduct.getQuantity()))).add((complexProduct.getPrice()).multiply(new BigDecimal(quantity / complexProduct.getQuantity())));

            }
            else if (quantity < complexProduct.getQuantity()) {

              return  simpleProduct.getPrice().multiply(new BigDecimal(quantity));
            }
            else {
              return complexProduct.getPrice();
            }
        }
        return null;
    }
}
