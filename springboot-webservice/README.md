1、根据wsdl生成客户端代码，有多种方式：cxf之wsdl2java，xfire，axis。

- 三种都依次试过，cxf对有些wsdl不支持，无法生成客户端代码；

- xfire对数组类型不支持，如果参数或者返回数据有数组，则无法使用；

- 最后选择了axis1.4，axis最新似乎到了axis2，没用过。

2、下载axis1.4，解压出来。

3、建一个文件夹，不要在中文目录最好，不然很容易出问题，在其中下建一个bat文件，内容如下：

```bash
# 你解压的目录的lib路径
set Axis_Lib=E:\workspace_idea\ComponentStudy\springboot-webservice\src\main\resources\axis-1_4\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
# 这个可以是本地的文件，也可以是一个运行的服务生成的wsdl，我下面直接使用本地文件了
set Axis_Servlet=http://127.0.0.1:8080/WsProject/services/TestService?wsdl
# 输出路径
set Output_Path=E:\workspace_idea\ComponentStudy\springboot-webservice\src\main\java
# 生成java文件的包名
set Package=com.itjing.webservice.client
# 执行命令
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% dyWsService.wsdl
pause
```

4、然后双击bat文件，则在对应目录下生成了wsdl的客户端代码。

5、使用客户端代码：

使用soapUI模拟wsdl，要修改IDyWsServiceServiceLocator中的地址和soapUI中的对应。

```java
public class TestService {
    public static void main(String[] args) {
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
    }
}
```

也可以如下使用：

```java
public static void main(String[] args) {
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
}
```

