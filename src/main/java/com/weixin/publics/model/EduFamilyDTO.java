package com.weixin.publics.model;



import java.util.List;

/**
 * @Author: jiang_zhiming
 * @Date: 2020/12/29 20:14
 */
public class EduFamilyDTO {

    private List<EduFamily> eduFamilies;

    private Integer isBatch;

    public List<EduFamily> getEduFamilies() {
        return eduFamilies;
    }

    public void setEduFamilies(List<EduFamily> eduFamilies) {
        this.eduFamilies = eduFamilies;
    }

    public Integer getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Integer isBatch) {
        this.isBatch = isBatch;
    }
}
