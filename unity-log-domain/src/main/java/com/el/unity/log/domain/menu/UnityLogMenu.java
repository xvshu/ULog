package com.el.unity.log.domain.menu;

public class UnityLogMenu implements java.io.Serializable {
    //alias
    private Long menId;
    /**
     *菜单名称
     */
    private String name;
    /**
     *菜单url
     */
    private String menUrl;
    /**
     *父菜单id
     */
    private Long parentMenId;
    /**
     *域名
     */
    private String domainName;
    /**
     *查询通过log4j发的日志
     */
    private String esAppenderUrl;
    /**
     *查询通过文件记录的日志
     */
    private String esFileUrl;
    /**
     *扩展：图片时间统计url
     */
    private String imgTimeUrl;
    /**
     *扩展：图片性能统计url
     */
    private String imgPerformsUrl;
    /**
     *扩展：统计url
     */
    private String extendUrl;
    /**
     *创建时间
     */
    private java.util.Date created;
    /**
     *修改时间
     */
    private java.util.Date modified;
    /**
     *备注
     */
    private String remark;
    /**
     *权限码
     */
    private String authCode;



    public void setMenId(Long menId) {
        this.menId = menId;
    }

    public Long getMenId() {
        return this.menId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMenUrl(String menUrl) {
        this.menUrl = menUrl;
    }

    public String getMenUrl() {
        return this.menUrl;
    }

    public void setParentMenId(Long parentMenId) {
        this.parentMenId = parentMenId;
    }

    public Long getParentMenId() {
        return this.parentMenId;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setEsAppenderUrl(String esAppenderUrl) {
        this.esAppenderUrl = esAppenderUrl;
    }

    public String getEsAppenderUrl() {
        return this.esAppenderUrl;
    }

    public void setEsFileUrl(String esFileUrl) {
        this.esFileUrl = esFileUrl;
    }

    public String getEsFileUrl() {
        return this.esFileUrl;
    }

    public void setImgTimeUrl(String imgTimeUrl) {
        this.imgTimeUrl = imgTimeUrl;
    }

    public String getImgTimeUrl() {
        return this.imgTimeUrl;
    }

    public void setImgPerformsUrl(String imgPerformsUrl) {
        this.imgPerformsUrl = imgPerformsUrl;
    }

    public String getImgPerformsUrl() {
        return this.imgPerformsUrl;
    }

    public void setExtendUrl(String extendUrl) {
        this.extendUrl = extendUrl;
    }

    public String getExtendUrl() {
        return this.extendUrl;
    }

    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    public java.util.Date getCreated() {
        return this.created;
    }

    public void setModified(java.util.Date modified) {
        this.modified = modified;
    }

    public java.util.Date getModified() {
        return this.modified;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UnityLogMenu other = (UnityLogMenu) that;
        return (this.getMenId() == null ? other.getMenId() == null : this.getMenId().equals(other.getMenId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMenUrl() == null ? other.getMenUrl() == null : this.getMenUrl().equals(other.getMenUrl()))
            && (this.getParentMenId() == null ? other.getParentMenId() == null : this.getParentMenId().equals(other.getParentMenId()))
            && (this.getDomainName() == null ? other.getDomainName() == null : this.getDomainName().equals(other.getDomainName()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getModified() == null ? other.getModified() == null : this.getModified().equals(other.getModified()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenId() == null) ? 0 : getMenId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMenUrl() == null) ? 0 : getMenUrl().hashCode());
        result = prime * result + ((getParentMenId() == null) ? 0 : getParentMenId().hashCode());
        result = prime * result + ((getDomainName() == null) ? 0 : getDomainName().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getModified() == null) ? 0 : getModified().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}