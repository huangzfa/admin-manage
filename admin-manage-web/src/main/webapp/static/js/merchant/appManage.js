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
            title : '应用唯一key',
            key : 'appKey',
            width : 200
        }, {
            title : '应用名',
            key : 'appName',
            width : 250
        }, {
            title : '应用平台',
            key : 'appPlatform'
        }, {
            title : '加密方式',
            key : 'encryptType'
        }, {
            title : '商户md5参与签名key',
            key : 'appMd5Key'
        }, {
            title : '商户rsa公钥',
            key : 'appPublicKey'
        }, {
            title : '修改时间',
            key : 'modifyTime'
        }, {
            title : '状态',
            key : 'state',
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
    addColumn: [],
    removeColumn: [],
    page : 1,
    size : 10,
    totalCnt : 0,
    formValidate: {
    appName: '',
        appMd5Key: '',
        merId: '',
        encryptType:'',
        appPublicKey: '',
        appPlatform: ''
},
modal_loading: false,
ruleValidate: {
    appName: [
        { required: true, message: '请输入应用名称', trigger: 'blur' }
    ],
        appPlatform: [
        { required: true, message: '请输入应用平台', trigger: 'blur' },
    ],
        encryptType: [
        { required: true, message: '请选择加密方式', trigger: 'change' }
    ]
}
},
methods : {
    saveApp:function(){
        axios.post('/a/app/add', {
            appName: tcolumn.formValidate.appName,
            appMd5Key: tcolumn.formValidate.appMd5Key,
            encryptType: tcolumn.formValidate.encryptType,
            appPublicKey: tcolumn.formValidate.appPublicKey,
            appPlatform: tcolumn.formValidate.appPlatform
        })
            .then((response)=>{
                console.log(response);
            })
            .catch((error)=>{
                console.log(error);
            });
    },
    rowClassName (row, index) {
        if (row.state != '10') {
            return 'rule-table-disable';
        }else{
            return 'rule-table-able';
        }
    },
    doQuery : function() {
        this.$Loading.start();
        this.tableLoading = true;
        // var params = new URLSearchParams();
        // params.append('tableName', this.queryTableName);
        // params.append('tableState', this.queryTableState == '00' ? '' : this.queryTableState);
        // params.append('page', this.page);
        // params.append('size', this.size);
        // var url = "/a/tcolumn/tableColumn/list";
        // axios.post(url, params).then(function (response) {
        //     tcolumn.tableLoading = false;
        //     if(response.data.code == '1'){
        //         tcolumn.totalCnt = response.data.page.total;
        //         tcolumn.tColumnDatas = response.data.data;
        //         tcolumn.$Loading.finish();
        //     }else{
        //         tcolumn.$Loading.error();
        //     }
        // }).catch(function (error) {
        //     tcolumn.$Message.error(error);
        // });
        axios.get('/a/app/listApp', {})
            .then((response)=>{
                if(response.data.code=1){
            tcolumn.totalCnt = response.data.data.pageCount;
            tcolumn.tColumnDatas = response.data.data.data;
            tcolumn.$Loading.finish();
            tcolumn.tableLoading = false;
                }else{
            this.$Modal.error({
                title: '系统提示：',
                content: response.data.msg
            });
            tcolumn.$Loading.finish();
            tcolumn.tableLoading = false;
        }


    })
    .catch((error)=>{
            this.$Modal.error({
            title: '系统提示：',
            content: error
        });
        tcolumn.$Loading.error();
    })
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
    editState : function(id, state) {
        var params = new URLSearchParams();
        params.append('id', id);
        params.append('state', state);
        var url = "/a/tcolumn/tableColumn/editState";
        axios.post(url, params).then(function (response) {
            if(response.data.code == '1'){
                tcolumn.$Message.success('操作成功');
                tcolumn.doQuery();
            }else{
                tcolumn.$Message.error(response.data.msg);
            }
        }).catch(function (error) {
            tcolumn.$Message.error(error);
        });
    },
    getAllTable : function (){
        axios.post('/a/tcolumn/tableColumn/listAllTable', {
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
        var params = new URLSearchParams();
        params.append('tableName', value);
        var url = "/a/tcolumn/tableColumn/listColumnByTable";
        axios.post(url, params).then(function (response) {
            if(response.data.code == '1'){
                tcolumn.columnTargetKeys = response.data.saved;
                tcolumn.columnDataUnAdded = response.data.nosave;
            }else{
                tcolumn.$Message.error(response.data.msg);
            }
        }).catch(function (error) {
            tcolumn.$Message.error(error);
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
        var params = new URLSearchParams();
        params.append('tableName', this.maintainTableName);
        params.append('removeColumn', this.removeColumn.join(','));
        params.append('addColumn', this.addColumn.join(','));
        var url = "/a/app/listApp";
        axios.post(url, params).then(function (response) {
            if(response.code == '1'){
                tcolumn.removeColumn = [];
                tcolumn.addColumn = [];
                tcolumn.columnTargetKeys = [];
                tcolumn.addTableName = "";
                tcolumn.columnDataUnAdded = [];
                tcolumn.doQuery();
                tcolumn.$Message.success(response.data.msg);
            }else{
                tcolumn.$Message.error(response.data.msg);
            }
        }).catch(function (error) {
            tcolumn.$Message.error(error);
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
    },
    handleReset:function(name,model) {
        tcolumn[model]=false;
        this.$refs[name].resetFields();
    }
},
mounted(){
    this.doQuery();
    this.getAllTable();
}
})