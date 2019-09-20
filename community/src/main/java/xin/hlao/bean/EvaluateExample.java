package xin.hlao.bean;

import java.util.ArrayList;
import java.util.List;

public class EvaluateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EvaluateExample() {
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

    protected abstract static class GeneratedCriteria {
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

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(String value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(String value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(String value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(String value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(String value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(String value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLike(String value) {
            addCriterion("eid like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotLike(String value) {
            addCriterion("eid not like", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<String> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<String> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(String value1, String value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(String value1, String value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andAdmireIsNull() {
            addCriterion("admire is null");
            return (Criteria) this;
        }

        public Criteria andAdmireIsNotNull() {
            addCriterion("admire is not null");
            return (Criteria) this;
        }

        public Criteria andAdmireEqualTo(Integer value) {
            addCriterion("admire =", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireNotEqualTo(Integer value) {
            addCriterion("admire <>", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireGreaterThan(Integer value) {
            addCriterion("admire >", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireGreaterThanOrEqualTo(Integer value) {
            addCriterion("admire >=", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireLessThan(Integer value) {
            addCriterion("admire <", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireLessThanOrEqualTo(Integer value) {
            addCriterion("admire <=", value, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireIn(List<Integer> values) {
            addCriterion("admire in", values, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireNotIn(List<Integer> values) {
            addCriterion("admire not in", values, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireBetween(Integer value1, Integer value2) {
            addCriterion("admire between", value1, value2, "admire");
            return (Criteria) this;
        }

        public Criteria andAdmireNotBetween(Integer value1, Integer value2) {
            addCriterion("admire not between", value1, value2, "admire");
            return (Criteria) this;
        }

        public Criteria andTrampleIsNull() {
            addCriterion("trample is null");
            return (Criteria) this;
        }

        public Criteria andTrampleIsNotNull() {
            addCriterion("trample is not null");
            return (Criteria) this;
        }

        public Criteria andTrampleEqualTo(Integer value) {
            addCriterion("trample =", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleNotEqualTo(Integer value) {
            addCriterion("trample <>", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleGreaterThan(Integer value) {
            addCriterion("trample >", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleGreaterThanOrEqualTo(Integer value) {
            addCriterion("trample >=", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleLessThan(Integer value) {
            addCriterion("trample <", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleLessThanOrEqualTo(Integer value) {
            addCriterion("trample <=", value, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleIn(List<Integer> values) {
            addCriterion("trample in", values, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleNotIn(List<Integer> values) {
            addCriterion("trample not in", values, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleBetween(Integer value1, Integer value2) {
            addCriterion("trample between", value1, value2, "trample");
            return (Criteria) this;
        }

        public Criteria andTrampleNotBetween(Integer value1, Integer value2) {
            addCriterion("trample not between", value1, value2, "trample");
            return (Criteria) this;
        }

        public Criteria andUidsIsNull() {
            addCriterion("uids is null");
            return (Criteria) this;
        }

        public Criteria andUidsIsNotNull() {
            addCriterion("uids is not null");
            return (Criteria) this;
        }

        public Criteria andUidsEqualTo(String value) {
            addCriterion("uids =", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsNotEqualTo(String value) {
            addCriterion("uids <>", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsGreaterThan(String value) {
            addCriterion("uids >", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsGreaterThanOrEqualTo(String value) {
            addCriterion("uids >=", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsLessThan(String value) {
            addCriterion("uids <", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsLessThanOrEqualTo(String value) {
            addCriterion("uids <=", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsLike(String value) {
            addCriterion("uids like", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsNotLike(String value) {
            addCriterion("uids not like", value, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsIn(List<String> values) {
            addCriterion("uids in", values, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsNotIn(List<String> values) {
            addCriterion("uids not in", values, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsBetween(String value1, String value2) {
            addCriterion("uids between", value1, value2, "uids");
            return (Criteria) this;
        }

        public Criteria andUidsNotBetween(String value1, String value2) {
            addCriterion("uids not between", value1, value2, "uids");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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