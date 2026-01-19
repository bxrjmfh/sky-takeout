package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /** 新增菜品 **/
    void save(DishDTO dishDTO);

    /** 修改菜品 **/
    void update(DishDTO dishDTO);

    /** 批量删除菜品 **/
    void delete(Long[] ids);

    /** 根据id查询菜品 **/
    DishVO getById(Long id);

    /** 根据类别id查询菜品 **/
    List<Dish> listByCategoryId(Long categoryId);

    /** 菜品分页查询 **/
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /** 停售起售菜品 **/
    void updateStatus(Integer status, Long id);
}
