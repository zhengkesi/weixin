package com.weixin.publics.service;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.LockObtainFailedException;

import java.io.IOException;

/**
 * @Author: xiaodong
 * @Date: 2020/12/14 14:44
 */
public interface SearcherNewService {
    public void index() throws CorruptIndexException,
            LockObtainFailedException, IOException;

    public Object search(String queryWord)
            throws Exception;
    public void loadFundInfo();
}
