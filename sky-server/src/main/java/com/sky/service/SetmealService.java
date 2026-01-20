package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
//    保存信息
    void saveWithDish(SetmealDTO setmealDTO);
//    更新套餐信息
    void updateWithDish(SetmealDTO setmealDTO);
//    批量删除套餐信息
    void deleteWithDish(List<Long> ids);
//    获取套餐VO信息
    SetmealVO getByIdWithDish(Long id);
//    修改套餐状态
    void updateStatus(Integer status, Long id);
//    分页查询
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);



}
