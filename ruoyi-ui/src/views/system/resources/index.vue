<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="素材名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入素材名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摄像人员Id" prop="shotPersonnel">
        <el-input
          v-model="queryParams.shotPersonnel"
          placeholder="请输入摄像人员Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摄像人员Id" prop="fileUrl">
        <el-input
          v-model="queryParams.fileUrl"
          placeholder="请输入摄像人员Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="状态(0未制作，1已制作)" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态(0未制作，1已制作)" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="任务ID" prop="missionId">
        <el-input
          v-model="queryParams.missionId"
          placeholder="请输入任务ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="知识点ID" prop="knowledgeId">
        <el-input
          v-model="queryParams.knowledgeId"
          placeholder="请输入知识点ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="queryParams.remarks"
          placeholder="请输入备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除NY" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入是否删除NY"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除NY" prop="submitTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.submitTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择是否删除NY">
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
          v-hasPermi="['system:resources:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:resources:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:resources:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:resources:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="resourcesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="是否删除NY" align="center" prop="id" />
      <el-table-column label="素材名称" align="center" prop="name" />
      <el-table-column label="摄像人员Id" align="center" prop="shotPersonnel" />
      <el-table-column label="摄像人员Id" align="center" prop="fileUrl" />
      <el-table-column label="状态(0未制作，1已制作)" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="任务ID" align="center" prop="missionId" />
      <el-table-column label="知识点ID" align="center" prop="knowledgeId" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="是否删除NY" align="center" prop="isDeleted" :formatter="isDeletedFormat" />
      <el-table-column label="是否删除NY" align="center" prop="submitTime" width="180">
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
            v-hasPermi="['system:resources:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:resources:remove']"
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

    <!-- 添加或修改资源对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="素材名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入素材名称" />
        </el-form-item>
        <el-form-item label="摄像人员Id" prop="shotPersonnel">
          <el-input v-model="form.shotPersonnel" placeholder="请输入摄像人员Id" />
        </el-form-item>
        <el-form-item label="摄像人员Id" prop="fileUrl">
          <el-input v-model="form.fileUrl" placeholder="请输入摄像人员Id" />
        </el-form-item>
        <el-form-item label="状态(0未制作，1已制作)">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="任务ID" prop="missionId">
          <el-input v-model="form.missionId" placeholder="请输入任务ID" />
        </el-form-item>
        <el-form-item label="知识点ID" prop="knowledgeId">
          <el-input v-model="form.knowledgeId" placeholder="请输入知识点ID" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="是否删除NY" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除NY" />
        </el-form-item>
        <el-form-item label="是否删除NY" prop="submitTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择是否删除NY">
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
import { listResources, getResources, delResources, addResources, updateResources, exportResources } from "@/api/system/resources";

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
      // 资源表格数据
      resourcesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态(0未制作，1已制作)字典
      statusOptions: [],
      // 是否删除NY字典
      isDeletedOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        shotPersonnel: undefined,
        fileUrl: undefined,
        status: undefined,
        missionId: undefined,
        knowledgeId: undefined,
        remarks: undefined,
        isDeleted: undefined,
        submitTime: undefined
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
    this.getDicts("sys_resources_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_is_deleted").then(response => {
      this.isDeletedOptions = response.data;
    });
  },
  methods: {
    /** 查询资源列表 */
    getList() {
      this.loading = true;
      listResources(this.queryParams).then(response => {
        this.resourcesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态(0未制作，1已制作)字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 是否删除NY字典翻译
    isDeletedFormat(row, column) {
      return this.selectDictLabel(this.isDeletedOptions, row.isDeleted);
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
        name: undefined,
        shotPersonnel: undefined,
        fileUrl: undefined,
        status: "0",
        missionId: undefined,
        knowledgeId: undefined,
        remarks: undefined,
        isDeleted: undefined,
        updateTime: undefined,
        submitTime: undefined
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
      this.title = "添加资源";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getResources(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资源";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateResources(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addResources(this.form).then(response => {
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
      this.$confirm('是否确认删除资源编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delResources(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有资源数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportResources(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>