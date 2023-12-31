package com.SOA.search.service.implementation;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.SOA.search.dao.ProductElasticSearchRepository;
import com.SOA.search.dto.SearchQuery;
import com.SOA.search.dto.SearchQueryWithPrice;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SearchService;
import com.SOA.search.utils.ElasticSearchUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class SearchServiceElasticSearchImpl implements SearchService {

    private final ProductElasticSearchRepository productElasticSearchRepository;
    private final IndexElasticSearchImpl indexElasticSearch;

    private final ElasticsearchClient elasticsearchClient;

    public SearchServiceElasticSearchImpl(ProductElasticSearchRepository productElasticSearchRepository, IndexElasticSearchImpl indexElasticSearch, ElasticsearchClient elasticsearchClient) {
        this.productElasticSearchRepository = productElasticSearchRepository;
        this.indexElasticSearch = indexElasticSearch;
        this.elasticsearchClient=elasticsearchClient;
    }


    @Override
    public void index(List<Product> products) {
           indexElasticSearch.index(products);
    }



    @Override
    public List<Product> searchByText(SearchQuery searchquery) throws BadRequestException {
        String query = searchquery.getQuery().toLowerCase();

        MatchQuery matchQuery = new MatchQuery.Builder().field("description").query(query).build();
        Query elasticQuery = Query.of(q -> q.match(matchQuery));

        try {
            SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(elasticQuery), Product.class);

            List<Hit<Product>> hits = searchResponse.hits().hits();
            List<Product> matchingProducts = new ArrayList<>();

            for (Hit<Product> hit : hits) {
                matchingProducts.add(hit.source());
            }

            return matchingProducts;

        } catch (IOException e) {
            throw new BadRequestException("Error executing Elasticsearch query", e);
        }
    }


    public SearchResponse<Product> matchProductsWithName(String fieldValue) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierWithNameField(fieldValue);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }


    public SearchResponse<Product> fuzzySearch(SearchQuery searchquery) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.createSupplierQuery(searchquery.getQuery());
        SearchResponse<Product> searchResponse = elasticsearchClient.
                search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch fuzzy query is "+supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> boolQueryImpl(SearchQueryWithPrice searchQueryWithPrice) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierQueryBool(searchQueryWithPrice.getQuery(), searchQueryWithPrice.getMinPrice(),searchQueryWithPrice.getMaxPrice());
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch bool query is "+supplier.get().toString());
        return searchResponse;
    }



    @Override
    public Iterable<Product> findAll() {
        return productElasticSearchRepository.findAll();
    }

    @Override
    public Optional<Product> searchById(long id) {
        return productElasticSearchRepository.findById(id);
    }
}
