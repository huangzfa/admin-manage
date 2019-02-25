let tcolumn = new Vue({
    el : '#app',
    data : {
        queryTableName : "",
        queryTableState : "00",
        stateList : [ {
            value : "00",
            label : "全部"
        }, {
            value : "10",
            label : "启用"
        }, {
            value : "20",
            label : "禁用"
        } ],
        tColumnTitles : [ {
            type: 'index',
            width: 70,
            align: 'center'
        }, {
            title : '商户唯一编号',
            key : 'merNo'
        }, {
            title : '商户名称',
            key : 'merName'
        }, {
            title : '商户地址',
            key : 'address'
        }, {
            title : '渠道来源',
            key : 'source'
        }, {
            title : '联系人手机号',
            key : 'contactPhone'
        }, {
            title : '联系人姓名',
            key : 'contactName'
        }, {
            title : '联系人身份证号',
            key : 'idNo'
        }, {
            title : '联系人身份证正面',
            key : 'identityFront'
        }, {
            title : '联系人身份证背面',
            key : 'identityBack'
        }, {
            title : '备注信息',
            key : 'remark'
        }, {
            title : '营业执照号',
            key : 'licenseNo'
        }, {
            title : '营业执照图片地址',
            key : 'licenseImgUrl'
        }, {
            title : '审核状态',
            key : 'authState'
        }, {
            title : '审核人id',
            key : 'auditor'
        }, {
            title : '审核时间',
            key : 'authTime'
        }, {
            title : '添加时间',
            key : 'addTime'
        }, {
            title : '修改时间',
            key : 'modifyTime'
        }, {
            title : '状态',
            key : 'authState',
            width : 70,
            align: 'center',
            render: (h, params) => {
            return h('div', [
                h('p', {
                    style: {
                        marginRight: '5px',
                        color : params.row.state == '10'?'#5cadff':'#ccc'
                    }
                }, params.row.state == '10'?'启用':'禁用')
            ]);
}
},
{
    title: '操作',
        key: 'action',
    width: 150,
    align: 'center',
    render: (h, params) => {
    return h('div', [
        h('Button', {
            props: {
                type: 'primary',
                size: 'small'
            },
            style: {
                marginRight: '5px'
            },
            on: {
                click: () => {
                tcolumn.edit(params.row.tbName);
}
}
}, '编辑'),
    h('Button', {
        props: {
            type: params.row.state == '10'?'error':'success',
            size: 'small'
        },
        on: {
            click: () => {
            tcolumn.editState(params.row.id, params.row.state == '10' ? '20' : '10');
}
}
}, params.row.state == '10'?'禁用':'启用' )
]);
}
} ],
tColumnDatas : [],
    tableLoading : false,
    addModel : false,
    addTableName : "",
    tableNameList: [],
    columnDataUnAdded: [],
    columnTargetKeys: [],
    maintainTableName: "",
    formValidate: {
            name: '',
        address: '',
        source: '',
        contactPhone: '',
        identityFront: '',
        contactName: '',
        identityBack: '',
        licenseNo: '',
        licenseImgUrl: '',
        state:false,
        remark: ''
},ruleValidate: {
    name: [
        { required: true, message: '请输入商户名称', trigger: 'blur' }
    ],address: [
        { required: true, message: '请输入商户地址', trigger: 'blur' }
    ],source: [
        { required: true, message: '请输入渠道来源', trigger: 'blur' }
    ],contactPhone: [
        { required: true, message: '请输入联系人手机号', trigger: 'blur' }
    ],contactName: [
        { required: true, message: '请输入联系人姓名', trigger: 'blur' }
    ],
        licenseNo: [
        { required: true, message: '请输入营业执照号', trigger: 'blur' }
    ]
},
    addColumn: [],
    removeColumn: [],
    page : 1,
    size : 10,
    totalCnt : 0
},
methods : {
    rowClassName (row, index) {
        if (row.state != '10') {
            return 'rule-table-disable';
        }else{
            return 'rule-table-able';
        }
    },
    doQuery : function(){
        this.$Loading.start();
        this.tableLoading = true;
        axios.get('/a/merchant/pageQuery', {
            params : {
                pageIndex : this.page,
                pageSize : this.size
            }
        }).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            let data=response.data.data
            console.log(data.data)
            this.page=data.pageIndex
            this.size=data.pageSize
            this.totalCnt=data.pageCount
            this.tColumnDatas=data.data
            this.$Loading.finish();
        }else{
            this.$Loading.error();
        }
    }).catch(function(error){
            this.tableLoading = false;
            this.$Loading.error();
        });
    },
    identityFrontError:function(file){
        this.$Notice.warning({
            title: '上传提示：',
            desc: '您上传的' + file.name + ' 文件格式不正确，请重新选择[jpg or png]'
        });
    },
    suuccessResult:function(response){
        if(response.code==1){
            this.formValidate.identityFront=response.url
        }
    },
    suuccessidentityBack:function(response){
        if(response.code==1){
            this.formValidate.identityBack=response.url
        }
    },
    suuccesslicenseImgUrl:function(response){
        if(response.code==1){
            this.formValidate.licenseImgUrl=response.url
        }
    },
    pageChange : function(value){
        this.page = value;
        this.doQuery();
    },
    queryReset : function(){
        this.queryTableName = "";
        this.queryTableState = "00";
        this.doQuery();
    },
    edit : function(tableName){
        this.changeAddTable(tableName);
        this.addModel = true;
        this.addTableName = tableName;
    },
    handleReset(name) {
        this.addModel=false;
        this.$refs[name].resetFields();
    },
    handleSubmit(name) {
        this.$refs[name].validate((valid) => {
            if (valid) {
                console.log(this.formValidate)
                // 为给定 ID 的 user 创建请求
                axios.post('/a/merchant/add',{
                    merName:this.formValidate.name,
                    address:this.formValidate.address,
                    source:this.formValidate.source,
                    contactPhone:this.formValidate.contactPhone,
                    identityFront:this.formValidate.identityFront,
                    contactName:this.formValidate.contactName,
                    identityBack:this.formValidate.identityBack,
                    licenseNo:this.formValidate.licenseNo,
                    licenseImgUrl:this.formValidate.licenseImgUrl,
                    state:this.formValidate.state==true?10:20,
                    remark:this.formValidate.remark
                 })
                    .then((response)=>{
                        let data=response.data
                        if(data.code==1){
                             this.$Modal.success({
                        title: '系统提示：',
                        content: data.msg
                    })
                        this.formValidate.name='',
                       this.formValidate.address='',
                       this.formValidate.source='',
                        this.formValidate.contactPhone='',
                        this.formValidate.identityFront='',
                        this.formValidate.contactName='',
                        this.formValidate.identityBack='',
                        this.formValidate.licenseNo='',
                        this.formValidate.licenseImgUrl='',
                        this.formValidate.state=false,
                        this.formValidate.remark=''
                    this.handleReset(name);
                        }else {
                            this.$Modal.error({
                                title: "系统提示：",
                                content: data.msg
                            });
                        }
                    })
                    .catch((error)=>{
                        this.$Modal.error({
                            title: "系统提示：",
                            content: error
                        });
                    });
            } else {
                this.$Message.error('必填信息必须输入，否则不给予通过!');
    }
    })
    },
    editState : function(id, state) {
        axios.get('/a/tcolumn/tableColumn/editState', {
            params:{
                id : id,
                state : state
            }
        }).then((response)=>{
            if(response.data.code == '1'){
            this.$Message.success('操作成功');
            this.doQuery();
        }else{
            this.$Message.error(response.data.msg);
        }
    });
    },
    getAllTable : function (){
        axios.get('/a/tcolumn/tableColumn/listAllTable', {
            params:{}
        }).then((response)=>{
            if(response.data.code == '1'){
            this.tableNameList = response.data.data;
        }else{
            this.$Message.error(response.data.msg);
        }
    });
    },
    changeAddTable (value){
        this.maintainTableName = value;
        this.addColumn = [];
        this.removeColumn = [];
        axios.get('/a/tcolumn/tableColumn/listColumnByTable', {
            params:{
                tableName : value
            }
        }).then((response)=>{
            if(response.data.code == '1'){
            this.columnTargetKeys = response.data.columnRule;
            this.columnDataUnAdded = response.data.columnData;
        }else{
            this.$Message.error(response.data.msg);
        }
    });
    },
    addColumnTransferRender: function (item) {
        return item.label;
    },
    addColumnTransferChange : function (newTargetKeys, direction, moveKeys) {
        if(direction == 'left'){
            // 移出
            for(var idx in moveKeys){
                if(this.isInArray(this.columnTargetKeys, moveKeys[idx])){
                    this.removeColumn.push(moveKeys[idx]);
                }
                if(this.isInArray(this.addColumn, moveKeys[idx])){
                    this.removeByValue(this.addColumn, moveKeys[idx]);
                }
            }
        }else if(direction == 'right'){
            // 添加
            for(var idx in moveKeys){
                if(!this.isInArray(this.columnTargetKeys, moveKeys[idx])){
                    this.removeByValue(this.removeColumn, moveKeys[idx]);
                }
                if(!this.isInArray(this.addColumn, moveKeys[idx])){
                    this.addColumn.push(moveKeys[idx]);
                }
            }
        }
        this.columnTargetKeys = newTargetKeys;
    },
    saveColumnMaintain (){
        if(!this.maintainTableName){
            return;
        }
        axios.get('/a/tcolumn/tableColumn/addColumn', {
            params:{
                "tableName" : this.maintainTableName,
                "removeColumn" : this.removeColumn.join(','),
                "addColumn" : this.addColumn.join(',')
            }
        }).then((response)=>{
            if(response.data.code == '1'){
            this.removeColumn = [];
            this.addColumn = [];
            this.columnTargetKeys = [];
            this.addTableName = "";
            this.columnDataUnAdded = [];
            this.doQuery();
            this.$Message.success(response.data.msg);
        }else{
            this.$Message.error(response.data.msg);
        }
    });
    },
    isInArray (arr, val){
        return arr.indexOf(val)!=-1;
    },
    removeByValue (arr, val){
        for(var i=0; i<arr.length; i++) {
            if(arr[i] == val) {
                arr.splice(i, 1);
                break;
            }
        }
    }
},
mounted(){
    this.doQuery();
    this.getAllTable();
}
})