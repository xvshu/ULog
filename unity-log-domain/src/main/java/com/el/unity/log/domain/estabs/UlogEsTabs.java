/*
 * Powered By [rapid-framework]
 * Web Site: http://www.eloancn.com
 * By:eloancn
 * Since 2015 - 2016
 */

package com.el.unity.log.domain.estabs;

import java.io.Serializable;


public class UlogEsTabs implements Serializable {


	//alias
	private Integer id;  
	/**
	*菜单id
	*/
	private Integer menuId;  
	/**
	*tab名称
	*/
	private String tabName;  
	/**
	*es索引名称
	*/
	private String esIndex;  
	/**
	*es类型名称
	*/
	private String esType;  
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
	

    
    public void setId(Integer id) {  
        this.id = id;  
    }  
      
    public Integer getId() {  
        return this.id;  
    }  
    
    public void setMenuId(Integer menuId) {  
        this.menuId = menuId;  
    }  
      
    public Integer getMenuId() {  
        return this.menuId;  
    }  
    
    public void setTabName(String tabName) {  
        this.tabName = tabName;  
    }  
      
    public String getTabName() {  
        return this.tabName;  
    }  
    
    public void setEsIndex(String esIndex) {  
        this.esIndex = esIndex;  
    }  
      
    public String getEsIndex() {  
        return this.esIndex;  
    }  
    
    public void setEsType(String esType) {  
        this.esType = esType;  
    }  
      
    public String getEsType() {  
        return this.esType;  
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
	
	

}
