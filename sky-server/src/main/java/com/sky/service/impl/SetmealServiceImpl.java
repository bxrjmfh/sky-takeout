package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    /**
     * 保存套餐
     */
    public void saveWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // 插入setmeal表中
        setmealMapper.insert(setmeal);
        Long setmealId = setmeal.getId();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
            // 插入setmeal_dish表中
            setmealDishMapper.insert(setmealDish);
        }
    }

    /**
     * 更新套餐信息
     */
    public void updateWithDish(SetmealDTO setmealDTO){
        Setmeal setmeal = new Setmeal();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        BeanUtils.copyProperties(setmealDTO, setmeal);
//      更新表中信息
        setmealMapper.update(setmeal);
        Long setmealId = setmeal.getId();
//        应该是先删除所有的info
        setmealDishMapper.delete(setmealId);

        for (SetmealDish setmealDish : setmealDishes){
            setmealDish.setSetmealId(setmealId);
//            插入到setmeal_dish 表中
            setmealDishMapper.insert(setmealDish);
        }
    }

    /**
     * 批量删除套餐信息
     */
    public void deleteWithDish(List<Long> ids){
        for (Long id : ids){
            setmealDishMapper.delete(id);
            setmealMapper.delete(id);
        }
    }

    /**
     * 根据id获取套餐信息
     */
    public SetmealVO getByIdWithDish(Long id){
        Setmeal setmeal = setmealMapper.select(id);
        List<SetmealDish> setmealDishes = setmealDishMapper.select(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    /**
     * 实现更新状态
     */
    public void updateStatus(Integer status, Long id){
        Setmeal setmeal = new Setmeal();
        setmeal.setId(id);
        setmeal.setStatus(status);
        setmealMapper.update(setmeal);
    }

    /**
     * 分页查询相关信息
     */
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<Setmeal> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        long total = page.getTotal();
//        List<Setmeal> records = page.getResult();
        List<SetmealVO> records = new ArrayList<>();
        for (Setmeal setmeal : page.getResult()){
            SetmealVO setmealVO = new SetmealVO();
            BeanUtils.copyProperties(setmeal, setmealVO);
            records.add(setmealVO);
        }
        return new PageResult(total, records);
    }


}
