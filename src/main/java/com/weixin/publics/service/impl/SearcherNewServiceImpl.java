package com.weixin.publics.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.mapper.AppletNewsDao;
import com.weixin.publics.service.SearcherNewService;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.weixin.publics.utils.ObjectUtil;
import com.weixin.publics.utils.PinyinUtils;
import com.weixin.publics.utils.SearchTokenizer;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author: xiaodong
 * @Date: 2020/12/14 14:45
 */
@Service
public class SearcherNewServiceImpl implements SearcherNewService {
    private static final Logger LOGGER = Logger.getLogger("search");

    @Autowired
    private AppletNewsDao appletNewsDao;

    private static final String [] QUERY_FIELD = { "XXMC" , "pinyin" , "pinyinHead", "id", "update_date", "show_date", "sticky_status", "sticky_text", "country_name", "label_name"}; // 需要参与模糊搜索的字段和最后需要显示的字段 如本次需求需要模糊搜索的字段为name、pinyin、pinyinHead 剩余字段不参与模糊搜索，仅为需要返回给前端显示的字段

    private static IndexSearcher indexSearcher = null;

    private static IndexReader reader = null;

    private static final String REGEX_NO = "^[0-9]\\w*$";

    private static final String REGEX_CHAR = "^[a-zA-Z]*";

    private static final int RESULT_COUNT = 100000;

    private static Directory ramdDrectory = new RAMDirectory();

    private final Lock writerLock = new ReentrantLock();

    private volatile IndexWriter writer = null;

    private Analyzer analyzer = new Analyzer(){
        @Override
        public TokenStream tokenStream(
                String fileName,
                Reader reader) {
            return new SearchTokenizer(reader);
        }
    };

