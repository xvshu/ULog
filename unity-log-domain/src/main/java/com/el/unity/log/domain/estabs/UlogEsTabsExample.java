/*
 * Powered By [rapid-framework]
 * Web Site: http://www.eloancn.com
 * By:eloancn
 * Since 2015 - 2016
 */

package com.el.unity.log.domain.estabs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.el.common.web.model.BaseModel;

public class UlogEsTabsExample extends BaseModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UlogEsTabsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements java.io.Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

       
		
		
			//alias

	 public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "Id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "Id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "Id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "Id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "Id");
            return (Criteria) this;
        } 

	 public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("menu_id =", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("menu_id <>", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("menu_id >", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_id >=", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("menu_id <", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("menu_id <=", value, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("menu_id in", values, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("menu_id not in", values, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("menu_id between", value1, value2, "MenuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_id not between", value1, value2, "MenuId");
            return (Criteria) this;
        } 

	 public Criteria andTabNameIsNull() {
            addCriterion("tab_name is null");
            return (Criteria) this;
        }

        public Criteria andTabNameIsNotNull() {
            addCriterion("tab_name is not null");
            return (Criteria) this;
        }

        public Criteria andTabNameEqualTo(String value) {
            addCriterion("tab_name =", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameNotEqualTo(String value) {
            addCriterion("tab_name <>", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameGreaterThan(String value) {
            addCriterion("tab_name >", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameGreaterThanOrEqualTo(String value) {
            addCriterion("tab_name >=", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameLessThan(String value) {
            addCriterion("tab_name <", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameLessThanOrEqualTo(String value) {
            addCriterion("tab_name <=", value, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameIn(List<String> values) {
            addCriterion("tab_name in", values, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameNotIn(List<String> values) {
            addCriterion("tab_name not in", values, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameBetween(String value1, String value2) {
            addCriterion("tab_name between", value1, value2, "TabName");
            return (Criteria) this;
        }

        public Criteria andTabNameNotBetween(String value1, String value2) {
            addCriterion("tab_name not between", value1, value2, "TabName");
            return (Criteria) this;
        } 

	 public Criteria andEsIndexIsNull() {
            addCriterion("es_index is null");
            return (Criteria) this;
        }

        public Criteria andEsIndexIsNotNull() {
            addCriterion("es_index is not null");
            return (Criteria) this;
        }

        public Criteria andEsIndexEqualTo(String value) {
            addCriterion("es_index =", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexNotEqualTo(String value) {
            addCriterion("es_index <>", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexGreaterThan(String value) {
            addCriterion("es_index >", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexGreaterThanOrEqualTo(String value) {
            addCriterion("es_index >=", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexLessThan(String value) {
            addCriterion("es_index <", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexLessThanOrEqualTo(String value) {
            addCriterion("es_index <=", value, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexIn(List<String> values) {
            addCriterion("es_index in", values, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexNotIn(List<String> values) {
            addCriterion("es_index not in", values, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexBetween(String value1, String value2) {
            addCriterion("es_index between", value1, value2, "EsIndex");
            return (Criteria) this;
        }

        public Criteria andEsIndexNotBetween(String value1, String value2) {
            addCriterion("es_index not between", value1, value2, "EsIndex");
            return (Criteria) this;
        } 

	 public Criteria andEsTypeIsNull() {
            addCriterion("es_type is null");
            return (Criteria) this;
        }

        public Criteria andEsTypeIsNotNull() {
            addCriterion("es_type is not null");
            return (Criteria) this;
        }

        public Criteria andEsTypeEqualTo(String value) {
            addCriterion("es_type =", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeNotEqualTo(String value) {
            addCriterion("es_type <>", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeGreaterThan(String value) {
            addCriterion("es_type >", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("es_type >=", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeLessThan(String value) {
            addCriterion("es_type <", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeLessThanOrEqualTo(String value) {
            addCriterion("es_type <=", value, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeIn(List<String> values) {
            addCriterion("es_type in", values, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeNotIn(List<String> values) {
            addCriterion("es_type not in", values, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeBetween(String value1, String value2) {
            addCriterion("es_type between", value1, value2, "EsType");
            return (Criteria) this;
        }

        public Criteria andEsTypeNotBetween(String value1, String value2) {
            addCriterion("es_type not between", value1, value2, "EsType");
            return (Criteria) this;
        } 

	 public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(java.util.Date value) {
            addCriterion("created =", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(java.util.Date value) {
            addCriterion("created <>", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(java.util.Date value) {
            addCriterion("created >", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
            addCriterion("created >=", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(java.util.Date value) {
            addCriterion("created <", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(java.util.Date value) {
            addCriterion("created <=", value, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<java.util.Date> values) {
            addCriterion("created in", values, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<java.util.Date> values) {
            addCriterion("created not in", values, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
            addCriterion("created between", value1, value2, "Created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
            addCriterion("created not between", value1, value2, "Created");
            return (Criteria) this;
        } 

	 public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(java.util.Date value) {
            addCriterion("modified =", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(java.util.Date value) {
            addCriterion("modified <>", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(java.util.Date value) {
            addCriterion("modified >", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(java.util.Date value) {
            addCriterion("modified >=", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(java.util.Date value) {
            addCriterion("modified <", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(java.util.Date value) {
            addCriterion("modified <=", value, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<java.util.Date> values) {
            addCriterion("modified in", values, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<java.util.Date> values) {
            addCriterion("modified not in", values, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(java.util.Date value1, java.util.Date value2) {
            addCriterion("modified between", value1, value2, "Modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(java.util.Date value1, java.util.Date value2) {
            addCriterion("modified not between", value1, value2, "Modified");
            return (Criteria) this;
        } 

	 public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "Remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "Remark");
            return (Criteria) this;
        } 
		
       
    }

    public static class Criteria extends GeneratedCriteria implements java.io.Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements java.io.Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}