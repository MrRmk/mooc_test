<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="老师Id" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入老师Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摄像人员id" prop="shotPersonnel">
        <el-input
          v-model="queryParams.shotPersonnel"
          placeholder="请输入摄像人员id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务Id" prop="missionId">
        <el-input
          v-model="queryParams.missionId"
          placeholder="请输入任务Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="拍摄开始时间" prop="shotStartTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.shotStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择拍摄开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="拍摄结束时间" prop="shotEndTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.shotEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择拍摄结束时间">
        </el-date-picker>
      </el-form-item>
        <el-form-item label="0:已预约,1:已拍摄" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择0:已预约,1:已拍摄" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.submitTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:order:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="老师Id" align="center" prop="teacherId" />
      <el-table-column label="摄像人员id" align="center" prop="shotPersonnel" />
      <el-table-column label="任务Id" align="center" prop="missionId" />
      <el-table-column label="拍摄开始时间" align="center" prop="shotStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shotStartTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拍摄结束时间" align="center" prop="shotEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shotEndTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="0:已预约,1:已拍摄" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改预览表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师Id" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入老师Id" />
        </el-form-item>
        <el-form-item label="摄像人员id" prop="shotPersonnel">
          <el-input v-model="form.shotPersonnel" placeholder="请输入摄像人员id" />
        </el-form-item>
        <el-form-item label="任务Id" prop="missionId">
          <el-input v-model="form.missionId" placeholder="请输入任务Id" />
        </el-form-item>
        <el-form-item label="拍摄开始时间" prop="shotStartTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.shotStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择拍摄开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="拍摄结束时间" prop="shotEndTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.shotEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择拍摄结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="0:已预约,1:已拍摄">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择提交时间">
          </el-date-picker>
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/system/order";

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
      // 预览表表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 0:已预约,1:已拍摄字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: undefined,
        shotPersonnel: undefined,
        missionId: undefined,
        shotStartTime: undefined,
        shotEndTime: undefined,
        status: undefined,
        submitTime: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_order_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询预览表列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 0:已预约,1:已拍摄字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        teacherId: undefined,
        shotPersonnel: undefined,
        missionId: undefined,
        shotStartTime: undefined,
        shotEndTime: undefined,
        status: "0",
        submitTime: undefined,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预览表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预览表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$confirm('是否确认删除预览表编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有预览表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>