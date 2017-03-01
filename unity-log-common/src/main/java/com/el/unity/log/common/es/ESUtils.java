package com.el.unity.log.common.es;

import com.el.unity.log.common.utils.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * ES工具类
 */

public class ESUtils {

    protected final static Logger log = LogManager.getLogger(ESUtils.class.getName());
    private static boolean  esClientTransportSniff;  //探测集群中机器状态
    private static String esClusterName; //指定集群名称
    private static String esIps;   //集群访问ip

    private static TransportClient client;
    public static HashSet<String> IndexSet;
    private static long reflashIndexNamesTime=System.currentTimeMillis();
    private static long reflashIndexNamesSpeed = 1*60*1000l;


    public synchronized static  void getClient() {


        log.info("=getClient=>");
        if (client == null) {
            log.info("=getClient=> ips:" + esIps + " cluster:" + esClusterName);
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", esClusterName) //指定集群名称
                    .put("client.transport.sniff", esClientTransportSniff) //探测集群中机器状态
                    .put("ignore_unavailable",true)
                    .build();
            client = new TransportClient(settings);
            String[] ips = esIps.split(",");
            for(String oneip :ips){
                String[] oneIpPoint = oneip.split(":");
                log.info("=getClient=> one es ip:"+oneIpPoint[0]+"  point:"+oneIpPoint[1]);
                client = client.addTransportAddress(new InetSocketTransportAddress(oneIpPoint[0], Integer.valueOf(oneIpPoint[1])));
            }

        }
        if(IndexSet == null||IndexSet.isEmpty()){
            reflashIndexNames();
            reflashIndexNamesTime = System.currentTimeMillis();
        }
        if((System.currentTimeMillis()-reflashIndexNamesTime)>reflashIndexNamesSpeed){
            reflashIndexNames();
            reflashIndexNamesTime = System.currentTimeMillis();
        }
        log.info("<=getClient=>");
        //return client;
    }



    /**
     * 关闭ES客户端
     */
    public synchronized void closeClient(){
        log.info("=closeClient=>");
        client.close();
        log.info("<=closeClient=>");
    }

    /**
     * 刷新客户端
     */
    public synchronized void reflashClient(){
        log.info("=reflashClient=>");
        closeClient();
        client = null;
        getClient();
        log.info("<=reflashClient=>");
    }

    public synchronized static void reflashIndexNames(){
        if(IndexSet == null){
            IndexSet = new HashSet<String> ();
        }
        ImmutableOpenMap<String, IndexMetaData> indexMap =  client.admin().cluster().prepareState().execute().actionGet().getState().getMetaData().getIndices();
        Iterator<ObjectObjectCursor<String, IndexMetaData>> iterator = indexMap.iterator();
        while (iterator.hasNext()){
            ObjectObjectCursor<String, IndexMetaData> ooc = iterator.next();
            IndexSet.add(ooc.key);
        }
    }

