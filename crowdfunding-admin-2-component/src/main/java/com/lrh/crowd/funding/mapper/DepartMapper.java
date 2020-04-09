package com.lrh.crowd.funding.mapper;

import com.lrh.crowd.funding.entity.Depart;
import com.lrh.crowd.funding.entity.DepartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartMapper {
    long countByExample(DepartExample example);

    int deleteByExample(DepartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Depart record);

    int insertSelective(Depart record);

    List<Depart> selectByExample(DepartExample example);

    Depart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByExample(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);
}