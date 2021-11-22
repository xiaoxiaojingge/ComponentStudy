package com.itjing.webservice.service;

/**
 * @author lijing
 * @date 2021年11月22日 16:14
 * @description 测试 WebService
 */
public class TestService {
    /*public static void main(String[] args) {
        Ywsljg ywsljg = queryYwsljg("", "");
        System.out.println(ywsljg.getDyqrmc());
    }

    public static Ywsljg queryYwsljg(String ywh, String cxxtmc) {
        try {
            Ywsljg ywsljg = getSoapBindingStub().queryYwsljg(ywh, cxxtmc);
            return ywsljg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static IDyWsServiceServiceSoapBindingStub getSoapBindingStub() throws MalformedURLException, RemoteException {
        IDyWsServiceServiceLocator locator = new IDyWsServiceServiceLocator();
        URL endpoint = new URL(locator.getIDyWsServicePortAddress());
        IDyWsServiceServiceSoapBindingStub _stub = new IDyWsServiceServiceSoapBindingStub(endpoint, locator);
        return _stub;
    }*/

    // 也可以如下使用
    /*public static void main(String[] args) {
        String wsdlUrl = "http://127.0.0.1:8002/reiss/services/dyWsService?wsdl";
        try {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();

            // 生成的客户端代码中有XxxStub类，可如下方式使用
            IDyWsServiceServiceSoapBindingStub stub = new IDyWsServiceServiceSoapBindingStub(new URL(wsdlUrl), service);

            // 有些webservice需要登录，登陆后才能进行一些操作，这个需要设置如下两个参数：
            // 超时时间
            stub.setTimeout(1000 * 60 * 20);
            // 次数设置true，登录后才能保持登录状态，否则第二次调用ws方法时仍然会提示未登录。
            stub.setMaintainSession(true);

            // 调用ws的方法
            Ywsljg ywsljg = stub.queryYwsljg("", "");
            System.out.println(ywsljg.getDyqrmc());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
