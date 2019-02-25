let sceneRule = new Vue({
    el : '#app',
    data : {
        sceneCode : null,
        merId : null,
        appId : null,
        queryMchAll: [],
        queryAppAll: [],
        querySceneAll: [],
        queryRuleAll: [],
        searchMchAll: [],
        searchAppAll: [],
        searchSceneAll: [],
        formCreate:{
            id: null,
            merId: null,
            appId: null,
            sceneName: null,
            sceneType: null,
            sceneVersion: null,
            sceneCode: null,
            sceneDesc: null,
        },
        tableLoading : false,
        tColumnTitles : [ {
            type: 'index',
            width: 70,
            align: 'center'
        }, {
            title : '商户名',
            key : 'merName',
            align: 'center',
            width : 100
        }, {
            title : '应用名称',
            key : 'appName',
            align: 'center',
            width : 100
        }, {
            title : '场景CODE',
            key : 'sceneCode',
            align: 'center',
            width : 100
        }, {
            title : '场景名称',
            key : 'sceneName',
            align: 'center',
            width : 100
        }, {
            title : '规则名称',
            key : 'ruleName',
            align: 'center',
            width : 100
        }, {
            title : '规则排序',
            key : 'ruleSort',
            align: 'center',
            width : 100
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
                        sceneRule.handleEdit(params.row);
                        }
                        }
                }, '编辑'),
                h('Button', {
                    props: {
                        type: 'error',
                        size: 'small'
                    },
                    on: {
                        click: () => {
                        sceneRule.editState(params.row.id);
                            }
                        }
                }, '禁用' ),
                ]);
            }
        },
    ],
    tColumnDatas : [],
    addModelSceneRule : false,
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
        axios.get('/a/scene/rule/list', {
            params : {
                sceneCode : this.sceneCode,
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
        this.sceneCode=null;
        this.appId= null;
        this.merId=null;
        this.doQuery();
    },
    pageChange : function(value){
        this.page = value;
        this.doQuery();
    },
    handleCreate (){
        this.addModelSceneRule= true;
    },
    doSearchMch: function(){
        axios.get('/a/scene/queryMchList', {}).then((response)=>{
            if(response.data.code == 1){
            this.searchMchAll = response.data.list;
            if(response.data.list.length > 0){
                this.doSearchApp(response.data.list[0].value)
            }
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doSearchApp: function(value){
        axios.get('/a/scene/queryAppList', {
            params:{merId: value},
        }).then((response)=>{
            if(response.data.code == 1){
            this.searchAppAll = response.data.list;
            if(response.data.list.length > 0){
                this.doSearchScene(value, response.data.list[0].value)
            }
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doSearchScene: function(merId, appId){
        axios.get('/a/scene/rule/querySceneList', {
            params:{ merId:merId, appId:appId},
        }).then((response)=>{
            if(response.data.code == 1){
            this.searchSceneAll = response.data.list;
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doQueryMch: function(){
        axios.get('/a/scene/queryMchList', {}).then((response)=>{
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
        if(response.data.code == 1){
            this.queryAppAll = response.data.list;
            if(response.data.list.length > 0){
                this.doQueryScene(value, response.data.list[0].value)
            }
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doQueryScene: function(merId,appId){
        axios.get('/a/scene/rule/querySceneList', {
            params:{ merId:merId, appId:appId},
        }).then((response)=>{
        if(response.data.code == 1){
            this.querySceneAll = response.data.list;
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    doQueryRule: function(){
        axios.get('/a/scene/rule/queryRuleList', {
        }).then((response)=>{
            if(response.data.code == 1){
            this.queryRuleAll = response.data.list;
        }
    }).catch(function(error){
            this.$Loading.error();
        });
    },
    handleAdd: function(name){
        this.$refs[name].validate((valid) => {
            if (valid) {
                delete this.formCreate.items
                axios.get('/a/scene/rule/addSceneRule', {
                    params: this.formCreate,
                }).then((response)=>{
                    this.tableLoading = false;
                if(response.data.code == 1){
                    this.$Message.success('成功!');
                    this.modelClean('formCreate');
                    this.doQuery()
                }
            }).catch(function(error){
                    this.$Loading.error();
                });
            } else {
                this.$Message.error('失败!');
            }
    });
    },
    handleUpdate: function(name){
        this.$refs[name].validate((valid) => {
            if (valid) {
                delete this.formCreate.items
                axios.get('/a/scene/rule/updateSceneRule', {
                        params: this.formCreate,
                    }
                ).then((response)=>{
                    this.tableLoading = false;
                if(response.data.code == 1){
                    this.$Message.success('成功!');
                    this.modelClean('formCreate');
                    this.doQuery();
                }
            }).catch(function(error){
                    this.$Loading.error();
                });
            } else {
                this.$Message.error('失败!');
    }
    });
    },
    handleEdit (params){
        this.formCreate.id = params.id;
        this.formCreate.ruleSort= params.ruleSort;
        this.updateModelScene= true;
    },
    modelClean (name){
        this.name = {};
    },
    editState (item){
        axios.get('/a/scene/rule/deleteSceneRule', {
                params: {id: item},
            }
        ).then((response)=>{
            this.tableLoading = false;
        if(response.data.code == 1){
            this.$Message.success('成功!');
            this.modelClean('formCreate');
            this.doQuery();
        }
        }).catch(function(error){
            this.$Loading.error();
        });
    }
},
mounted(){
    this.doQuery();
    this.doQueryMch();
    this.doSearchMch();
    this.doQueryRule();

}
})