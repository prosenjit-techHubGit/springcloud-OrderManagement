package solr;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.join.ScoreMode;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ElasticSearchClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Client client = null;

		try {
			client = new PreBuiltTransportClient(Settings.builder().put("client.transport.sniff", true)
					.put("cluster.name", "globo-monitoring").build()).addTransportAddress(
							new InetSocketTransportAddress(InetAddress.getByName("192.168.56.101"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// queryDocument(client);

		searchDocument(client, "logstash-2018.12.22", "Doe", "", "");

		/*
		 * String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564," +
		 * "\"fullName\":\"John Doe\"}"; IndexResponse response =
		 * client.prepareIndex("people", "Doe").setSource(jsonObject,
		 * XContentType.JSON).get();
		 * 
		 * String id = response.getId(); String index = response.getIndex(); String type
		 * = response.getType(); long version = response.getVersion();
		 * 
		 * System.out.println(id);
		 */

	}

	public static void queryDocument(Client client) {

		ObjectMapper mapper = new ObjectMapper();

		SearchResponse response = client.prepareSearch().execute().actionGet();
		List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
		List<Person> results = new ArrayList<Person>();
		searchHits.forEach(hit -> {
			try {
				results.add(mapper.readValue(hit.getSourceAsString(), Person.class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		results.forEach(p -> System.out.println(p.getFullName()));

	}

	public static void searchDocument(Client client, String index, String type,

			String field, String value) {
		
		final AggregationBuilder aggregation = AggregationBuilders
			    .terms("customer")
			    .field("Service.keyword")
			    .size(10);

		SearchResponse response = client.prepareSearch(index)

				.setSearchType(SearchType.QUERY_AND_FETCH).setQuery(buildQuery())
				.addAggregation(aggregation)		
			
				.setExplain(true)

				.execute()
				.actionGet();

		System.out.println(response.getAggregations().asList().get(0).getMetaData());		

		SearchHit[] results = response.getHits().getHits();
		
		

		System.out.println("Current results: " + results.length);

		for (SearchHit hit : results) {

			System.out.println("------------------------------");

			Map<String, Object> result = hit.getSource();

			System.out.println(result);

		}

	}

	public static org.elasticsearch.index.query.QueryBuilder buildQuery() {
		final BoolQueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("level_value").gte(1000));
				

		return query;

	}

}
