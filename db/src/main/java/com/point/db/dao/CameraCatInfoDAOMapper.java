package com.point.db.dao;

import com.point.db.model.CameraCatInfoDAO;
import com.point.db.model.CameraCatInfoDAOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraCatInfoDAOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    long countByExample(CameraCatInfoDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int deleteByExample(CameraCatInfoDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int insert(CameraCatInfoDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int insertSelective(CameraCatInfoDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    List<CameraCatInfoDAO> selectByExample(CameraCatInfoDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    CameraCatInfoDAO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CameraCatInfoDAO record, @Param("example") CameraCatInfoDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CameraCatInfoDAO record, @Param("example") CameraCatInfoDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CameraCatInfoDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CameraCatInfoDAO record);
}