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
            align: 'center'
        }, {
            title : '表名',
            key : 'tbName',
            align: 'center'
        }, {
            title : '场景CODE',
            key : 'sceneCode',
            align: 'center'
        }, {
            title : '场景名',
            key : 'sceneName',
            align: 'center'
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
        axios.get('/a/scene/table/list', {
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
},
mounted(){
    this.doQuery();
    this.doSearchMch();
}
})