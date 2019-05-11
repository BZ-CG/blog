package cn.edu.pzhu.base;

import cn.edu.pzhu.base.util.AddressUtils;
import cn.edu.pzhu.base.util.HttpClientUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    public void testHttpClient() {
        String qqStr = "http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=1245978718";
        String json = HttpClientUtils.send(qqStr);

        json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
        json = json.substring(1, json.length() - 1);

        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("1245978718");


        System.out.println("url:" + jsonArray.get(0));
        System.out.println("name:" + jsonArray.get(6));

    }

    @Test
    public void test() {
        String ip = "219.136.134.157";
        String result = "";
        try {
            result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject resultObject = JSONObject.fromObject(result);
        System.out.println(resultObject);

        String region = JSONObject.fromObject(resultObject.get("data")).get("region").toString();
        String city = JSONObject.fromObject(resultObject.get("data")).get("city").toString();
        String county = JSONObject.fromObject(resultObject.get("data")).get("county").toString();

        System.out.println("省份:" + region);
        System.out.println("城市:" + city);
        System.out.println("区/县:" + county);
    }
}
