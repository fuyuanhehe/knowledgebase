<!--
@file：项目协作-项目编辑
@createTime：2020-04-23
@updateTime：2020-04-23
@author：Claire
-->
<template>
  <div class="projectEdit">
    <!--面包屑-->
    <div class="breadcrumb-box">
      <v-breadcrumb :nav="breadcrumbNav"/>
    </div>
    <div class="content">
      <el-form class="ruleForm" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
        <div class="ruleForm-item">
          <h3>项目简介</h3>
          <el-form-item label="简      介：" prop="introduction">
            <el-input type="textarea" v-model="ruleForm.introduction" placeholder="1000字以内"></el-input>
          </el-form-item>
        </div>
        <v-divider></v-divider>
        <div class="ruleForm-item clearfix">
          <h3>项目信息</h3>
          <el-col :span="8">
            <el-form-item label="项目名称：" prop="name">
              <el-input v-model="ruleForm.name" placeholder="请输入项目名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="项目类别：" prop="type">
              <el-select v-model="ruleForm.type" placeholder="请选择项目类别">
                <el-option label="内部项目" :value="0"></el-option>
                <el-option label="外部项目" :value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="项目创建人：" prop="createUserNick">
              <el-input v-model="ruleForm.createUserNick" placeholder="请输入项目创建人" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划开始时间：" prop="beginDate">
              <el-date-picker type="date" placeholder="请选择日期" v-model="ruleForm.beginDate"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划截止时间：" prop="endDate">
              <el-date-picker type="date" placeholder="请选择日期" v-model="ruleForm.endDate"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="共计：" prop="term">
              <el-input class="w150 mr10" v-model="ruleForm.term" ></el-input>
              <span>天</span>
            </el-form-item>
          </el-col>
          <el-col :span="9">
          <el-form-item label="项目总金额：" prop="totalMoney">
            <el-input class="mr10" v-model="ruleForm.totalMoney" placeholder="请输入项目金额"></el-input>
            <span>元</span>
          </el-form-item>
          </el-col>
        </div>
        <v-divider></v-divider>
        <div class="ruleForm-item clearfix">
          <h3>客户信息</h3>
          <el-col :span="8">
            <el-form-item label="客户名称：" prop="customer">
              <el-input v-model="ruleForm.customer" placeholder="请输入客户名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户对接人：" prop="conscientiousUser">
              <el-input v-model="ruleForm.conscientiousUser" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="对接人手机号：" prop="phone">
              <el-input v-model="ruleForm.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="对接人座机号：" prop="landline">
              <el-input v-model="ruleForm.landline" placeholder="请输入座机号码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户地址：" prop="customerAddr">
              <el-input v-model="ruleForm.customerAddr" placeholder="请输入地址"></el-input>
            </el-form-item>
          </el-col>
        </div>
        <v-divider></v-divider>
        <div class="ruleForm-item">
          <h3>项目关系人</h3>
          <el-form-item label="项目负责人：" prop="chargePerson">
            <el-input class="cursor_p"
                      readonly
                      v-model="chargePersonName"
                      suffix-icon="el-icon-plus"
                      @click.native="handlerDialogShow('selectChargePersonDialog')"
                      placeholder="请选择项目负责人"></el-input>
          </el-form-item>
          <el-form-item label="项目参与人：" prop="participationUsers">
            <el-input class="cursor_p"
                      readonly
                      v-model="participationUserName"
                      suffix-icon="el-icon-plus"
                      @click.native="handlerDialogShow('selectParticipationUsersDialog')"
                      placeholder="请选择项目参与人"></el-input>
          </el-form-item>
          <el-form-item label="项目抄送人：" prop="duplicateUsers">
            <el-input class="cursor_p"
                      readonly
                      v-model="duplicateUsersName"
                      suffix-icon="el-icon-plus"
                      @click.native="handlerDialogShow('selectDuplicateUsersDialog')"
                      placeholder="请选择项目抄送人"></el-input>
          </el-form-item>
        </div>
        <v-divider></v-divider>
        <div class="ruleForm-item">
          <h3>项目相关附件</h3>
          <el-form-item label="附件：" prop="createUser">
            <upload-file
              class="file-upload"
              :file-list.sync="files"
              :width="110"
              :height="40"
              @success="projectUploadSuccess"
              text="点击上传">
            </upload-file>
          </el-form-item>
        </div>
        <v-divider></v-divider>
        <el-form-item>
          <el-button class="btn_bg_blue" @click="submitForm()">确定</el-button>
          <el-button class="btn_border_blue" @click="goBack">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!--项目负责人选择弹框-->
    <selectUserDialog ref="selectChargePersonDialog"
                      @selected="getSelectedChargePerson"
                      selectType="single"
                      dialogTitle="项目负责人"
                      :selectedUsers="checkedChargePerson"
                      :selectedUserIds="ruleForm.chargePerson"></selectUserDialog>
    <!--项目参与人选择弹框-->
    <selectUserDialog ref="selectParticipationUsersDialog"
                      dialogTitle="项目参与人"
                      @selected="getSelectedParticipationUsers"
                      :selectedUsers="checkedParticipationUsers"
                      :selectedUserIds="ruleForm.participationUsers"></selectUserDialog>
    <!--项目抄送人选择弹框-->
    <selectUserDialog ref="selectDuplicateUsersDialog"
                      dialogTitle="项目抄送人"
                      @selected="getSelectedDuplicateUsers"
                      :selectedUsers="checkedDuplicateUsers"
                      :selectedUserIds="ruleForm.duplicateUsers"></selectUserDialog>
  </div>
