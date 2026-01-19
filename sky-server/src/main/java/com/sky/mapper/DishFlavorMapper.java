package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    @Select("select * from dish_flavor where dish_id = #{dishId} ")
    public List<DishFlavor> getByDishID(Long dishId);

    /** 根据菜品id删除口味 **/
    void deleteByDishId(Long dishId);

    /** 批量插入口味 **/
    void insertBatch(Long dishId, List<DishFlavor> flavors);
}
