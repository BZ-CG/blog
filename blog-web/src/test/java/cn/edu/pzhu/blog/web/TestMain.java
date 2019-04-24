package cn.edu.pzhu.blog.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试类.
 * @author:CG
 * @date:2019/04/21 21:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMain {

    @Test
    public void test() {
        String str = "12,4--+-,sad";
        String[] strings = str.split(",");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
