package com.example.cangqiong.mapper;


import com.example.cangqiong.entity.vo.SetmealsSumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkspaceMapper {


    /**
     * 按照状态 分组 查询套餐 启售 停售数量
     *
     * @return
     */
    @Select(" select sum(case when status = 0 then 1 else 0 end) as discontinued, " +
            " sum(case when status = 1 then 1 else 0 end) as sold  from setmeal")
    public SetmealsSumVo overviewSetmeals();

    @Select(" select sum(case when status = 0 then 1 else 0 end) as discontinued," +
            "  sum(case when status = 1 then 1 else 0 end) as sold  from dish")
    public SetmealsSumVo overviewDishes();
}
