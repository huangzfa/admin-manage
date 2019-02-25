let tcolumn = new Vue({
    el : '#app',
    data : {
        queryOrderNo: '',//订单号
        name: '',
        examineState: '',//审批 状态
        //审批 状态列表
        examineList : [{
            value : "10",
            label : "通过"
        }, {
            value : "20",
            label : "需要人工复审"
        },{
            value : "30",
            label : "不通过"
        }],
        //订单号	用户姓名	手机号	场景	渠道	审批结果	审批时间	异步响应	异步响应时间	操作
        tColumnTitles : [
            {
                title: '订单号',
                key: 'id',
            },{
                title: '用户姓名',
                key: 'name',
            },{
                title: '手机号',
                key: 'phone',
            },{
                title: '场景',
                key: 'phone',
            },{
                title: '手机号',
                key: 'phone',
            },{
                title: '渠道',
                key: 'phone',
            },{
                title: '审批结果',
                key: 'phone',
            },{
                title: '异步响应',
                key: 'phone',
            },{
                title: '异步响应时间',
                key: 'phone',
            },{
                title: '操作',
                key: 'action',
                width: 150,
                align: 'center',
                render: (h, params) => {
                    return h('div', [
                        h('Button', {
                            props: {
                                type: 'success',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: () => {
                                tcolumn.aler();
                                }
                            }
                            }, '获取报告')
                    ]);
                }
            }],

            page : 1,
            size : 10,
            totalCnt : 0,
            tColumnDatas : [],
            scenes: [],
        },
methods : {
    doQuery: function () {
        this.$Loading.start();
        axios.get('/a/verifyRecord/verifyRecordList' ,{
            params : {
                /* queryOrderNo: this.queryOrderNo,
                 ruleName: this.ruleName,
                 ruleState: this.ruleState,
                 resultValue: this.resultValue,
                 page : this.page,
                 size : this.size*/
             }
        }).then((response)=>{
            this.tColumnDatas = response.data.data;
           // this.totalCnt = response.data.page.total;
        this.$Loading.finish();
    }).catch(function(error){
         this.$Loading.error();
        });
    },
    //场景名称
    querySceneList(){
        this.$Loading.start();
        axios.get('/a/tpp/querySceneList',{ params:{} }).then((response)=>{
            this.scenes = response.data.data;
            this.$Loading.finish();
        }).catch(function(error){
            this.$Loading.error();
        });
    },
    //重置搜索按钮
    queryReset: function () {
        this.queryOrderNo = "";
        this.rule_name = "";
        this.rule_state = "00";
        this.resultValue = "00";
        this.doQuery();
    },

    //分页
    handlePage: function (value) {
        this.page = value;
        this.doQuery();
    },
    aler(){
        window.open("/a/verifyRecord/report")
    }
},
    mounted(){
        console.log('----初始化会加载----')
        this.doQuery();
        this.querySceneList();
    }
})