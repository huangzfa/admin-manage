let tcolumn = new Vue({
	el : '#app',
	data : {
        addTppModal: false,
        apiDetailModel: false,//查看model框
        serverConfigModel: false,//服务与配置模态框开关
        modalSubPathState: true,//服务与配置 修改 添加 后端路径
        tppName : "",
        tppState: "00",
        resultValue : "00",//审批结果 默认值
        /*tpp 状态 开始*/
        tppNameAdd: '',
        tppStateAdd: '',
        tppStates:[ {
            value:'10',text:"启用"
        },{
            value:'20',text:"禁用"
        }],

        /*tpp 状态 结束*/
		stateSearch : [ {
			value : "00",
			label : "全部"
		}, {
			value : "10",
			label : "启用"
		}, {
			value : "20",
			label : "禁用"
		}],

		tColumnTitles : [ {
            tital:'编号',
            width: 70,
            key : 'id',
            align: 'center'
        }, {
			title : '第三方名称',
			key : 'tppName',
			width : 100
		},{
			title : '状态',
			key : 'tppState',
            width : 100,
            render: (h, params) => {
                return h('div', [
                    h('p', {
                        style: {
                            marginRight: '5px',
                            color : params.row.state == '10'?'#5cadff':'#ccc'
                        }
                    },     params.row.tppState == 10 ? '启用' : '禁用')
                ]);
            }
		}, {
			title : '添加时间',
			key : 'addTime',
			width : 170
		},{
            title: '操作',
            key: 'action',
            width: 250,
            align: 'center',
            render: (h, params) => {
            return h('div', [
                h('Button', {
                    props: {
                        type: 'primary',
                        size: 'small'
                    },
                    style: {marginRight: '5px' },
                    on: {
                        click: () => {
                        tcolumn.apiDetail(params.row.id);
                        }
                    }
                }, '接口详情'),
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
                            tcolumn.isNotUsing(params.row.id,params.row.tppState);
                            }
                        }
                }, params.row.tppState == '10'?'禁用':'启用')
             ]);
             }
        }],
    //-----------------------------------接口详情列表
        columns1: [
            {
                title: '服务编号',
                key: 'id'
            },
            {
                title: '服务名称',
                key: 'serviceName'
            },
            {
                title: '服务唯一编码',
                key: 'serviceCode'
            },
            {
                title: '状态',
                key: 'serviceState',
                render: (h, params) => {
                return h('div', [
                    h('p', {
                        style: {
                            marginRight: '5px',
                            color : params.row.serviceState == '10'?'#5cadff':'#ccc'
                        }
                    },     params.row.serviceState == 10 ? '启用' : '禁用')
                ]);
                }
            },{
                title: '添加时间',
                key: 'addTime'
            },
            {
                title: '操作',
                    key: 'action',
                width: 250,
                align: 'center',
                render: (h, params) => {
                return h('div', [
                    h('Button', {
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {marginRight: '5px' },
                        on: {
                            click: () => {
                            tcolumn.serverEdit(params.row);
                            }
                        }
                    }, '编辑')
                ]);
                }
            }
        ],

    //--------------------------------新增三方数据服务
    index: 1,
    originCode: '',//修改时 后端对比是否有修改痕迹
    serverAndCfg: {
        tppId: 0,
        serviceName: '',
        serviceCode: '',
        serviceState: '10',
        tqTppServiceConfigs: [{
        key: '',
        val: '',
        index: 1,
    }],
   },



    modalTitle: '',//模态框名称
        tppPage : 1,
        tppSize : 10,
        tppTotal : 0,


        tppServerPage: 1,
        tppServerSize: 10,
        tppServerCount: 0,
        tppServerData: [],

        tColumnDatas : [],
        tableNameList: []

	},
	methods : {
      doQuery : function(){
			this.$Loading.start();
			axios.get('/a/tpp/queryTppList', {
				params : {
                    tppName: this.tppName,
                    tppState: this.tppState,
                    page : this.tppPage,
                    size : this.tppSize
				}
			}).then((response)=>{
				this.tColumnDatas = response.data.data;
                this.tppTotal = response.data.page.total;
				this.$Loading.finish();
			}).catch(function(error){
				this.$Loading.error();
			});
		},
        addTpp: function(){
            this.addTppModal = true;
        },
        submitTpp(){
            axios.get('/a/tpp/saveTpp', {
                params:{
                    tppState: this.tppStateAdd,
                    tppName: this.tppNameAdd
                }
            }).then((response)=>{
                if(0 == response.data.code){
                    this.$Message.error(response.data.msg);
                }else{
                    this.$Message.success('添加成功');
                }
                this.doQuery();
        });
            this.tppName = "";//搜索条件的服务名称
            this.tppState ="00";//搜索条件的状态
            this.tppNameAdd ='';//新增框中的服务名称
            this.tppStateAdd = '';//新增框中的状态
        },
        //重置搜索按钮
		queryReset(){
            this.tppName = "";
            this.tppState ="00";
			this.doQuery();
		},
        apiDetail : function(tppId){
		    this.serverAndCfg.tppId = tppId;
            this.queryTppServerByTppId();
            this.apiDetailModel = true;
        },
        queryTppServerByTppId : function(){
            axios.get('/a/tpp/queryTppServerByTppId', {
                params:{
                    tppId: this.serverAndCfg.tppId,
                    page: this.tppServerPage,
                    size: this.tppServerSize
                }
            }).then((response)=>{
                if(response.data.code == '1'){
                    this.tppServerData = response.data.data;
                    this.tppServerCount = response.data.page.total;
                }else{
                    this.$Message.error(response.data.msg);
                }
            });
        },
        //tpp分页
        tppPaging(value){
            this.tppPage = value;
            this.doQuery();
        },
        tppServerPaging(value){
            this.tppServerPage = value;
            this.queryTppServerByTppId();
        },
        ItemResultPage: function(value){
		    this.itemPage = value;
		    this.getAllTable();
        },
        //打开·修改服务与配置的modal框
        serverEdit(params){
            this.serverConfigModel = true;
            this.modalSubPathState = false;
            this.modalTitle = "修改数据服务配置";
            this.originCode = params.serviceCode;
            axios.get('/a/tpp/queryTppServerByTppServerId', {
                params:{ serverId: params.id }
            }).then((response)=>{
                if(response.data.code == '1'){
                this.serverAndCfg = response.data.data;
            }else{
                this.$Message.error(response.data.msg);
            }
        });
        },
        //打开添加 服务与配置model框
        addMoreDataServer: function(value){
		    this.serverConfigModel = true;
		    this.modalTitle = "添加数据服务配置";

            this.serverAndCf.serviceName  ='';
            this.serverAndCf.serviceCode  ='';
            this.serverAndCf.serviceState  ='10';
            this.serverAndCf.tqTppServiceConfigs  =[{  key: '', val: '', index: 1,}];
        },
        submitOrEditTppServerAndConfig (){
            let path = this.modalSubPathState ? 'saveTppService' : 'editTppService/'+this.originCode;
            let tqTppModel = this.serverAndCfg;//json对象
            var url = '/a/tpp/' + path ;
            this.$Loading.start();
            axios.post(url, tqTppModel).then((response)=>{
                if(0 == response.data.code){
                    this.$Message.error(response.data.msg);
                }else{
                    this.$Message.success(response.data.msg);
                    this.queryTppServerByTppId();
                }
            this.$Loading.finish();
            }).catch(function(error){
                this.$Loading.error();
            });
        },
        /*禁用启用*/
        isNotUsing(id,tppState){
            this.$Loading.start();
            axios.get('/a/tpp/tqTppUpState', {
                params : {
                    id: id,
                    tppState: tppState}
            }).then((response)=>{
                if(0 == response.data.code){
                this.$Message.error(response.data.msg);
            }else{
               this.$Message.success(response.data.msg);
            }
            this.$Loading.finish();
            this.doQuery();
        }).catch(function(error){
                this.$Loading.error();
            });
        },
        handleAdd(){//添加配置 key val input框
            this.index++;
            this.serverAndCfg.tqTppServiceConfigs.push({key: '',val: '',index: this.index, })
        },
        handleRemove(index){//删除配置 key val input框
            this.serverAndCfg.tqTppServiceConfigs.splice(index,1);
        },
        cleanObj(obj){
            for(var key in obj) {
                delete obj[key];
            }
        },
        cancel(){
            this.modalSubPathState =true;//编辑页面，esc后 防止 新增配置项 和 删除配置项消失
        },
	    },
	mounted(){
		console.log('----初始化会加载----')
		this.doQuery();
	}
})