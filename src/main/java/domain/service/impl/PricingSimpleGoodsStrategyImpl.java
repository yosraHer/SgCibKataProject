package domain.service.impl;


import domain.model.Product;
import domain.service.api.PricingGoodsStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PricingSimpleGoodsStrategyImpl implements PricingGoodsStrategy {

    private List<Product> simplePriceList;

    public PricingSimpleGoodsStrategyImpl(List<Product> simplePriceList) {
        this.simplePriceList = simplePriceList;
    }

    @Override
    public BigDecimal getTotalPrice(Product product, int quantity) {

            Product result = simplePriceList.stream().filter(p -> product.getName().equals(p.getName())).findAny()
                    .orElse(null);
            if (result != null)
                return (result.getPrice().multiply(new BigDecimal(quantity)));
            return null;

    }
}
