let tcolumn = new Vue({
	el : '#app',
	data : {
        loading1: false,
        servers:[],//下拉服务列表
        scenes: [],//下拉场景列表
        sceneCode: '',
        getTypes: [
            //获取方式：once 获取一次，every 每次获取，cycle 固定周期获取
            {key: '获取一次',val: 'once'},
            {key: '每次获取',val: 'every'},
            {key: '固定周期获取',val: 'cycle'},
            ],
        states:[
            {key: '启用',val: '10'},
            {key: '禁用',val: '20'},
        ],
        serviceSort: 10,
        tqSceneTpp:[{
            serviceSort: 10,
        }],
        //scene: 新增或者修改的提交对象
        tqTppScenes : {
            sceneCode: '',//
            tppServiceId: 0,
            getTpye: '',
            period: 0,//固定周期时值
            periodUnit: '',
            ortherFlag: false,
            ortherGetTpye: '',//其他商户数据获取方式
            ortherCycle: 0,
            ortherCycleUnit: false,
            state: '10',
            serviceSort: 99,
        },
        //固定单位
        periodUnits: [
            {key: '天',val: 'day'},
            {key: '小时',val: 'hour'},
            ],
        //是否获取其他商户数据
        ortherFlags: [
            {key: '是',val: true },
            {key: '否',val: false},
        ],
        page: 1,
        size: 10,
        total: 0,
        tppSceneModal: false,//modal框是否打开
        tppSceneModelTitle: '',//modal框标题
        modalState: true,//true 添加 false 编辑
        Datas: [],
        columnDatas : [{
            title:'场景名称',
            width: 120,
            key : 'sceneName',
           // align: 'center'
        }, {
			title : '服务名称',
			key : 'serviceName',
			width : 120
		},{
            title : '获取方式',
            key : 'getTpye',
            width : 120,
            render: (h, params) => {
                //获取方式：once获取一次，every每次获取，cycle固定周期获取
                if(params.row.getTpye == 'once'){
                    return h('p', '获取一次')
                }else if(params.row.getTpye == 'every'){
                    return h('p', '每次获取')
                }else if(params.row.getTpye == 'cycle'){
                    return h('p', '固定周期获取')
                }else{
                    return h('p', '未知')
                }
            }
        }, {
			title : '固定周期单位',
			key : 'periodUnit',
			width : 120,
                render: (h, params) => {
                    return h('p', params.row.period+':'+params.row.periodUnit)
                }
		},{
            title : '获取其他商户数据',
            key : 'ortherFlag',
            width : 120
        },
        {
            title : '其他商户数据获取方式',
                key : 'ortherGetTpye',
            width : 120,
            render: (h, params) => {
                //获取方式：once获取一次，every每次获取，cycle固定周期获取
                if(params.row.ortherGetTpye == 'once'){
                    return h('p', '获取一次')
                }else if(params.row.ortherGetTpye == 'every'){
                    return h('p', '每次获取')
                }else if(params.row.ortherGetTpye == 'cycle'){
                    return h('p', '固定周期获取')
                }else{
                    return h('p', '未知')
                }
            }
        },
        {
            title : '其他商户数据获取固定周期单位',
                key : 'ortherCycleUnit',
            width : 120,
            render: (h, params) => {
            return h('p', params.row.ortherCycle+':'+params.row.ortherCycleUnit)
        }
        },{
            title : '服务顺序',
                key : 'serviceSort',
                width : 120
        },
        {
            title : '状态',
                key : 'state',
            width : 120,
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
            width: 120,
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
                        tcolumn.edit(params.row);
                        }
                    }
                }, '编辑')/*,
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
                }, params.row.tppState == '10'?'禁用':'启用')*/
             ]);
             }
        }]
        },

	methods : {
      doQuery : function(){
			this.$Loading.start();
			axios.get('/a/tpp/queryTppSceneServerList', {
				params : {
                    sceneCode: this.sceneCode,
                    page : this.page,
                    size : this.size
				}
			}).then((response)=>{
				this.Datas = response.data.data;
                this.total = response.data.page.total;
				this.$Loading.finish();
			}).catch(function(error){
				this.$Loading.error();
			});
		},
        querySceneList(){
            this.$Loading.start();
            axios.get('/a/tpp/querySceneList',{
                params:{}
            }).then((response)=>{
            this.scenes = response.data.data;
            this.$Loading.finish();
        }).catch(function(error){
                this.$Loading.error();
            });
        },

        doGetServerName(){
            this.$Loading.start();
            axios.get('/a/tpp/queryTppServerList').then((response)=>{
             this.servers = response.data.data;
            this.$Loading.finish();
        }).catch(function(error){
                this.$Loading.error();
            });
        },

        submitOrEditTppScenc (){

            let path = this.modalState ? 'saveTppScene' : 'ediTppScene';
            var tqSceneTppService= this.tqTppScenes;
            this.$Loading.start();
            axios.post('/a/tpp/' + path, tqSceneTppService).then((response)=>{
                  if(0 == response.data.code){
                      this.$Message.error(response.data.msg);
                  }else{
                      this.$Message.success(response.data.msg);
                      this.doQuery();
                  }
              this.$Loading.finish();
              }).catch(function(error){
                  this.$Loading.error();
              });
        },
        addTppServerTable: function(){
            this.tppSceneModal = true;
            this.tppSceneModelTitle = '新增';
            this.modalState = true;
            this.cleanObj(this.tqTppScenes);
        },
        edit(row){
            this.tppSceneModal = true;
            this.tppSceneModelTitle = '修改';
            this.modalState = false;
            this.tqTppScenes = row;
            },
        //分页
        paging(value){
            this.page = value;
            this.doQuery();
        },
        //重置搜索按钮
		queryReset(){
            this.sceneCode = '';
			this.doQuery();
		},
        //清空对象值
        cleanObj(obj){
            for(var key in obj) {
                delete obj[key];
            }
        },
	    },
	mounted(){
		this.doQuery();
		this.doGetServerName();
		this.querySceneList();
	}
})