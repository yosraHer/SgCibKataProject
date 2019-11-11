package domain.service.api;


import domain.model.Product;
import domain.model.SpecialProduct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public interface PricingGoodsService {
    public BigDecimal getTotalAmountGoods(TreeMap<String, Integer> goodsToBuy, List<SpecialProduct> complexPriceList, List<Product> simplePriceList);
}
