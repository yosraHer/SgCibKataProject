
import domain.config.TestConfiguration;
import domain.service.api.PricingGoodsService;
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
import java.util.TreeMap;

@ContextConfiguration(classes = { TestConfiguration.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PricingGoodsTest {
    private TreeMap <String, Integer> goodsToBuy;

    @Autowired
    private PricingGoodsService pricingGoodsService;


    @Before
    public void setUp() {
        loadGoodsToBuy();

    }

    private void loadGoodsToBuy() {
        goodsToBuy = new TreeMap<String, Integer>();
        goodsToBuy.put("A", 1);
    }

    @Test
    public void simplePricingGoodsTest()  {
        BigDecimal price = pricingGoodsService.getTotalAmountGoods(goodsToBuy);
      Assert.assertEquals(new BigDecimal(4), price);
    }
}
