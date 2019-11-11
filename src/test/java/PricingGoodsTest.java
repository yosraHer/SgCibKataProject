
import domain.config.TestConfiguration;
import domain.model.Product;
import domain.model.SpecialProduct;
import domain.service.api.PricingGoodsService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@ContextConfiguration(classes = {TestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PricingGoodsTest {
    private TreeMap<String, Integer> simpleGoodsToBuy;
    private TreeMap<String, Integer> complexGoodsToBuy;
    private java.util.List<SpecialProduct> complexPriceList = new ArrayList<>();
    private List<Product> simplePriceList = new ArrayList<>();

    @Autowired
    private PricingGoodsService pricingGoodsService;


    @Before
    public void setUp() {
        loadSimplePriceList();
        loadSimpleGoodsToBuy();
        loadComplexPriceList();
        loadComplexGoodsToBuy();

    }

    private void loadComplexPriceList() {
        complexPriceList.add(new SpecialProduct("B", new BigDecimal(14), 2));
        complexPriceList.add(new SpecialProduct("E", new BigDecimal(10), 3));
    }

    private void loadSimplePriceList() {
        simplePriceList.add(new Product("A", new BigDecimal(4)));
        simplePriceList.add(new Product("B", new BigDecimal(8.55)));
        simplePriceList.add(new Product("C", new BigDecimal(10.5)));
        simplePriceList.add(new Product("D", new BigDecimal(8.01)));

    }

    private void loadComplexGoodsToBuy() {
        complexGoodsToBuy = new TreeMap<String, Integer>();
        complexGoodsToBuy.put("B", 5);
    }

    private void loadSimpleGoodsToBuy() {
        simpleGoodsToBuy = new TreeMap<String, Integer>();
        simpleGoodsToBuy.put("A", 1);
    }

    @Test
    public void simplePricingGoodsTest() {
        BigDecimal amount = pricingGoodsService.getTotalAmountGoods(simpleGoodsToBuy, complexPriceList, simplePriceList);
        Assert.assertEquals(new BigDecimal(4), amount);
    }

    @Test
    public void complexPricingGoodsTest() {
        BigDecimal result = pricingGoodsService.getTotalAmountGoods(complexGoodsToBuy, complexPriceList, simplePriceList);
        BigDecimal amount = (new BigDecimal(8.55)).add(new BigDecimal(14).multiply(new BigDecimal(2)));
        Assert.assertEquals(amount, result);
    }
}
