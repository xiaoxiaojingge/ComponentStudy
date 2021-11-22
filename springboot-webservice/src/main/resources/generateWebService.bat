set Axis_Lib=E:\workspace_idea\ComponentStudy\springboot-webservice\src\main\resources\axis-1_4\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Axis_Servlet=http://127.0.0.1:8080/WsProject/services/TestService?wsdl
set Output_Path=E:\workspace_idea\ComponentStudy\springboot-webservice\src\main\java
set Package=com.itjing.webservice.client
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% dyWsService.wsdl
pause