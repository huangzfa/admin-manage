let rule = new Vue({
	el : '#app',
	data : {
		queryRuleName : "",
		queryRuleState : "00",
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
		tableLoading : false,
		tColumnTitles : [ {
            type: 'index',
            width: 70,
            align: 'center'
        }, {
			title : '规则类型',
			key : 'tbName',
			width : 200
		}, {
			title : '规则名称',
			key : 'tbComment',
			width : 250
		}, {
			title : '版本',
			key : 'version',
			width : 70
		}, {
			title : '规则项数量',
			key : 'columnListStr'
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
        addModelBaseRule : true,
        addModelScoreRule : false,
        addModelComplexRule : false,
        value1 : 1,
        index: 1,
        formDynamic: {
            items: [
                {
                    value: '',
                    index: 1,
                    status: 1
                }
            ]
        },
        data2: [{
            value: 'zhejiang',
            label: '浙江',
            children: [{
                value: 'hangzhou',
                label: '杭州',
                children: [{
                    value: 'xihu',
                    label: '西湖'
                }]
            }]
        }, {
            value: 'jiangsu',
            label: '江苏',
            disabled: true,
            children: [{
                value: 'nanjing',
                label: '南京',
                children: [{
                    value: 'zhonghuamen',
                    label: '中华门'
                }]
            }]
        }],
        formValidate: {
            name: '',
            mail: '',
            city: '',
            gender: '',
            interest: [],
            date: '',
            time: '',
            desc: ''
        },
        ruleValidate : {
        	name: [
        	    { required: true, message: 'The name cannot be empty', trigger: 'blur' }
            ]
        },
        page : 1,
        size : 10,
        totalCnt : 0
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
			alert("query");
		},
		queryReset : function(){
			this.queryRuleName = "";
			this.queryRuleState = "00";
			this.doQuery();
		},
		pageChange : function(value){
			this.page = value;
			this.doQuery();
		},
		handleSubmit (name) {
            this.$refs[name].validate((valid) => {
                if (valid) {
                    this.$Message.success('Success!');
                } else {
                    this.$Message.error('Fail!');
                }
            })
        },
        handleReset (name) {
            this.$refs[name].resetFields();
        },
        handleAdd () {
            this.index++;
            this.formDynamic.items.push({
                value: '',
                index: this.index,
                status: 1
            });
        },
        handleRemove (index) {
            this.formDynamic.items[index].status = 0;
        }
	},
	mounted(){
	}
})