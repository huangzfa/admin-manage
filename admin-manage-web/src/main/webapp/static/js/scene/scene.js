let scene = new Vue({
    el : '#app',
    data : {
        sceneName : null,
        merId : null,
        appId : null,
        queryMchAll : [],
        queryAppAll : [],
        queryRangeAll : [],
        querySceneTypeAll : [],
        formCreate:{
            id: null,
            merId: null,
            appId: null,
            sceneName: null,
            sceneType: null,
            sceneVersion: null,
            rangeId: null,
            sceneCode: null,
            sceneDesc: null,
        },
        tableLoading : false,
        showRange : false,
        tColumnTitles : [ {
            type: 'index',
            width: 70,
            align: 'center'
        }, {
            title : '商户名称',
            key : 'merName',
            align: 'center',
            width : 100
        }, {
            title : '应用名称',
            key : 'appName',
            align: 'center',
            width : 100
        }, {
            title : '场景名称',
            key : 'sceneName',
            align: 'center',
            width : 250
        }, {
            title : '场景类型',
            key : 'sceneTypeName',
            align: 'center',
            width : 100
        }, {
            title : '场景CODE',
            key : 'sceneCode',
            align: 'center',
            width : 100
        }, {
            title : '场景描述',
            key : 'sceneDesc',
            align: 'center',
            width : 250
        }, {
            title : '状态',
            key : 'isDelete',
            width : 70,
            align: 'center',
            render: (h, params) => {
            return h('div', [
                h('p', {
                    style: {
                        marginRight: '5px',
                    }
                }, params.row.isDelete == '0'?'启用':'禁用')
            ]);
            }
        }, {
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
                            scene.handleEdit(params.row);
                        }
                        }
                }, '编辑'),
                h('Button', {
                    props: {
                        type: params.row.isDelete == '0'?'error':'success',
                        size: 'small'
                    },
                    on: {
                        click: () => {
                            scene.editState(params.row.id, params.row.isDelete == '0' ? '20' : '10');
                            }
                        }
                }, params.row.isDelete == '0'?'禁用':'启用' ),
                ]);
            }
        },
    ],
    tColumnDatas : [],
    addModelScene : false,
    updateModelScene : false,
    page : 1,
    size : 10,
    totalCnt : 0,
    },
methods : {
    rowClassName (row, index) {
        if (row.state != '10') {
            return 'rule-disable';
        }else{
            return 'rule-able';
        }
    },
    doQuery : function(){
        this.$Loading.start();
        this.tableLoading = true;
        axios.get('/a/scene/list', {
            params : {
                sceneName : this.sceneName,
                merId : this.merId,
                appId : this.appId,
                page : this.page,
                size : this.size
            }
        }).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.totalCnt = response.data.page.total;
            this.tColumnDatas = response.data.data;
            this.$Loading.finish();
        }else{
            this.$Loading.error();
        }
        }).catch(function(error){
            this.tableLoading = false;
            this.$Loading.error();
        });
    },
    queryReset : function(){
        this.sceneName = null,
        this.merId = null,
        this.appId = null,
        this.doQuery();
    },
    pageChange : function(value){
        this.page = value;
        this.doQuery();
    },
    handleCreate (){
        this.addModelScene= true;
    },
    doQueryMch: function(){
        axios.get('/a/scene/queryMchList', {}).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.queryMchAll = response.data.list;
            if(response.data.list.length > 0){
                this.doQueryApp(response.data.list[0].value)
            }
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doQueryApp: function(value){
        axios.get('/a/scene/queryAppList', {
            params:{merId: value},
        }).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.queryAppAll = response.data.list;
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doQuerySceneType: function(value){
        axios.get('/a/scene/querySceneTypeList', {
            params:{merId: value},
        }).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.querySceneTypeAll = response.data.list;
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    handleAdd: function(name){
        this.$refs[name].validate((valid) => {
            if (valid) {
                delete this.formCreate.items
                axios.get('/a/scene/addScene', {
                    params: this.formCreate,
                }).then((response)=>{
                    this.tableLoading = false;
                if(response.data.code == 1){
                    this.modelClean('formCreate');
                    this.$Message.success('添加成功!');
                    this.doQuery()
                }
            }).catch(function(error){
                    this.$Loading.error();
                });
            } else {
                this.$Message.error('错误!');
            }
    });
    },
    handleUpdate: function(name){
        this.$refs[name].validate((valid) => {
            if (valid) {
                delete this.formCreate.items
                axios.get('/a/scene/updateScene', {
                        params: this.formCreate,
                    }
                ).then((response)=>{
                    this.tableLoading = false;
                if(response.data.code == 1){
                    this.$Message.success('修改成功!');
                    this.modelClean('formCreate');
                    this.doQuery();
                }
            }).catch(function(error){
                    this.$Loading.error();
                });
            } else {
                this.$Message.error('错误!');
    }
    });
    },
    handleEdit (params){
        this.formCreate.id = params.id;
        this.formCreate.sceneName= params.sceneName;
        this.formCreate.sceneType=params.sceneType;
        this.formCreate.sceneVersion= params.sceneVersion;
        this.formCreate.sceneDesc= params.sceneDesc;
        this.updateModelScene= true;
    },
    modelClean (name){
        this.name = {};
    },
    editState (item){
        axios.get('/a/scene/deleteScene', {
                params: {id: item},
            }
        ).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.$Message.success('Success!');
            this.doQuery();
        }
        }).catch(function(error){
            this.$Loading.error();
        });
    },
    doShowRange (item){
        if (item == 'range'){
            this.showRange = true;
        } else {
           delete this.formCreate.rangeId
            this.showRange = false;
        }
    }
},
mounted(){
    this.doQuery();
    this.doQueryMch();
    this.doQuerySceneType();
}
})