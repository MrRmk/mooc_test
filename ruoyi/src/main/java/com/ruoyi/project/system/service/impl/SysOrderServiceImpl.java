package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysOrderMapper;
import com.ruoyi.project.system.domain.SysOrder;
import com.ruoyi.project.system.service.ISysOrderService;

/**
 * 预览表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper sysOrderMapper;

    /**
     * 查询预览表
     * 
     * @param id 预览表ID
     * @return 预览表
     */
    @Override
    public SysOrder selectSysOrderById(Long id)
    {
        return sysOrderMapper.selectSysOrderById(id);
    }

    /**
     * 查询预览表列表
     * 
     * @param sysOrder 预览表
     * @return 预览表
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增预览表
     * 
     * @param sysOrder 预览表
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder)
    {
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改预览表
     * 
     * @param sysOrder 预览表
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        sysOrder.setUpdateTime(DateUtils.getNowDate());
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除预览表
     * 
     * @param ids 需要删除的预览表ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderByIds(Long[] ids)
    {
        return sysOrderMapper.deleteSysOrderByIds(ids);
    }

    /**
     * 删除预览表信息
     * 
     * @param id 预览表ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderById(Long id)
    {
        return sysOrderMapper.deleteSysOrderById(id);
    }
}
