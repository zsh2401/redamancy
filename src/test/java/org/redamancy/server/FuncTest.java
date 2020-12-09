package org.redamancy.server;


import org.greenrobot.eventbus.EventBus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class FuncTest {
    @Autowired
    private EventBus bus;

    @Test
    public void add() {
        Assert.assertNotNull(bus);
    }
}
