package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysStore;
import java.util.List;

/**
 * 门店自提Mapper接口
 * 
 * @author ruoyi
 * @date 2020-02-21
 */
public interface SysStoreMapper 
{
    /**
     * 查询门店自提
     * 
     * @param id 门店自提ID
     * @return 门店自提
     */
    public SysStore selectSysStoreById(Integer id);

    /**
     * 查询门店自提列表
     * 
     * @param sysStore 门店自提
     * @return 门店自提集合
     */
    public List<SysStore> selectSysStoreList(SysStore sysStore);

    /**
     * 新增门店自提
     * 
     * @param sysStore 门店自提
     * @return 结果
     */
    public int insertSysStore(SysStore sysStore);

    /**
     * 修改门店自提
     * 
     * @param sysStore 门店自提
     * @return 结果
     */
    public int updateSysStore(SysStore sysStore);

    /**
     * 删除门店自提
     * 
     * @param id 门店自提ID
     * @return 结果
     */
    public int deleteSysStoreById(Integer id);

    /**
     * 批量删除门店自提
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysStoreByIds(Integer[] ids);
    
    /**
     * 审核
     * @param sysStore
     * @return
     */
    public int updateStatus(SysStore sysStore);
}
