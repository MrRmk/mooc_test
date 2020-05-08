<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item prop="courseName">
        <el-input
          class="search-name"
          prefix-icon="el-icon-search"
          v-model="queryParams.courseName"
          placeholder="请输入课程名"
          clearable
          size="medium"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="coursewareType" style=" margin-left:-15px">
        <el-select
          class="select-input"
          v-model="queryParams.coursewareType"
          placeholder="全部学校"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in coursewareTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <div style="float: right;">
        <el-row :gutter="10" class="mb8" justify="space-between">
          <el-col :span="1.5">
            <el-button
              size="medium"
              :disabled="single"
              @click="handleAdd"
              v-hasPermi="['system:mission:add']"
            >查看任务</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              size="medium"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:mission:edit']"
            >上传素材</el-button>
          </el-col>
        </el-row>
      </div>
    </el-form>

    <el-table v-loading="loading" :data="missionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约状态(0未预约，1已预约)" align="center" prop="id" />
      <el-table-column label="任务编号" align="center" prop="missionNumber" />
      <el-table-column label="学校ID" align="center" prop="schoolId" />
      <el-table-column label="课程名" align="center" prop="courseName" />
      <el-table-column label="讲师" align="center" prop="teacherId" />
      <el-table-column label="课件地址" align="center" prop="coursewareUrl" />
      <el-table-column
        label="课件类型(ppt,讲稿)"
        align="center"
        prop="coursewareType"
        :formatter="coursewareTypeFormat"
      />
      <el-table-column label="拍摄时间" align="center" prop="shotTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shotTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拍摄地点" align="center" prop="shotLocation" />
      <el-table-column
        label="拍摄形式(虚拟抠像，访谈方式)"
        align="center"
        prop="shotType"
        :formatter="shotTypeFormat"
      />
      <el-table-column label="摄像人员id" align="center" prop="shotPersonnel" />
      <el-table-column label="后期人员id" align="center" prop="postPersonnel" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="审核附件地址" align="center" prop="auditFileurl" />
      <el-table-column label="审核标签" align="center" prop="auditLabel" />
    </el-table>
    <div style="width:60%">
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
    <!-- 添加或修改任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务编号" prop="missionNumber">
          <el-input v-model="form.missionNumber" placeholder="请输入任务编号" />
        </el-form-item>
        <el-form-item label="学校ID" prop="schoolId">
          <el-input v-model="form.schoolId" placeholder="请输入学校ID" />
        </el-form-item>
        <el-form-item label="课程名" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名" />
        </el-form-item>
        <el-form-item label="讲师" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入讲师" />
        </el-form-item>
        <el-form-item label="课件地址" prop="coursewareUrl">
          <el-input v-model="form.coursewareUrl" placeholder="请输入课件地址" />
        </el-form-item>
        <el-form-item label="课件类型(ppt,讲稿)">
          <el-select v-model="form.coursewareType" placeholder="请选择课件类型(ppt,讲稿)">
            <el-option
              v-for="dict in coursewareTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="拍摄时间" prop="shotTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.shotTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择拍摄时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="拍摄地点" prop="shotLocation">
          <el-input v-model="form.shotLocation" placeholder="请输入拍摄地点" />
        </el-form-item>
        <el-form-item label="拍摄形式(虚拟抠像，访谈方式)">
          <el-select v-model="form.shotType" placeholder="请选择拍摄形式(虚拟抠像，访谈方式)">
            <el-option
              v-for="dict in shotTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="摄像人员id" prop="shotPersonnel">
          <el-input v-model="form.shotPersonnel" placeholder="请输入摄像人员id" />
        </el-form-item>
        <el-form-item label="后期人员id" prop="postPersonnel">
          <el-input v-model="form.postPersonnel" placeholder="请输入后期人员id" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="审核附件地址" prop="auditFileurl">
          <el-input v-model="form.auditFileurl" placeholder="请输入审核附件地址" />
        </el-form-item>
        <el-form-item label="审核标签" prop="auditLabel">
          <el-input v-model="form.auditLabel" placeholder="请输入审核标签" />
        </el-form-item>
        <el-form-item label="删除标志N未删除Y已删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入删除标志N未删除Y已删除" />
        </el-form-item>
        <el-form-item label="删除时间" prop="deletedTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.deletedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择删除时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="添加时间" prop="submitTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择添加时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="预约状态(0未预约，1已预约)">
          <el-radio-group v-model="form.appointmentStatus">
            <el-radio
              v-for="dict in appointmentStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMission,
  getMission,
  delMission,
  addMission,
  updateMission,
  exportMission
} from "@/api/system/mission";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 任务表格数据
      missionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 课件类型(ppt,讲稿)字典
      coursewareTypeOptions: [],
      // 拍摄形式(虚拟抠像，访谈方式)字典
      shotTypeOptions: [],
      // 删除标志N未删除Y已删除字典
      isDeletedOptions: [],
      // 预约状态(0未预约，1已预约)字典
      appointmentStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        missionNumber: undefined,
        schoolId: undefined,
        courseName: undefined,
        teacherId: undefined,
        coursewareUrl: undefined,
        coursewareType: undefined,
        shotTime: undefined,
        shotLocation: undefined,
        shotType: undefined,
        shotPersonnel: undefined,
        postPersonnel: undefined,
        remarks: undefined,
        auditFileurl: undefined,
        auditLabel: undefined,
        isDeleted: undefined,
        deletedTime: undefined,
        submitTime: undefined,
        appointmentStatus: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [{ required: true, message: "修改时间不能为空", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_courseware_type").then(response => {
      this.coursewareTypeOptions = response.data;
    });
    this.getDicts("sys_shot_type").then(response => {
      this.shotTypeOptions = response.data;
    });
    this.getDicts("sys_is_deleted").then(response => {
      this.isDeletedOptions = response.data;
    });
    this.getDicts("sys_order_status").then(response => {
      this.appointmentStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询任务列表 */
    getList() {
      this.loading = true;
      listMission(this.queryParams).then(response => {
        this.missionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 课件类型(ppt,讲稿)字典翻译
    coursewareTypeFormat(row, column) {
      return this.selectDictLabel(
        this.coursewareTypeOptions,
        row.coursewareType
      );
    },
    // 拍摄形式(虚拟抠像，访谈方式)字典翻译
    shotTypeFormat(row, column) {
      return this.selectDictLabel(this.shotTypeOptions, row.shotType);
    },
    // 删除标志N未删除Y已删除字典翻译
    isDeletedFormat(row, column) {
      return this.selectDictLabel(this.isDeletedOptions, row.isDeleted);
    },
    // 预约状态(0未预约，1已预约)字典翻译
    appointmentStatusFormat(row, column) {
      return this.selectDictLabel(
        this.appointmentStatusOptions,
        row.appointmentStatus
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        missionNumber: undefined,
        schoolId: undefined,
        courseName: undefined,
        teacherId: undefined,
        coursewareUrl: undefined,
        coursewareType: undefined,
        shotTime: undefined,
        shotLocation: undefined,
        shotType: undefined,
        shotPersonnel: undefined,
        postPersonnel: undefined,
        remarks: undefined,
        auditFileurl: undefined,
        auditLabel: undefined,
        isDeleted: undefined,
        deletedTime: undefined,
        submitTime: undefined,
        appointmentStatus: "0",
        updateTime: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getMission(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateMission(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addMission(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除任务编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return delMission(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有任务数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportMission(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
