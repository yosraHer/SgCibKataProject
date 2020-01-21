
import domain.config.Config;
import domain.model.Product;
import domain.service.impl.PricingComplexGoodsStrategyImpl;
import domain.service.impl.PricingSimpleGoodsStrategyImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@ContextConfiguration(classes = {Config.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PricingGoodsTest {
    private List<Product> simpleGoodsToBuy = new ArrayList<>();
    private List<Product> complexGoodsToBuy = new ArrayList<>();
    private Product simpleProduct1;
    private Product simpleProduct2;
    private Product simpleProduct3;
    private Product simpleProduct4;
    private Product complexProduct2;
    private Product complexProduct4;



    @Before
    public void setUp() {
        loadSimpleGoodsToBuy();
        loadComplexGoodsToBuy();

    }

    private void loadComplexGoodsToBuy() {
        complexProduct2 = new Product("p2", new BigDecimal(1), 3);
        complexProduct4 = new Product("p4", new BigDecimal(2), 3);
        complexGoodsToBuy.add(complexProduct2);
        complexGoodsToBuy.add(complexProduct4);
    }

    private void loadSimpleGoodsToBuy() {
        simpleProduct1 = new Product("p1", new BigDecimal(0.65), 1);
        simpleProduct2 = new Product("p2", new BigDecimal(0.40), 1);
        simpleProduct3 = new Product("p3", new BigDecimal(1.99), 1);
        simpleProduct4 = new Product("p4", new BigDecimal(1), 1);
        simpleGoodsToBuy.add(simpleProduct1);
        simpleGoodsToBuy.add(simpleProduct2);
        simpleGoodsToBuy.add(simpleProduct3);
        simpleGoodsToBuy.add(simpleProduct4);
    }


    @Test
    public void OneSimplePricingGoodsTest() {
        Assert.assertEquals(simpleProduct1.getPrice(), simpleProduct1.getTotalPrice(new PricingSimpleGoodsStrategyImpl(simpleGoodsToBuy), 1));
    }



    @Test
    public void complexPricingGoodsTest() {
        BigDecimal result = simpleProduct2.getPrice().multiply(new BigDecimal(2)).add(complexProduct2.getPrice());

        Assert.assertEquals(result, complexProduct2.getTotalPrice(new PricingComplexGoodsStrategyImpl(simpleGoodsToBuy, complexGoodsToBuy), 5));

    }

    @Test
    public void amountOfSimplePricingGoodsTest() {

        BigDecimal result = simpleProduct3.getPrice().multiply(new BigDecimal(4));

        Assert.assertEquals(result, simpleProduct3.getTotalPrice(new PricingSimpleGoodsStrategyImpl(simpleGoodsToBuy), 4));
    }

    @Test
    public void complexPricingGoodsWithFreeItemTest() {
        BigDecimal result = simpleProduct4.getPrice().multiply(new BigDecimal(2));

        Assert.assertEquals(result, complexProduct4.getTotalPrice(new PricingComplexGoodsStrategyImpl(simpleGoodsToBuy, complexGoodsToBuy), 3));

    }
}
