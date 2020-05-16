import { remove, getList, save, update } from '@/api/system/dict'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: '添加字典',
      deptList: [],
      roleList: [],
      isAdd: true,
      permissons: [],
      permissonVisible: false,
      form: {
        name: '',
        id: '',
        detail: '',
        details: []
      },
      rules: {
        name: [
          { required: true, message: '请输入字典名称', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ]

      },
      listQuery: {
        name: undefined
      },
      list: null,
      listLoading: true,
      selRow: {}
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data
        this.listLoading = false
      }).catch(() => {
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

    },

    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        name: '',
        id: '',
        details: [],
        detail: []

      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加字典'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var dictName = self.form.name
          var dictValues = ''
          for (var key in self.form.details) {
            var item = self.form.details[key]
            dictValues += item['key'] + ':' + item['value'] + ';'
          }
          if (this.form.id !== '') {
            update({ id: self.form.id, dictName: dictName, dictValues: dictValues }).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
            })
          } else {
            save({ dictName: dictName, dictValues: dictValues }).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
            })
          }
        } else {
          return false
        }
      })
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.formTitle = '修改字典'
        var detail = this.selRow.detail.split(',')
        var details = []
        detail.forEach(function(val, index) {
          var arr = val.split(':')
          details.push({ 'key': arr[0], 'value': arr[1] })
        })
        this.form = { name: this.selRow.name, id: this.selRow.id, details: details, detail: this.selRow.detail }
        this.formVisible = true
      }
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id

        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    },
    addDetail() {
      var details = this.form.details

      details.push({
        value: '',
        key: ''
      })
      this.form.details = details
    },
    removeDetail(detail) {
      var details = []
      this.form.details.forEach(function(val, index) {
        if (detail.key !== val.key) {
          details.push(val)
        }
      })
      this.form.details = details
    }

  }
}
