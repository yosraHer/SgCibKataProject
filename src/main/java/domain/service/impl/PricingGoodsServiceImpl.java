package domain.service.impl;


import domain.model.Product;
import domain.service.api.PricingGoodsService;
import domain.utils.SimplePricingGoods;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

@Service
public class PricingGoodsServiceImpl implements PricingGoodsService {

    @Override
    public BigDecimal getTotalAmountGoods(TreeMap<String, Integer> goodsToBuy) {
        final BigDecimal[] total = {new BigDecimal(0)};
        goodsToBuy.forEach((k, v) -> {
            Product result = SimplePricingGoods.simplePriceList.stream().filter(product -> product.getName().equals(k)).findAny()
                    .orElse(null);
            if (result != null) total[0] = total[0].add(result.getPrice());

        });
        return total[0];
    }
}
