let tcolumn = new Vue({
	el : '#app',
	data : {
        modal1: false,//查看model框
        queryOrderNo : "",
        ruleName: "",//规则名称
        ruleState: "00",//规则状态
        resultValue : "00",//审批结果 默认值
		stateList : [ {
			value : "00",
			label : "全部"
		}, {
			value : "10",
			label : "启用"
		}, {
			value : "20",
			label : "禁用"
		},
            {
                value : "30",
                label : "试运行"
        }],
		examineList : [{
            value : "00",
            label : "全部"
        }, {
            value : "10",
            label : "通过"
        }, {
            value : "20",
            label : "需要人工复审"
        },
        {
            value : "30",
            label : "不通过"
		}],
		tColumnTitles : [ {
            tital:'编号',
            width: 70,
            key : 'ruleId',
            align: 'center'
        }, {
			title : '订单号',
			key : 'tqOrderNo',
			width : 100
		}, {
			title : '规则名称',
			key : 'ruleName',
			width : 300
		}, {
			title : '规则类型',
			key : 'ruleType',
            width : 100,
            render: (h, params) => {
			    //规则类型: 0=普通规则;1=复杂规则;2=评分规则(人审、分层规则)
                if(params.row.ruleType == 0){
                    return h('p', '普通规则')
                }else if(params.row.ruleType == 1){
                    return h('p', '复杂规则')
                }else{
                    return h('p', '评分规则')
                }
            }
		}, {
			title : '得分',
			key : 'score',
			width : 70
		},{
            title: '匹配结果',
            key: 'result',
            width: 150,
            render:(h,params)=>{
                //结果模式时： 10通过，20人审，30不通过
                if(params.row.result == 10){
                    return h('p', '通过')
                }else if(params.row.result == 20){
                    return h('p', '人审')
                }else{
                    return h('p', '不通过')
                }
            }
        },{
        title: '规则状态',
            key: 'ruleState',
            width: 150,
            render:(h,params)=>{
                //规则状态，10启用，20禁用，99试运行
                if(params.row.ruleState == 10){
                    return h('p', '启用')
                }else if(params.row.ruleState == 20){
                    return h('p', '禁用')
                }else{
                    return h('p', '试运行')
                }
            }
        },{
         title : '添加时间',
         key : 'addTime',
         width : 200
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
                        tcolumn.edit(params.row.ruleId,params.row.tqOrderNo);
                        }
                    }
                }, '查看')
             ]);
             }
        }],
    //-----------------------------------查看
        columns1: [
            {
                title: '订单号',
                key: 'tq_order_no'
            },
            {
                title: '表中文名称',
                key: 'tb_name'
            },
            {
                title: '对比值',
                key: 'cf_value'
            },
            {
                title: '是否命中',
                key: 'val_state'
            },
            {
                title: '添加时间',
                key: 'add_time'
            }
        ],
        itemPage: 1,
        itemSize: 10,
        itemCount: 0,
        id: 0,
        tqOrderNo: '',
        ruleItemResultData: [],

        page : 1,
        size : 10,
        totalCnt : 0,
        tColumnDatas : [],
        tableNameList: []

	},
	methods : {
		doQuery : function(){
			this.$Loading.start();
			axios.get('/a/rule/ruleMatchingResultList', {
				params : {
                    queryOrderNo: this.queryOrderNo,
                    ruleName: this.ruleName,
                    ruleState: this.ruleState,
                    resultValue: this.resultValue,
                    page : this.page,
                    size : this.size
				}
			}).then((response)=>{
				this.tColumnDatas = response.data.data;
                this.totalCnt = response.data.page.total;
				this.$Loading.finish();
			}).catch(function(error){
				this.$Loading.error();
			});
		},
        //重置搜索按钮
		queryReset : function(){
            this.queryOrderNo= "";
            this.rule_name= "";
            this.rule_state= "00";
            this.resultValue= "00";
			this.doQuery();
		},
        edit : function(id,tqOrderNo){
		    this.id = id;
		    this.tqOrderNo = tqOrderNo;
            this.getAllTable();
            this.modal1 = true;
        },
        getAllTable : function (){
            axios.get('/a/rule/watchRuleItem', {
                params:{
                    ruleId: this.id,
                    orderNo: this.tqOrderNo,
                    page: this.itemPage,
                    size: this.itemSize
                }
            }).then((response)=>{
                console.log(response.data)
                if(response.data.code == '1'){
                    this.ruleItemResultData = response.data.data;
                    this.itemCount = response.data.page.total;
                }else{
                    this.$Message.error(response.data.msg);
                }
            });
        },
        //分页
        handlePage :function(value){
            this.page = value;
            this.doQuery();
        },
        ItemResultPage: function(value){
		    this.itemPage = value;
		    this.getAllTable();
        }
	},
	mounted(){
		console.log('----初始化会加载----')
		this.doQuery();
	}
})