    public IndexWriter getIndexWriter(Directory dir, IndexWriterConfig config) {
        if (null == dir) {
            throw new IllegalArgumentException("Directory can not be null.");
        }
        if (null == config) {
            throw new IllegalArgumentException("IndexWriterConfig can not be null.");
        }
        try {
            if (null == writer) {
                if (IndexWriter.isLocked(dir)) {
                    //throw new LockObtainFailedException("Directory of index had been locked.");
                    IndexWriter.unlock(dir);
                }
                writer = new IndexWriter(dir, config);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return writer;
    }

    @Override
    public void index() throws CorruptIndexException,
            LockObtainFailedException, IOException {
        LOGGER.info(" init search method index() ");
        List<Map<String, Object>> list = loadResources();
        if (list == null || list.isEmpty()) return ;

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
        try {
            writerLock.lock();
            getIndexWriter(ramdDrectory, config);
            writer.deleteAll();

            Document doc = null;
            String pinyin = null;
            String pinyinHead = null;
            for (Map<String, Object> appleNews : list) {
                //根据name生成对应的全拼
                pinyin = PinyinUtils.getChineseByPinYin(appleNews.get("XXMC").toString()).toLowerCase();
                //根据name生成对应的拼音首字母
                pinyinHead = PinyinUtils.getPinYinHeadChar(appleNews.get("XXMC").toString()).toLowerCase();
                //为每个字段赋值，根据自己需求展示对应字段 与上面数组对应即可, Field.Store和Field.Index具体的含义见下面解释
                doc = new Document();
                doc.add(new Field(QUERY_FIELD[0], appleNews.get("XXMC").toString(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field(QUERY_FIELD[1], pinyin, Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(QUERY_FIELD[2], pinyinHead, Field.Store.YES, Field.Index.NOT_ANALYZED));
                /*doc.add(new Field(QUERY_FIELD[3], String.valueOf(appleNews.get("id")), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(QUERY_FIELD[4], appleNews.get("updateDate").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(QUERY_FIELD[5], appleNews.get("showDate").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field(QUERY_FIELD[6], appleNews.get("stickyStatus").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                if(!ObjectUtil.isEmpty(appleNews, "stickyText")){
                    doc.add(new Field(QUERY_FIELD[7], appleNews.get("stickyText").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                }else{
                    doc.add(new Field(QUERY_FIELD[7], "", Field.Store.YES, Field.Index.NOT_ANALYZED));
                }
                if(!ObjectUtil.isEmpty(appleNews, "countryName")){
                    doc.add(new Field(QUERY_FIELD[8], appleNews.get("countryName").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                }else{
                    doc.add(new Field(QUERY_FIELD[8], "", Field.Store.YES, Field.Index.NOT_ANALYZED));
                }
                if(!ObjectUtil.isEmpty(appleNews, "labelName")){
                    doc.add(new Field(QUERY_FIELD[9], appleNews.get("labelName").toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                }else{
                    doc.add(new Field(QUERY_FIELD[9], "", Field.Store.YES, Field.Index.NOT_ANALYZED));
                }*/
                writer.addDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
            writer = null;
            writerLock.unlock();
        }
    }

    @Override
    public Object search(String queryWord)
            throws Exception {
        JSONArray appletNewsList = new JSONArray();
        indexSearcher = getIndexSearcher(reader);
        if (indexSearcher == null) {
            return appletNewsList;
        }
        Query query = null;
        PhraseQuery phrase = null;
        PrefixQuery prefix = null;
        BooleanQuery blquery = null;
        QueryParser parser = null;
        MultiFieldQueryParser multiParser = null;
        TermQuery term = null;
        String[] multiQueryField = {QUERY_FIELD[0]};
        if (queryWord.matches(REGEX_NO)) {
            queryWord = queryWord.toLowerCase();
            // code搜索
            phrase = new PhraseQuery();
            phrase.setSlop(0);
            for (int i = 0; i < queryWord.length(); i++) {
                phrase.add(new Term(QUERY_FIELD[2], Character.toString(queryWord.charAt(i))));
            }
            query = phrase;
        } else if (queryWord.matches(REGEX_CHAR)) {
            // 拼音搜索
            prefix = new PrefixQuery(new Term(QUERY_FIELD[1], queryWord.toLowerCase()));
            query = new WildcardQuery(new Term(QUERY_FIELD[2], queryWord.toLowerCase() + "*"));
            term = new TermQuery(new Term(QUERY_FIELD[0], queryWord.toLowerCase()));
            blquery = new BooleanQuery();
            blquery.add(prefix, Occur.SHOULD);
            blquery.add(query, Occur.SHOULD);
            blquery.add(term, Occur.SHOULD);
            query = blquery;
        } else {
            multiParser = new MultiFieldQueryParser(Version.LUCENE_36, multiQueryField, analyzer);
            parser = multiParser;
            parser.setDefaultOperator(QueryParser.Operator.AND);
            query = parser.parse(QueryParser.escape(queryWord));
        }
        LOGGER.info("query param is : " + query.toString());
        // start time
        TopScoreDocCollector collector = TopScoreDocCollector.create(RESULT_COUNT, false);
        long start = new Date().getTime();
        indexSearcher.search(query, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        JSONObject appletNews = null;
        for (ScoreDoc scoreDoc : hits) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            appletNews = new JSONObject();
            appletNews.put(QUERY_FIELD[0], doc.get(QUERY_FIELD[0]));
            /*appletNews.put(QUERY_FIELD[1], doc.get(QUERY_FIELD[1]));
            appletNews.put(QUERY_FIELD[2], doc.get(QUERY_FIELD[2]));
            appletNews.put(QUERY_FIELD[3], doc.get(QUERY_FIELD[3]));
            appletNews.put(QUERY_FIELD[4], doc.get(QUERY_FIELD[4]));
            appletNews.put(QUERY_FIELD[5], doc.get(QUERY_FIELD[5]));
            appletNews.put(QUERY_FIELD[6], doc.get(QUERY_FIELD[6]));
            appletNews.put(QUERY_FIELD[7], doc.get(QUERY_FIELD[7]));
            appletNews.put(QUERY_FIELD[8], doc.get(QUERY_FIELD[8]));
            appletNews.put(QUERY_FIELD[9], doc.get(QUERY_FIELD[9]));*/
            appletNewsList.add(appletNews);
        }
        // end time
        long end = new Date().getTime();
        LOGGER.info(
                "\nFound " + collector.getTotalHits() + " document(s) (in "
                        + (end - start) + " millindexSearchereconds) that matched query '"
                        + queryWord + "':"
        );
        return appletNewsList;
    }

    /**
     * 获取索引
     * @param reader
     * @return
     */
    private IndexSearcher getIndexSearcher(
            IndexReader reader){
        try {
            if (reader == null) {
                reader = IndexReader.open(ramdDrectory);
            } else {
                //如果当前reader在打开期间index发生改变,则打开并返回一个新的IndexReader,否则返回null
                IndexReader ir = IndexReader.openIfChanged(reader);
                if (ir != null) {
                    reader.close();
                    reader = ir;
                }
            }
            return new IndexSearcher(reader);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null; //发生异常则返回null
    }

    @Override
    public void loadFundInfo() {}

    public List<Map<String, Object>> loadResources() {
        List<Map<String, Object>> fundInfoList = appletNewsDao.newSelectAll();
        return fundInfoList;
    }

}
