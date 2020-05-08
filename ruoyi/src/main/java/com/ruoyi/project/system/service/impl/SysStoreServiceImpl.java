package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysStoreMapper;
import com.ruoyi.project.system.domain.SysStore;
import com.ruoyi.project.system.service.ISysStoreService;

/**
 * 门店自提Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-21
 */
@Service
public class SysStoreServiceImpl implements ISysStoreService 
{
    @Autowired
    private SysStoreMapper sysStoreMapper;

    /**
     * 查询门店自提
     * 
     * @param id 门店自提ID
     * @return 门店自提
     */
    @Override
    public SysStore selectSysStoreById(Integer id)
    {
        return sysStoreMapper.selectSysStoreById(id);
    }

    /**
     * 查询门店自提列表
     * 
     * @param sysStore 门店自提
     * @return 门店自提
     */
    @Override
    public List<SysStore> selectSysStoreList(SysStore sysStore)
    {
        return sysStoreMapper.selectSysStoreList(sysStore);
    }

    /**
     * 新增门店自提
     * 
     * @param sysStore 门店自提
     * @return 结果
     */
    @Override
    public int insertSysStore(SysStore sysStore)
    {
        return sysStoreMapper.insertSysStore(sysStore);
    }

    /**
     * 修改门店自提
     * 
     * @param sysStore 门店自提
     * @return 结果
     */
    @Override
    public int updateSysStore(SysStore sysStore)
    {
        return sysStoreMapper.updateSysStore(sysStore);
    }

    /**
     * 批量删除门店自提
     * 
     * @param ids 需要删除的门店自提ID
     * @return 结果
     */
    @Override
    public int deleteSysStoreByIds(Integer[] ids)
    {
        return sysStoreMapper.deleteSysStoreByIds(ids);
    }

    /**
     * 删除门店自提信息
     * 
     * @param id 门店自提ID
     * @return 结果
     */
    @Override
    public int deleteSysStoreById(Integer id)
    {
        return sysStoreMapper.deleteSysStoreById(id);
    }
    
    /**
     * 审核
     */
	@Override
	public int updateStatus(SysStore sysStore) {
		
		return sysStoreMapper.updateStatus(sysStore);
	}
}
