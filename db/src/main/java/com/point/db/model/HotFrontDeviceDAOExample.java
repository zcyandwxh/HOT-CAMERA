package com.point.db.model;

import java.util.ArrayList;
import java.util.List;

public class HotFrontDeviceDAOExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public HotFrontDeviceDAOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
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

        public Criteria andDevIdIsNull() {
            addCriterion("dev_id is null");
            return (Criteria) this;
        }

        public Criteria andDevIdIsNotNull() {
            addCriterion("dev_id is not null");
            return (Criteria) this;
        }

        public Criteria andDevIdEqualTo(Long value) {
            addCriterion("dev_id =", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotEqualTo(Long value) {
            addCriterion("dev_id <>", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThan(Long value) {
            addCriterion("dev_id >", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dev_id >=", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdLessThan(Long value) {
            addCriterion("dev_id <", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdLessThanOrEqualTo(Long value) {
            addCriterion("dev_id <=", value, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdIn(List<Long> values) {
            addCriterion("dev_id in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotIn(List<Long> values) {
            addCriterion("dev_id not in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdBetween(Long value1, Long value2) {
            addCriterion("dev_id between", value1, value2, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotBetween(Long value1, Long value2) {
            addCriterion("dev_id not between", value1, value2, "devId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andDevNameIsNull() {
            addCriterion("dev_name is null");
            return (Criteria) this;
        }

        public Criteria andDevNameIsNotNull() {
            addCriterion("dev_name is not null");
            return (Criteria) this;
        }

        public Criteria andDevNameEqualTo(String value) {
            addCriterion("dev_name =", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotEqualTo(String value) {
            addCriterion("dev_name <>", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameGreaterThan(String value) {
            addCriterion("dev_name >", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameGreaterThanOrEqualTo(String value) {
            addCriterion("dev_name >=", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLessThan(String value) {
            addCriterion("dev_name <", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLessThanOrEqualTo(String value) {
            addCriterion("dev_name <=", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameLike(String value) {
            addCriterion("dev_name like", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotLike(String value) {
            addCriterion("dev_name not like", value, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameIn(List<String> values) {
            addCriterion("dev_name in", values, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotIn(List<String> values) {
            addCriterion("dev_name not in", values, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameBetween(String value1, String value2) {
            addCriterion("dev_name between", value1, value2, "devName");
            return (Criteria) this;
        }

        public Criteria andDevNameNotBetween(String value1, String value2) {
            addCriterion("dev_name not between", value1, value2, "devName");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNull() {
            addCriterion("dev_type is null");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNotNull() {
            addCriterion("dev_type is not null");
            return (Criteria) this;
        }

        public Criteria andDevTypeEqualTo(Byte value) {
            addCriterion("dev_type =", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotEqualTo(Byte value) {
            addCriterion("dev_type <>", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThan(Byte value) {
            addCriterion("dev_type >", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("dev_type >=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThan(Byte value) {
            addCriterion("dev_type <", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThanOrEqualTo(Byte value) {
            addCriterion("dev_type <=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeIn(List<Byte> values) {
            addCriterion("dev_type in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotIn(List<Byte> values) {
            addCriterion("dev_type not in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeBetween(Byte value1, Byte value2) {
            addCriterion("dev_type between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("dev_type not between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andDevNumIsNull() {
            addCriterion("dev_num is null");
            return (Criteria) this;
        }

        public Criteria andDevNumIsNotNull() {
            addCriterion("dev_num is not null");
            return (Criteria) this;
        }

        public Criteria andDevNumEqualTo(String value) {
            addCriterion("dev_num =", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumNotEqualTo(String value) {
            addCriterion("dev_num <>", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumGreaterThan(String value) {
            addCriterion("dev_num >", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumGreaterThanOrEqualTo(String value) {
            addCriterion("dev_num >=", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumLessThan(String value) {
            addCriterion("dev_num <", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumLessThanOrEqualTo(String value) {
            addCriterion("dev_num <=", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumLike(String value) {
            addCriterion("dev_num like", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumNotLike(String value) {
            addCriterion("dev_num not like", value, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumIn(List<String> values) {
            addCriterion("dev_num in", values, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumNotIn(List<String> values) {
            addCriterion("dev_num not in", values, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumBetween(String value1, String value2) {
            addCriterion("dev_num between", value1, value2, "devNum");
            return (Criteria) this;
        }

        public Criteria andDevNumNotBetween(String value1, String value2) {
            addCriterion("dev_num not between", value1, value2, "devNum");
            return (Criteria) this;
        }

        public Criteria andIpAddrIsNull() {
            addCriterion("ip_addr is null");
            return (Criteria) this;
        }

        public Criteria andIpAddrIsNotNull() {
            addCriterion("ip_addr is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddrEqualTo(String value) {
            addCriterion("ip_addr =", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotEqualTo(String value) {
            addCriterion("ip_addr <>", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrGreaterThan(String value) {
            addCriterion("ip_addr >", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrGreaterThanOrEqualTo(String value) {
            addCriterion("ip_addr >=", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLessThan(String value) {
            addCriterion("ip_addr <", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLessThanOrEqualTo(String value) {
            addCriterion("ip_addr <=", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLike(String value) {
            addCriterion("ip_addr like", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotLike(String value) {
            addCriterion("ip_addr not like", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrIn(List<String> values) {
            addCriterion("ip_addr in", values, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotIn(List<String> values) {
            addCriterion("ip_addr not in", values, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrBetween(String value1, String value2) {
            addCriterion("ip_addr between", value1, value2, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotBetween(String value1, String value2) {
            addCriterion("ip_addr not between", value1, value2, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrIsNull() {
            addCriterion("ipv6_addr is null");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrIsNotNull() {
            addCriterion("ipv6_addr is not null");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrEqualTo(String value) {
            addCriterion("ipv6_addr =", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrNotEqualTo(String value) {
            addCriterion("ipv6_addr <>", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrGreaterThan(String value) {
            addCriterion("ipv6_addr >", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrGreaterThanOrEqualTo(String value) {
            addCriterion("ipv6_addr >=", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrLessThan(String value) {
            addCriterion("ipv6_addr <", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrLessThanOrEqualTo(String value) {
            addCriterion("ipv6_addr <=", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrLike(String value) {
            addCriterion("ipv6_addr like", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrNotLike(String value) {
            addCriterion("ipv6_addr not like", value, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrIn(List<String> values) {
            addCriterion("ipv6_addr in", values, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrNotIn(List<String> values) {
            addCriterion("ipv6_addr not in", values, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrBetween(String value1, String value2) {
            addCriterion("ipv6_addr between", value1, value2, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andIpv6AddrNotBetween(String value1, String value2) {
            addCriterion("ipv6_addr not between", value1, value2, "ipv6Addr");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(String value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(String value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(String value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(String value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(String value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(String value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLike(String value) {
            addCriterion("lng like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotLike(String value) {
            addCriterion("lng not like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<String> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<String> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(String value1, String value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(String value1, String value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNull() {
            addCriterion("place is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNotNull() {
            addCriterion("place is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceEqualTo(String value) {
            addCriterion("place =", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotEqualTo(String value) {
            addCriterion("place <>", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThan(String value) {
            addCriterion("place >", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("place >=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThan(String value) {
            addCriterion("place <", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThanOrEqualTo(String value) {
            addCriterion("place <=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLike(String value) {
            addCriterion("place like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotLike(String value) {
            addCriterion("place not like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceIn(List<String> values) {
            addCriterion("place in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotIn(List<String> values) {
            addCriterion("place not in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceBetween(String value1, String value2) {
            addCriterion("place between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotBetween(String value1, String value2) {
            addCriterion("place not between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_front_device
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_front_device
     *
     * @mbg.generated
     */
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