package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysResources;
import java.util.List;

/**
 * 资源Mapper接口
 * 
 * @author chenjb
 * @date 2020-05-07
 */
public interface SysResourcesMapper 
{
    /**
     * 查询资源
     * 
     * @param id 资源ID
     * @return 资源
     */
    public SysResources selectSysResourcesById(Long id);

    /**
     * 查询资源列表
     * 
     * @param sysResources 资源
     * @return 资源集合
     */
    public List<SysResources> selectSysResourcesList(SysResources sysResources);

    /**
     * 新增资源
     * 
     * @param sysResources 资源
     * @return 结果
     */
    public int insertSysResources(SysResources sysResources);

    /**
     * 修改资源
     * 
     * @param sysResources 资源
     * @return 结果
     */
    public int updateSysResources(SysResources sysResources);

    /**
     * 删除资源
     * 
     * @param id 资源ID
     * @return 结果
     */
    public int deleteSysResourcesById(Long id);

    /**
     * 批量删除资源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysResourcesByIds(Long[] ids);
}
