package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysResourcesMapper;
import com.ruoyi.project.system.domain.SysResources;
import com.ruoyi.project.system.service.ISysResourcesService;

/**
 * 资源Service业务层处理
 * 
 * @author chenjb
 * @date 2020-05-07
 */
@Service
public class SysResourcesServiceImpl implements ISysResourcesService 
{
    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    /**
     * 查询资源
     * 
     * @param id 资源ID
     * @return 资源
     */
    @Override
    public SysResources selectSysResourcesById(Long id)
    {
        return sysResourcesMapper.selectSysResourcesById(id);
    }

    /**
     * 查询资源列表
     * 
     * @param sysResources 资源
     * @return 资源
     */
    @Override
    public List<SysResources> selectSysResourcesList(SysResources sysResources)
    {
        return sysResourcesMapper.selectSysResourcesList(sysResources);
    }

    /**
     * 新增资源
     * 
     * @param sysResources 资源
     * @return 结果
     */
    @Override
    public int insertSysResources(SysResources sysResources)
    {
        return sysResourcesMapper.insertSysResources(sysResources);
    }

    /**
     * 修改资源
     * 
     * @param sysResources 资源
     * @return 结果
     */
    @Override
    public int updateSysResources(SysResources sysResources)
    {
        sysResources.setUpdateTime(DateUtils.getNowDate());
        return sysResourcesMapper.updateSysResources(sysResources);
    }

    /**
     * 批量删除资源
     * 
     * @param ids 需要删除的资源ID
     * @return 结果
     */
    @Override
    public int deleteSysResourcesByIds(Long[] ids)
    {
        return sysResourcesMapper.deleteSysResourcesByIds(ids);
    }

    /**
     * 删除资源信息
     * 
     * @param id 资源ID
     * @return 结果
     */
    @Override
    public int deleteSysResourcesById(Long id)
    {
        return sysResourcesMapper.deleteSysResourcesById(id);
    }
}
