package domain.service.impl;


import domain.model.Product;
import domain.model.SpecialProduct;
import domain.service.api.PricingGoodsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

@Service
public class PricingGoodsServiceImpl implements PricingGoodsService {

    @Override
    public BigDecimal getTotalAmountGoods(TreeMap<String, Integer> goodsToBuy, List<SpecialProduct> complexPriceList, List<Product> simplePriceList) {
        final BigDecimal[] total = {new BigDecimal(0)};
        goodsToBuy.forEach((k, v) -> {
            SpecialProduct specialProduct = complexPriceList.stream().filter(product -> product.getName().equals(k)).findAny()
                    .orElse(null);
            if (specialProduct != null) {
                Product product = simplePriceList.stream().filter(p -> p.getName().equals(k)).findAny()
                        .orElse(null);
                if (v > specialProduct.getQuantity()) {

                   total[0] = total[0].add((product.getPrice()).multiply(new BigDecimal(v % specialProduct.getQuantity()))).add((specialProduct.getPrice()).multiply(new BigDecimal(v / specialProduct.getQuantity())));

                }
                else if (v < specialProduct.getQuantity()) {

                    total[0] = total[0].add(product.getPrice().multiply(new BigDecimal(v)));
                }
                else {
                    total[0] = total[0].add(specialProduct.getPrice());
                }
            } else {
                Product result = simplePriceList.stream().filter(product -> product.getName().equals(k)).findAny()
                        .orElse(null);
                if (result != null) total[0] = total[0].add(result.getPrice());
            }
        });
        return total[0];
    }
}
