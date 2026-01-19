package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 保存菜品
     */
    public void save(DishDTO dishDTO){
        Dish dish = new Dish();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        for (DishFlavor dishFlavor : flavors){
            dishFlavor.setDishId(dish.getId());
        }
        dishFlavorMapper.insertBatch(dish.getId(),flavors);
    }

    /**
     * 修改菜品信息
     */
    public void update(DishDTO dishDTO){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.updateById(dish);
        dishFlavorMapper.insertBatch(dishDTO.getId(), dishDTO.getFlavors());
    }

    /**
     * 批量删除菜品
     */
    public void delete(Long[] ids){
        for (Long id : ids) {
            dishMapper.deleteById(id);
        }
    }

    /**
     * 根据id查询菜品信息
     */
    public DishVO getById(Long id){
        Dish dish = dishMapper.selectById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishID(id);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    /** 根据类别id查询菜品 **/
    public List<Dish> listByCategoryId(Long categoryId){
        return dishMapper.selectByCategoryId(categoryId);
    }

    /** 分页查询菜品 **/
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO){
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<Dish> page = dishMapper.pageQuery(dishPageQueryDTO);
        long total = page.getTotal();
        List<Dish> records = page.getResult();
        return new PageResult(total, records);
    }

    /** 批量起售停售菜品 **/
    public void updateStatus(Integer status, Long id){
//        for (Long id : ids) {
            Dish dish = new Dish();
            dish.setId(id);
            dish.setStatus(status);
            dishMapper.updateById(dish);
//        }
    }

}