</template>

<script>
  import selectUserDialog from './dialog/selectUserDialog';
  export default {
    name: 'projectEdit',
    components: {
      selectUserDialog
    },
    data () {
      return {
        breadcrumbNav: [
          {
            name: '项目协作',
            path: '/project',
            routeName: 'project'
          }, {
            name: '项目立项',
            path: '/project/projectApply',
            routeName: 'projectApply'
          }, {
            name: this.$route.query.type === 'add' ? '新建项目' : '编辑项目',
            path: '/project/projectEdit',
            routeName: 'projectEdit',
            disabled: true
          }
        ], // 面包屑导航集合
        pageType: this.$route.query.type, // 页面类型： add-新增；edit-编辑
        projectId: '', // 编辑项目id
        ruleForm: {
          name: '',
          introduction: '',
          type: '',
          createUserNick: this.$store.state.user.nickName, // 创建人昵称
          beginDate: '',
          endDate: '',
          term: '',
          totalMoney: '',
          customer: '', // 客户名称
          conscientiousUser: '', // 客户对接人
          phone: '', // 对接人手机号
          landline: '', // 对接人座机
          customerAddr: '', // 客户地址
          chargePerson: '', // 项目负责人
          participationUsers: [], // 项目参与人
          duplicateUsers: [], // 项目抄送人
          enclosures: '' // 附件
        }, // 表单对象
        rules: this.validationRules(), // 表单验证规则
        currentEditProjectName: '', // 修改时当前编辑项目名
        checkedChargePerson: {}, // 选中项目负责人对象
        chargePersonName: '', // 选中项目负责人显示姓名
        checkedParticipationUsers: [], // 选中项目参与人对象数组
        participationUserName: '', // 选中项目参与人显示姓名
        checkedDuplicateUsers: [], // 选中项目抄送人对象数组
        duplicateUsersName: '', // 选中项目抄送人显示姓名
        files: []
      };
    },
    beforeRouteEnter (to, from, next) {
      if (to.query.type === 'add') { // 新增
        next(vm => {
        });
      } else { // 修改
        next(vm => {
          vm.projectId = to.query.id;
          vm.getData(to.query.id);
        });
      }
    },
    created(){

     /* let data = this.$store.state.user;
         console.log(data )*/
    },

    methods: {
      /**
       * 获取项目信息
       * @param id 项目id
       */
      async getData (id) {
        const params = { id: id };
        this.ruleForm = await this.$api.getProject(params);
        if (this.pageType === 'edit') {
          this.currentEditProjectName = this.ruleForm.name;
        }
      },
      /**
       * 对话框显示
       */
      handlerDialogShow (dialogName) {
        this.$refs[dialogName].handleShow();
      },
      /**
       * 获取项目负责人选中对象
       */
      getSelectedChargePerson (data) {
        this.ruleForm.chargePerson = data.id;
        this.chargePersonName = data.nickName;
        this.checkedChargePerson = data;
      },
      /**
       * 获取项目参与人选中对象
       */
      getSelectedParticipationUsers (data) {
        const selectedName = [];
        for (let i = 0; i < data.length; i++) {
          this.ruleForm.participationUsers.push(data[i].id);
          selectedName.push(data[i].nickName);
        }
        this.participationUserName = selectedName.join('、');
        this.checkedParticipationUsers = data;
      },
      /**
       * 获取项目抄送人选中对象
       */
      getSelectedDuplicateUsers (data) {
        const selectedName = [];
        for (let i = 0; i < data.length; i++) {
          this.ruleForm.duplicateUsers.push(data[i].id);
          selectedName.push(data[i].nickName);
        }
        this.duplicateUsersName = selectedName.join('、');
        this.checkedDuplicateUsers = data;
      },
      /**
       * 提交表单
       */
      submitForm () {
        this.$refs.ruleForm.validate(async (valid) => {
          if (valid) {
            const params = this.ruleForm;
            console.log( params)

            if (this.pageType === 'add') { // 新增
              await this.$api.saveProject(params).then(res => {
                this.$message.success('新增项目成功!');
              });
            } else { // 修改
              await this.$api.modifyProject(params).then(res => {
                this.$message.success('修改项目成功!');
              });
            }
           // this.$router.push({ path: '/system/systemSetup/userMgt' });
            this.$router.push({ path: '/project/projectApply' });


          }
        });
      },

      projectUploadSuccess(res, file){
        this.ruleForm.enclosures = JSON.stringify(res.data) ;
      },
      /**
       * 上传文件
       *
       * /


      /**
       * 取消（返回上一页面）
       */
      goBack () {
        if (this.pageType == 'add') {
          this.$router.push({ name: 'projectApply' });
        } else {
          this.$router.push({ name: 'recycleBin' });
        }
      },
      validationRules () {
        const checkProjectOnly = (rule, value, callback) => {
          // 修改项目时，判断当前输入名称与原名称是否一致，如一致则不验证
          if (this.pageType === 'edit' && value === this.currentEditProjectName) {
            callback();
          } else {
            this.$api.checkProjectName({ name: value }).then(res => {
              if (!res) {
                callback();
              } else {
                callback(new Error('该项目名称已经存在，请重新输入'));
              }
            });
          }
        };
        // 手机号验证
        const checkPhone = (rule, value, callback) => {
          if (value) {
            if (!(/^1[3456789]\d{9}$/.test(value))) return callback(new Error('请正确填写 11 位手机号'));
            callback();
          }
        };
        // 座机号验证
        const checkLandline = (rule, value, callback) => {
          // /^[0][1-9]{3,4}-[0-9]{8}$/
          if (value) {
            if (!(/0\d{2,3}-\d{7,8}/.test(value))) return callback(new Error('请正确填写座机号'));
            callback();
          }
        };
        // 金额验证
        const checkMoney = (rule, value, callback) => {
          const reg = /(^[1-9](\d+)?(\.\d{1,2})?$)|(^[1-9]$)|(^\d\.[1-9]{1,2}$)|(^\d\.[0]{1}[1-9]{1}$|(^\d\.[1-9]{1}[0]{1}$)$)/;
          if (!(reg.test(value))) return callback(new Error('请正确填写金额(最多两位小数)'));
          callback();
        };
        return {
          name: [
            { required: true, message: '请输入项目名称', trigger: 'blur' },
            { required: true, validator: checkProjectOnly, trigger: 'blur' },
            { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
          ],
          introduction: [
            { required: true, message: '请输入项目简介', trigger: 'blur' },
            { min: 1, max: 1000, message: '长度在 1 到 1000 个字符', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '请选择项目类型', trigger: 'change' }
          ],
          createUserNick: [
            { required: true, message: '请输入项目创建人', trigger: 'change' }
          ],
          beginDate: [
            { required: true, message: '请选择项目开始时间', trigger: 'change' }
          ],
          endDate: [
            { required: true, message: '请选择项目截止时间', trigger: 'change' }
          ],
          term: [
            { required: true, message: '请输入项目期限', trigger: 'change' }
          ],
          totalMoney: [
            { required: true, message: '请输入项目金额', trigger: 'blur' },
            { required: true, validator: checkMoney, trigger: 'blur' }
          ],
          phone: [
            { validator: checkPhone, trigger: 'change' }
          ],
          landline: [
            { validator: checkLandline, trigger: 'change' }
          ],
          chargePerson: [
            { required: true, message: '请选择项目负责人', trigger: 'change' }
          ],
          participationUsers: [
            { required: true, message: '请选择项目参与人', trigger: 'change' }
          ]
        };
      }
    }
  };
</script>

<style scoped lang="scss">
  .projectEdit {
    .content {
      .ruleForm {
        .ruleForm-item {
          h3 {
            width: 130px;
            height:36px;
            line-height: 36px;
            text-align: center;
            margin-bottom: 30px;
            font-size: $font-size-middle;
            background:rgba(246,246,246,1);
            border-radius:4px;
          }
        }
      }
    }
  }
</style>
