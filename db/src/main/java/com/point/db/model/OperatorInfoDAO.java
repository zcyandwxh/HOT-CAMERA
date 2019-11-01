package com.point.db.model;

public class OperatorInfoDAO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operator_info.operator_id
     *
     * @mbg.generated
     */
    private Long operatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operator_info.group_id
     *
     * @mbg.generated
     */
    private Byte groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operator_info.operator_name
     *
     * @mbg.generated
     */
    private String operatorName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operator_info.operator_number
     *
     * @mbg.generated
     */
    private String operatorNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operator_info.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operator_info.operator_id
     *
     * @return the value of operator_info.operator_id
     *
     * @mbg.generated
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operator_info.operator_id
     *
     * @param operatorId the value for operator_info.operator_id
     *
     * @mbg.generated
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operator_info.group_id
     *
     * @return the value of operator_info.group_id
     *
     * @mbg.generated
     */
    public Byte getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operator_info.group_id
     *
     * @param groupId the value for operator_info.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Byte groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operator_info.operator_name
     *
     * @return the value of operator_info.operator_name
     *
     * @mbg.generated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operator_info.operator_name
     *
     * @param operatorName the value for operator_info.operator_name
     *
     * @mbg.generated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operator_info.operator_number
     *
     * @return the value of operator_info.operator_number
     *
     * @mbg.generated
     */
    public String getOperatorNumber() {
        return operatorNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operator_info.operator_number
     *
     * @param operatorNumber the value for operator_info.operator_number
     *
     * @mbg.generated
     */
    public void setOperatorNumber(String operatorNumber) {
        this.operatorNumber = operatorNumber == null ? null : operatorNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operator_info.description
     *
     * @return the value of operator_info.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operator_info.description
     *
     * @param description the value for operator_info.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operator_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorId=").append(operatorId);
        sb.append(", groupId=").append(groupId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", operatorNumber=").append(operatorNumber);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operator_info
     *
     * @mbg.generated
     */
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
        OperatorInfoDAO other = (OperatorInfoDAO) that;
        return (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getOperatorNumber() == null ? other.getOperatorNumber() == null : this.getOperatorNumber().equals(other.getOperatorNumber()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table operator_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getOperatorNumber() == null) ? 0 : getOperatorNumber().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}