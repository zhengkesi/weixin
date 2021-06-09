package com.weixin.publics.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 阅读活动小程序用户表对象 t_edu_family
 *
 * @author zhengkesi
 * @date 2020-12-16
 */
public class EduFamily implements Serializable{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;
    /** 微信openid */
    private String openId;
    /** 微信unionid */
    private String unionId;
    /** 是否是默认选择；1：是；0：不是 */
    private Integer isDefault;
    /** 手机号 */
    private String phone;
    /** 学生姓名 */
    private String stuName;
    /** 学生身份证 */
    private String idCard;
    /** 学生userid */
    private String stuId;
    /** 学校id */
    private Long schId;

    private String XXMC;

    private String gradeName;

    private String BJ;

    private Integer isAudit;

    private Date createTime;

    private Long xqId;

    private Long gradeId;

    private Long classId;

    private String encryptPhone;

    @Transient
    private String header;

    @Transient
    private String city;

    @Transient
    private String xqName;

    @Transient
    private String proCode;

    @Transient
    private String cityCode;

    @Transient
    private String disCode;

    @Transient
    private String proName;

    @Transient
    private String cityName;

    @Transient
    private String disName;

    @Transient
    private String NJ;

    private String loginId;

    private Integer continuousLoginDays;

    private Date lastLoginDate;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUnionId() {
        return unionId;
    }
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() {
        return isDefault;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuName() {
        return stuName;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuId() {
        return stuId;
    }
    public void setSchId(Long schId) {
        this.schId = schId;
    }

    public Long getSchId() {
        return schId;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public String getXXMC() {
        return XXMC;
    }

    public void setXXMC(String XXMC) {
        this.XXMC = XXMC;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getBJ() {
        return BJ;
    }

    public void setBJ(String BJ) {
        this.BJ = BJ;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getXqId() {
        return xqId;
    }

    public void setXqId(Long xqId) {
        this.xqId = xqId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getXqName() {
        return xqName;
    }

    public void setXqName(String xqName) {
        this.xqName = xqName;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getNJ() {
        return NJ;
    }

    public void setNJ(String NJ) {
        this.NJ = NJ;
    }

    public String getEncryptPhone() {
        return encryptPhone;
    }

    public void setEncryptPhone(String encryptPhone) {
        this.encryptPhone = encryptPhone;
    }

    public Integer getContinuousLoginDays() {
        return continuousLoginDays;
    }

    public void setContinuousLoginDays(Integer continuousLoginDays) {
        this.continuousLoginDays = continuousLoginDays;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("unionId", getUnionId())
            .append("isDefault", getIsDefault())
            .append("phone", getPhone())
            .append("stuName", getStuName())
            .append("idCard", getIdCard())
            .append("stuId", getStuId())
            .append("schId", getSchId())
            .toString();
    }
}
