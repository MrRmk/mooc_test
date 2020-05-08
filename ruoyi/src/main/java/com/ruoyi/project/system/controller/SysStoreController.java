package com.ruoyi.project.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.SysStore;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysStoreService;
import com.ruoyi.project.system.service.ISysUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 门店自提Controller
 * 
 * @author ruoyi
 * @date 2020-02-21
 */
@RestController
@RequestMapping("/system/store")
public class SysStoreController extends BaseController
{
    @Autowired
    private ISysStoreService sysStoreService;
    
    @Autowired
    private ISysUserService userService;

    /**
     * 查询门店自提列表
     */
    //@PreAuthorize("@ss.hasPermi('system:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStore sysStore)
    {
        startPage();
        List<SysStore> list = sysStoreService.selectSysStoreList(sysStore);
        return getDataTable(list);
    }

    /**
     * 导出门店自提列表
     */
    //@PreAuthorize("@ss.hasPermi('system:store:export')")
    @Log(title = "门店自提", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStore sysStore)
    {
        List<SysStore> list = sysStoreService.selectSysStoreList(sysStore);
        ExcelUtil<SysStore> util = new ExcelUtil<SysStore>(SysStore.class);
        return util.exportExcel(list, "store");
    }

    /**
     * 获取门店自提详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:store:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysStoreService.selectSysStoreById(id));
    }

    /**
     * 新增门店自提
     */
    //@PreAuthorize("@ss.hasPermi('system:store:add')")
    @Log(title = "门店自提", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStore sysStore)
    {
        return toAjax(sysStoreService.insertSysStore(sysStore));
    }

    /**
     * 修改门店自提
     */
    //@PreAuthorize("@ss.hasPermi('system:store:edit')")
    @Log(title = "门店自提", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStore sysStore)
    {
        return toAjax(sysStoreService.updateSysStore(sysStore));
    }
    
    /**
     * 审核
     * @param sysStore
     * @return
     */
    @Log(title = "门店自提", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody SysStore sysStore){
    	
    	int r=sysStoreService.updateStatus(sysStore);
    	if(r>0){
    		SysStore s=sysStoreService.selectSysStoreById(sysStore.getId());
    		SysUser user =new SysUser();
    		user.setUserName(s.getPhone());
    		user.setPhonenumber(s.getPhone());
    		user.setDeptId(Long.valueOf("103"));
    		user.setNickName(s.getName());
    		String[] arr = new String[]{"1"};
            /*1.字符串数组转换为集合*/
            List<Long> longList = Arrays.asList(arr)
                    //调用stream获得操作流
                    .stream()
                    //调用map将集合中的每个字符串装换为Long类型
                    .map(Long::parseLong)
                    //收约成一个Long类型集合
                    .collect(Collectors.toList());
            //集合转换为数组
            Long[] longArr =  longList.toArray(new Long[]{});
    		user.setPostIds(longArr);
    		String[] arrRole = new String[]{"1"};
            /*1.字符串数组转换为集合*/
            List<Long> longroleList = Arrays.asList(arrRole)
                    //调用stream获得操作流
                    .stream()
                    //调用map将集合中的每个字符串装换为Long类型
                    .map(Long::parseLong)
                    //收约成一个Long类型集合
                    .collect(Collectors.toList());
            //集合转换为数组
            Long[] longArrRole =  longroleList.toArray(new Long[]{});
            user.setRoleIds(longArrRole);
    		if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
            {
                return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
            }
            else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
            {
                return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
            }
            else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
            {
                return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
            }
            user.setCreateBy("admin");
            user.setPassword(SecurityUtils.encryptPassword("123456"));
            userService.insertUser(user);
    		return AjaxResult.suc(200,"成功");
    	}
    	return AjaxResult.error(-10000, "失败");
    }

    /**
     * 删除门店自提
     */
   // @PreAuthorize("@ss.hasPermi('system:store:remove')")
    @Log(title = "门店自提", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysStoreService.deleteSysStoreByIds(ids));
    }
}
