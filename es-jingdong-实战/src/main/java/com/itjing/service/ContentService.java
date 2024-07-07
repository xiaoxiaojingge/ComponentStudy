package com.itjing.service;

import com.alibaba.fastjson.JSON;
import com.itjing.pojo.Content;
import com.itjing.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// 业务编写
@Service
public class ContentService {

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	// 1、解析数据放入 es 索引中
	public Boolean parseContent(String keywords) throws IOException {
		List<Content> contents = HtmlParseUtil.parseJD(keywords);
		// 把查询的数据放入 es 中
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.timeout("2m");

		for (int i = 0; i < contents.size(); i++) {
			bulkRequest.add(new IndexRequest("jd_goods").id("" + (i + 1)) // 不写 id
																			// 就会使用默认id
				.source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
		}

		BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
		return !response.hasFailures();
	}

	// 2、获取数据，实现搜索功能
	public List<Map<String, Object>> searchPage(String keyword, Integer pageNo, Integer pageSize) throws IOException {
		if (pageNo <= 1) {
			pageNo = 1;
		}

		// 条件搜索
		SearchRequest searchRequest = new SearchRequest("jd_goods");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		// 分页
		sourceBuilder.from(pageNo);
		sourceBuilder.size(pageSize);

		// 精准匹配
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
		sourceBuilder.query(termQueryBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		// 执行搜索
		searchRequest.source(sourceBuilder);
		SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

		// 解析结果
		List<Map<String, Object>> list = new ArrayList<>();
		for (SearchHit documentFields : response.getHits().getHits()) {
			list.add(documentFields.getSourceAsMap());
		}
		return list;
	}

	// 3、获取数据，实现搜索功能，并且实现关键字高亮
	public List<Map<String, Object>> searchPageAndHighlight(String keyword, Integer pageNo, Integer pageSize)
			throws IOException {
		if (pageNo <= 1) {
			pageNo = 1;
		}

		// 条件搜索
		SearchRequest searchRequest = new SearchRequest("jd_goods");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		// 分页
		sourceBuilder.from(pageNo);
		sourceBuilder.size(pageSize);

		// 精准匹配
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
		sourceBuilder.query(termQueryBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		// 高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.field("title");
		highlightBuilder.requireFieldMatch(false); // 多个高亮显示！
		highlightBuilder.preTags("<span style='color:red'>");
		highlightBuilder.postTags("</span>");
		sourceBuilder.highlighter(highlightBuilder);

		// 执行搜索
		searchRequest.source(sourceBuilder);
		SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

		// 解析结果
		List<Map<String, Object>> list = new ArrayList<>();
		for (SearchHit documentFields : response.getHits().getHits()) {

			Map<String, HighlightField> HighlightFields = documentFields.getHighlightFields();
			HighlightField title = HighlightFields.get("title");
			Map<String, Object> sourceAsMap = documentFields.getSourceAsMap(); // 原来的结果
			// 解析高亮的字段，将原来的字段换为我们高亮的字段即可！
			if (title != null) {
				Text[] fragments = title.fragments();
				String n_title = "";
				for (Text text : fragments) {
					n_title += text;
				}
				sourceAsMap.put("title", n_title); // 高亮字段替换掉原来的内容即可！
			}
			list.add(sourceAsMap);
		}
		return list;
	}

}
