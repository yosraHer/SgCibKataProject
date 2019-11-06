package domain.utils;

import domain.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SimplePricingGoods {

    public static List<Product> simplePriceList = new ArrayList<>();
    static{
        simplePriceList.add(new Product("A", new BigDecimal(4)));
        simplePriceList.add(new Product("B", new BigDecimal(8.55)));
        simplePriceList.add(new Product("C", new BigDecimal(10.5)));
        simplePriceList.add(new Product("D", new BigDecimal(8.01)));
    }
}
