package com.itjing;

import com.alibaba.fastjson.JSON;
import com.itjing.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * es 7.6.x 高级客户端测试 API
 */
@SpringBootTest
class EsApiApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 测试索引的创建  Request
    @Test
    public void testCreateIndex() {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("jing_index");
        // 2、客户端执行请求 IndicesClient，请求后获得响应
        try {
            CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试获取索引，判断其是否存在
    @Test
    public void testGetIndex() {
        GetIndexRequest request = new GetIndexRequest("jing_index");
        try {
            boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            System.out.println(exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试删除索引
    @Test
    public void testDeleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest("jing_index");
        try {
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(response.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("晶哥哥", 23);
        // 创建请求
        IndexRequest request = new IndexRequest("jing_index");
        // 规则 PUT /jing_index/_doc/1
        // 链式
        request.id("1")
                // .timeout(TimeValue.timeValueSeconds(1))
                .timeout("1s")
                // 将我们的数据放入请求  json
                .source(JSON.toJSONString(user), XContentType.JSON);

        // 客户端发送请求
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status()); //对应我们命令返回的状态
    }

    // 获取文档，判断是否存在   GET /jing_index/doc/1
    @Test
    void testIsExistsDocument() throws IOException {
        GetRequest request = new GetRequest("jing_index", "1");
        // 不获取返回的_source的上下文了
        request.fetchSourceContext(new FetchSourceContext(false))
                .storedFields("_none_");
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    // 获取文档的信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("jing_index", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString()); //打印文档内容
        System.out.println(response); //返回的全部内容和kibana中的命令操作后是一样的
    }

    // 更新文档信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("jing_index", "1");
        request.timeout("1s");

        User user = new User("晶哥学Java", 18);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.status());
    }

    // 删除文档记录
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("jing_index", "1");
        request.timeout("1s");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.status());
    }

    // 特殊的，真正的项目一般都会批量插入数据！
    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        List<User> userList = new ArrayList<>();
        userList.add(new User("Jinggege1", 23));
        userList.add(new User("Jinggege2", 23));
        userList.add(new User("Jinggege3", 23));
        userList.add(new User("Jinggege4", 23));
        userList.add(new User("Jinggege5", 23));
        userList.add(new User("Jinggege6", 23));
        userList.add(new User("Jinggege7", 23));

        // 批处理请求
        for (int i = 0; i < userList.size(); i++) {
            // 批量更新和批量删除，就在这里修改对应的请求就可以了
            bulkRequest.add(
                    new IndexRequest("jing_index")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(userList.get(i)), XContentType.JSON)
            );
        }
        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.hasFailures()); // 查看是否失败，返回 false 代表成功！
    }

    // 测试搜索
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 构建高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder
    // xxx QueryBuilder 对应我们刚刚看到的命令
    @Test
    public void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("jing_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件，我们可以使用 QueryBuilders 工具类来实现
        // QueryBuilders.termQuery 精确匹配
        // QueryBuilders.matchAllQuery() 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "jinggege1"); // 英文首字母要小写
        // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("------------------------");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }
}
