package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUserRoleInfo;
import com.ruoyi.project.system.domain.vo.MissionVo;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysUserService;
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
import com.ruoyi.project.system.domain.SysMission;
import com.ruoyi.project.system.service.ISysMissionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author chenjb
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/mission")
public class SysMissionController extends BaseController
{
    @Autowired
    private ISysMissionService sysMissionService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMission sysMission)
    {
        startPage();
        List<SysMission> list = sysMissionService.selectSysMissionList(sysMission);
        return getDataTable(list);
    }
    /**
     * 查询任务列表Vo
     */
    @PreAuthorize("@ss.hasPermi('system:mission:list')")
    @GetMapping("/listVo")
    public TableDataInfo listVo(SysMission sysMission)
    {
        startPage();
        List<MissionVo> list = sysMissionService.selectMissionVoList(sysMission);
        return getDataTable(list);
    }
    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMission sysMission)
    {
        List<SysMission> list = sysMissionService.selectSysMissionList(sysMission);
        ExcelUtil<SysMission> util = new ExcelUtil<SysMission>(SysMission.class);
        return util.exportExcel(list, "mission");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMissionService.selectSysMissionById(id));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('system:mission:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping(value = "save")
    public AjaxResult add(@RequestBody SysMission sysMission)
    {
        return toAjax(sysMissionService.insertSysMission(sysMission));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('system:mission:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping(value = "update")
    public AjaxResult edit(@RequestBody SysMission sysMission)
    {
        return toAjax(sysMissionService.updateSysMission(sysMission));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('system:mission:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMissionService.deleteSysMissionByIds(ids));
    }
    /**
     * 获取用户列表
     */
    /* @PreAuthorize("@ss.hasPermi('system:mission:list')")*/
    @GetMapping("/userList")
    public AjaxResult userList(SysUserRoleInfo sysUserRoleInfo)
    {
        List<SysUserRoleInfo> list = userService.selectUserListByRole(sysUserRoleInfo);
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG,list);
        return ajax;
    }
    /**
     * 获取学校列表
     */
    @PreAuthorize("@ss.hasPermi('system:mission:list')")
    @GetMapping("/listSchool")
    public AjaxResult listSchool(SysDept dept)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysDept> depts = deptService.selectDeptSchool(dept);
        ajax.put(AjaxResult.DATA_TAG,depts);
        return ajax;
    }
}