    /**
     * 查询数据
     * @param esSerach 查询内容，封在map中
     * @return 返回查询结果：json格式
     * @throws Exception
     */
    public  String getData(ESSerach esSerach){
        log.info("=getData=>");
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        SortOrder sortOrder = SortOrder.DESC;
        long begaintime=0;
        long endtime=0;
        //循环取出要查询的字段名称与字段值
        for(Map.Entry<String, String> entry:esSerach.getSerachWord().entrySet()){
            if(entry.getValue()!=null&& entry.getValue().trim().length()>0){
                //匹配查询
                if(entry.getKey().equals("created_begain")){

                    String createdBegain=entry.getValue();
                    String createdEnd =esSerach.getSerachWord().get("created_end");

                    if(createdBegain.length()>10){
                        begaintime = DateFormatUtils.parseDate(createdBegain, "yyyy-MM-dd HH:mm:ss").getTime();
                        endtime = DateFormatUtils.parseDate(createdEnd, "yyyy-MM-dd HH:mm:ss").getTime();
                    }else{
                        begaintime = DateFormatUtils.parseDate(createdBegain, "yyyy-MM-dd").getTime();
                        //es查询日期段，截止日期要延后一天，负责无法查询出正确结果
                        endtime = DateFormatUtils.parseDate(createdEnd, "yyyy-MM-dd").getTime()
                                + 1 * 24 * 60 * 60 * 1000;
                    }


                    query.must(QueryBuilders.rangeQuery("created")
                            .from(begaintime)
                            .to(endtime)
                            .includeLower(true).includeUpper(false));
                }else if(entry.getKey().equals("created_begain_more_info")){
                    begaintime =Long.valueOf(entry.getValue());

                    if(esSerach.getSerachWord().get("created_begain_filter").equals("gte")){
                        query.must(QueryBuilders.rangeQuery("created")
                                .gte(begaintime)
                                .includeLower(true).includeUpper(false));
                        sortOrder= SortOrder.ASC;
                    }else{
                        query.must(QueryBuilders.rangeQuery("created")
                                .lte(begaintime)
                                .includeLower(true).includeUpper(false));
                    }

                }else if(entry.getKey().equals("created_begain_filter")) {
                    continue;
                }else if(entry.getKey().equals("created_end")) {
                    continue;
                }else if(entry.getKey().equals("SearchType")){
                    continue;
                }else if(entry.getKey().equals("HightLight")){
                    continue;
                }else{

                    //这是模糊匹配，允许使用通配符：?用来匹配任意字符，*用来匹配零个或者多个字符
                    //query.must(QueryBuilders.wildcardQuery(entry.getKey(), '*' + entry.getValue() + '*'));
                    if(esSerach.getSerachWord().get("SearchType").equals("and")){
                        query.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue().trim()).operator(MatchQueryBuilder.Operator.AND));
                    }else{
                        query.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue().trim()));
                    }

                }
            }
        }


        log.info("<=getData=>");
        return executeQuery(esSerach.getIndex(), esSerach.getType(), query, esSerach.getFrom(), esSerach.getSize(), esSerach.getSerachWord().get("HightLight"), esSerach.getOrderFile(),sortOrder,esSerach.getHasAggregation(),begaintime,endtime);
    }


    /**
     * 与es服务器交互，获取数据
     * @param index 索引
     * @param type 类型
     * @param query 查询实体
     * @param from 分页：起始页
     * @param size 分页：页大小
     * @return 返回查询结果：json格式
     * @throws Exception
     */
    private  String executeQuery(String index, String type, QueryBuilder query, int from , int size, String isHighlighte, String sortFile, SortOrder sortOrder, Boolean hasAggregation,long begaintime,long endtime){
        log.info("=executeQuery=>");
        long starttime = System.currentTimeMillis();
        getClient();
        System.out.println("=======executeQuery.getClient=======>spen time:"+(System.currentTimeMillis()-starttime));
        SearchResponse response=null;
        SearchRequestBuilder searchRequestBuilder=null;
        try {
            searchRequestBuilder = client
                    .prepareSearch(index == null || index.length() == 0 ? null : ElasticIndexBuilder.getIndexNames(index,begaintime,endtime));
        }catch(Exception e){
            log.error("=executeQuery=>",e);
            reflashClient();
            searchRequestBuilder = client
                    .prepareSearch(index == null || index.length() == 0 ? null : index.split("\\,"));
        }
        System.out.println("=======executeQuery.client.prepareSearch=======>spen time:"+(System.currentTimeMillis()-starttime));
        if( type!=null&&type.length()>0 ){
            String[] types = type.trim().split("\\|");
            searchRequestBuilder.setTypes(types); //类型
        }
        if( size>0 ){
            searchRequestBuilder.setFrom(from).setSize(size); //分页
        }
        if(isHighlighte.equals("yes")){
            searchRequestBuilder
                    .setHighlighterPreTags("<font color=\"red\" >") //高亮显示
                    .setHighlighterPostTags("</font>")
                    .addHighlightedField("context");
        }
        searchRequestBuilder.addSort(sortFile,sortOrder);
        //判断是否含有聚合信息
        if(hasAggregation){
            searchRequestBuilder.addAggregation(AggregationBuilders.terms("classNames").field("className").subAggregation(AggregationBuilders.terms("methodNames").field("methodName")).subAggregation(AggregationBuilders.count("countExcetion").field("spendTime")));
        }
        try{
            long starttimeex = System.currentTimeMillis();
            response=searchRequestBuilder
                    .setQuery(query) //设置查询条件
                    .execute().actionGet();
            System.out.println("=======executeQuery.actionGet=======>spen time:"+(System.currentTimeMillis()-starttimeex));
            starttimeex = System.currentTimeMillis();
            log.info("=executeQuery=>index:["+index == null || index.length() == 0 ? null : ElasticIndexBuilder.getIndexNames(index,begaintime,endtime)+"] type:["+type+"] query string is"+searchRequestBuilder.toString());
            System.out.println("=======executeQuery.searchRequestBuilder.toString()=======>spen time:"+(System.currentTimeMillis()-starttimeex));
        }catch (Exception e){
            reflashClient();
            log.error("=executeQuery=>error:",e);
        }
        System.out.println("=======executeQuery.execute()=======>spen time:"+(System.currentTimeMillis()-starttime));
        long starttimeex = System.currentTimeMillis();
        String queryResult = null==response?"":response.toString();
        System.out.println("=======executeQuery.response.toString()=======>spen time:"+(System.currentTimeMillis()-starttimeex));
        log.info("<=executeQuery=>");
        return queryResult;
    }



    /**
     * 删除数据
     * @param esSerach 查询内容，封在map中
     * @return 返回查询结果：json格式
     * @throws Exception
     */
    public  String deleteData(ESSerach esSerach){
        log.info("=getData=>");
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        //循环取出要查询的字段名称与字段值
        for(Map.Entry<String, String> entry:esSerach.getSerachWord().entrySet()){
            if(entry.getValue()!=null&& entry.getValue().trim().length()>0){
                if(entry.getKey().equals("created_begain_more_info")){
                    long begaintime =Long.valueOf(entry.getValue());

                    if(esSerach.getSerachWord().get("created_begain_filter").equals("gte")){
                        query.must(QueryBuilders.rangeQuery("created")
                                .gte(begaintime)
                                .includeLower(true).includeUpper(false));

                    }else{
                        query.must(QueryBuilders.rangeQuery("created")
                                .lte(begaintime)
                                .includeLower(true).includeUpper(false));
                    }

                }
            }
        }


        log.info("<=getData=>");
        return executeDeleteQuery(esSerach.getIndex(), esSerach.getType(), query);
    }


    /**
     * 与es服务器交互，获取数据
     * @param index 索引
     * @param type 类型
     * @param query 查询实体
     * @return 返回查询结果：json格式
     * @throws Exception
     */
    private  String executeDeleteQuery(String index,String type,QueryBuilder query){
        log.info("=executeQuery=>");
        log.info("=executeQuery=> query string is"+query.toString());
        getClient();
        DeleteByQueryResponse response=null;
        DeleteByQueryRequestBuilder deleteRequestBuilder=null;
        try {
            deleteRequestBuilder = client
                    .prepareDeleteByQuery(index == null || index.length() == 0 ? "" : index);
        }catch(Exception e){
            log.error("=executeQuery=>",e);
            reflashClient();
            deleteRequestBuilder = client
                    .prepareDeleteByQuery(index == null || index.length() == 0 ? "" : index);
        }
        if( type!=null&&type.length()>0 ){
            String[] types = type.trim().split("\\|");
            deleteRequestBuilder.setTypes(type);
        }

        try{
            response=deleteRequestBuilder
                    .setQuery(query)
                    .execute().actionGet();
        }catch (Exception e){
            log.error("=executeQuery=>error:",e);
        }finally {
            //closeClient(client);
        }

        String queryResult = null==response?"":response.toString();
        log.info("<=executeQuery=>");
        return queryResult;
    }

    public static void main(String[] args){
        String  esips = "10.1.112.82:9301,10.1.112.82:9311,10.1.112.83:9301,10.1.112.83:9311,10.1.112.84:9301,10.1.112.84:9311";
        String[] ips = esips.split(",");
        for(String oneip :ips){
            String[] oneIpPoint = oneip.split(":");
            System.out.println("ip:"+oneIpPoint[0]+"  point:"+Integer.valueOf(oneIpPoint[1]));
        }

    }



    public boolean isEsClientTransportSniff() {
        return esClientTransportSniff;
    }

    public void setEsClientTransportSniff(boolean esClientTransportSniff) {
        this.esClientTransportSniff = esClientTransportSniff;
    }

    public String getEsClusterName() {
        return esClusterName;
    }

    public void setEsClusterName(String esClusterName) {
        this.esClusterName = esClusterName;
    }

    public String getEsIps() {
        return esIps;
    }

    public void setEsIps(String esIps) {
        this.esIps = esIps;
    }
}
