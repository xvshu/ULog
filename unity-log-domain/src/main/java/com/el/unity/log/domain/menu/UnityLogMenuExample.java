package com.el.unity.log.domain.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.el.common.web.model.BaseModel;

public class UnityLogMenuExample extends BaseModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UnityLogMenuExample() {
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

        public Criteria andMenIdIsNull() {
            addCriterion("men_id is null");
            return (Criteria) this;
        }

        public Criteria andMenIdIsNotNull() {
            addCriterion("men_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenIdEqualTo(Long value) {
            addCriterion("men_id =", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdNotEqualTo(Long value) {
            addCriterion("men_id <>", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdGreaterThan(Long value) {
            addCriterion("men_id >", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdGreaterThanOrEqualTo(Long value) {
            addCriterion("men_id >=", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdLessThan(Long value) {
            addCriterion("men_id <", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdLessThanOrEqualTo(Long value) {
            addCriterion("men_id <=", value, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdIn(List<Long> values) {
            addCriterion("men_id in", values, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdNotIn(List<Long> values) {
            addCriterion("men_id not in", values, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdBetween(Long value1, Long value2) {
            addCriterion("men_id between", value1, value2, "menId");
            return (Criteria) this;
        }

        public Criteria andMenIdNotBetween(Long value1, Long value2) {
            addCriterion("men_id not between", value1, value2, "menId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMenUrlIsNull() {
            addCriterion("men_url is null");
            return (Criteria) this;
        }

        public Criteria andMenUrlIsNotNull() {
            addCriterion("men_url is not null");
            return (Criteria) this;
        }

        public Criteria andMenUrlEqualTo(String value) {
            addCriterion("men_url =", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlNotEqualTo(String value) {
            addCriterion("men_url <>", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlGreaterThan(String value) {
            addCriterion("men_url >", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlGreaterThanOrEqualTo(String value) {
            addCriterion("men_url >=", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlLessThan(String value) {
            addCriterion("men_url <", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlLessThanOrEqualTo(String value) {
            addCriterion("men_url <=", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlLike(String value) {
            addCriterion("men_url like", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlNotLike(String value) {
            addCriterion("men_url not like", value, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlIn(List<String> values) {
            addCriterion("men_url in", values, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlNotIn(List<String> values) {
            addCriterion("men_url not in", values, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlBetween(String value1, String value2) {
            addCriterion("men_url between", value1, value2, "menUrl");
            return (Criteria) this;
        }

        public Criteria andMenUrlNotBetween(String value1, String value2) {
            addCriterion("men_url not between", value1, value2, "menUrl");
            return (Criteria) this;
        }

        public Criteria andParentMenIdIsNull() {
            addCriterion("parent_men_id is null");
            return (Criteria) this;
        }

        public Criteria andParentMenIdIsNotNull() {
            addCriterion("parent_men_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentMenIdEqualTo(Long value) {
            addCriterion("parent_men_id =", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdNotEqualTo(Long value) {
            addCriterion("parent_men_id <>", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdGreaterThan(Long value) {
            addCriterion("parent_men_id >", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_men_id >=", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdLessThan(Long value) {
            addCriterion("parent_men_id <", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_men_id <=", value, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdIn(List<Long> values) {
            addCriterion("parent_men_id in", values, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdNotIn(List<Long> values) {
            addCriterion("parent_men_id not in", values, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdBetween(Long value1, Long value2) {
            addCriterion("parent_men_id between", value1, value2, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andParentMenIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_men_id not between", value1, value2, "parentMenId");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
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

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
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

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
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
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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