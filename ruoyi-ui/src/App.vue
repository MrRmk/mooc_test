<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>
<style>
.el-table__header-wrapper {
  border: 1px solid #e8e8e8;
  border-top-left-radius: 7px;
  border-top-right-radius: 7px;
}
th {
  background-color: #ffffff !important;
  border-right: 1px solid #e8e8e8;
}
td {
  background-color: #ffffff !important;
  border-right: 1px solid #e8e8e8;
}
tr {
  background-color: #ffffff !important;
  border: 1px solid #e8e8e8;
}
.el-table-column--selection {
  border-left: 1px solid #dfe6ec;
}
.select-input {
  background-color: #5e5e5e;
  border-top-right-radius: 7px;
  border-bottom-right-radius: 7px;
}
.search-name {
  padding-top: 1px;
}
.select-input > .el-input > .el-input__inner {
  background-color: #5e5e5e;
  border: none;
  width: 100px;
}
.search-name > .el-input__inner {
  border-radius: 0px;
  border-top-left-radius: 7px;
  border-bottom-left-radius: 7px;
  border: 1px solid #5e5e5e;
}
.search-name > .el-input__inner::-webkit-input-placeholder {
  color: #5e5e5e !important;
  text-align: center;
}
.search-name > .el-input__prefix {
  color: #5e5e5e !important;
}
.el-table th.is-leaf,
.el-table td {
  border-bottom: none !important;
}
</style>
