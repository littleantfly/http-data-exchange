package com.enneagon.digital.exchange.procotol.nuonuo;

import com.enneagon.digital.exchange.procotol.nuonuo.utils.DESDZFP;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class NuoNuoExampleTests {

    @Test
    public void test(){

        String SendUrl="https://localhost/cxfServerKpOrderSync.action";
        String order="{\"identity\":\"2329CC5F90EDAA8208F1F3C72A0CE72A713A9D425CD50CDE\",\"order\":{\"buyername\":\"浙江爱信诺\",\"taxnum\":\"124511234993295177\",\"phone\":\"0\",\"address\":\"浙江省杭州市万塘路\",\"account\":\"\",\"telephone\":\"0\",\"orderno\":\"nuonuo12345\",\"invoicedate\":\"2018-10-31 19:16:51\",\"clerk\":\"黄芝\",\"saleaccount\":\"宇宙行442612010103507108\",\"salephone\":\"0774-7893911\",\"saleaddress\":\"富川瑶族自治县新 永路 138 号 \",\"saletaxnum\":\"339901999999142\",\"kptype\":\"1\",\"message\":\"\",\"payee\":\"林莉苏 \",\"checker\":\"林莉苏 \",\"tsfs\":\"-1\",\"email\":\"502192347@qq.com\",\"qdbz\":\"0\",\"qdxmmc\":\"\",\"dkbz\":\"0\",\"deptid\":\"\",\"clerkid\":\"\",\"invoiceLine\":\"p\",\"cpybz\":\"\",\"detail\":[{\"goodsname\":\"苹果 \",\"num\":\"1\",\"price\":\"1\",\"hsbz\":\"1\",\"taxrate\":\"0.13\",\"spec\":\"\",\"unit\":\"吨 \",\"spbm\":\"10101150101\",\"zsbm\":\"\",\"fphxz\":\"0\",\"yhzcbs\":\"0\",\"zzstsgl\":\"\",\"lslbs\":\"\",\"kce\":\"\"}]}}";
        order= DESDZFP.encrypt(order);
        HttpClient httpclient=null;
        PostMethod post=null;
        try{
            httpclient = new HttpClient();
            post = new PostMethod(SendUrl);
            //设置编码方式
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
            //添加参数
            post.addParameter("order",order);
            //执行
            httpclient.executeMethod(post);
            //接口返回信息
            String info = new String(post.getResponseBody(),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭连接，释放资源
            post.releaseConnection();
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
        }



    }
}
