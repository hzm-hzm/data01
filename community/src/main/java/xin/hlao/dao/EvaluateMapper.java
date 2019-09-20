package xin.hlao.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xin.hlao.bean.Evaluate;
import xin.hlao.bean.EvaluateExample;

public interface EvaluateMapper {
    long countByExample(EvaluateExample example);

    int deleteByExample(EvaluateExample example);

    int deleteByPrimaryKey(String eid);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(String eid);

    int updateByExampleSelective(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByExample(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}