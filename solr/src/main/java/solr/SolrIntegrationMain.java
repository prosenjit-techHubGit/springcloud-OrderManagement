package solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrIntegrationMain {
	
	public static void main(String[] args) {
		
		
	String urlString = "http://192.168.56.101:8983/solr/live_logs";
		SolrClient solr = new HttpSolrClient.Builder(urlString).build();
		
	//	String zkHostString = "192.168.56.101:2181/solr/live_logs";
	//	SolrClient solr = new CloudSolrClient.Builder().withZkHost(zkHostString).build();
		
	//	SolrClient solr = new CloudSolrClient.Builder().withSolrUrl("http://192.168.56.101:8983/solr").build();
		
	
		
		//solr.setParser(new XMLResponseParser());
		
		SolrQuery query = new SolrQuery();
	//	query.setQuery(mQueryString);
		
	//	query.set("fl", "category,title,price");
	//	query.setFields("category", "title", "price");
		query.set("q", "request:departments");
		
		QueryResponse response = null;
		
		try {
			response = solr.query(query);
			System.out.println(response);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SolrDocumentList list = response.getResults();
		System.out.println(list);
		
	}

}
