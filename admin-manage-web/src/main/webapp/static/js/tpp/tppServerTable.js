let tcolumn = new Vue({
	el : '#app',
	data : {
        loading1: false,
        servers:[],//下拉服务列表
        tables: [],//下拉表列表
        tqTppServiceTable: {
            id: 0,
            tppServiceId: 0,
            tableName: '',
        },
        
        page: 1,
        size: 10,
        total: 0,
        tppServerTableModal: false,
        tppServerTableModelTitle: '',
        tppServerTableModelState: true,//true 添加 false 编辑
        Datas: [],
        columnDatas : [{
            tital:'编号',
            width: 70,
            key : 'id',
            align: 'center'
        }, {
			title : '服务名称',
			key : 'tppServiceName',
			width : 100
		},{
            title : '表名称',
            key : 'tableName',
            width : 170
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
			axios.get('/a/tpp/queryTppTableList', {
				params : {
                    tppServiceId: this.tqTppServiceTable.tppServiceId,
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
        doGetServerName(){
            this.$Loading.start();
            axios.get('/a/tpp/queryTppServerList').then((response)=>{
             this.servers = response.data.data;
            this.$Loading.finish();
        }).catch(function(error){
                this.$Loading.error();
            });
        },
        doGetTableName(){
            this.$Loading.start();
            axios.post('/a/tcolumn/tableColumn/listAllTable',{
                params:{}
            }).then((response)=>{
                this.tables = response.data.data;
            this.$Loading.finish();
        }).catch(function(error){
                this.$Loading.error();
            });
        },
        submitOrEditTppServerTable (){
            let path = this.tppServerTableModelState ? 'saveTppServiceTable' : 'ediTppServiceTable';
                console.log( )
            this.$Loading.start();
              axios.get('/a/tpp/' + path, {
                  params : this.tqTppServiceTable,
              }).then((response)=>{
                  if(0 == response.data.code){
                      this.$Message.error(response.data.msg);
                  }else{
                      this.$Message.success(response.data.msg);
                      this.tqTppServiceTable.tppServiceId = 0;
                      this.tqTppServiceTable.tableName = '';
                      this.doQuery();
                  }
              this.$Loading.finish();
              }).catch(function(error){
                  this.$Loading.error();
              });
        },
        addTppServerTable: function(){
            this.tppServerTableModal = true;
            this.tppServerTableModelTitle = '新增';
            this.tppServerTableModelState = true;
        },
        edit(row){
            this.tppServerTableModal = true;
            this.tppServerTableModelTitle = '修改';
            this.tppServerTableModelState = false;
            this.tqTppServiceTable.id = row.id;
            this.tqTppServiceTable.tppServiceId = row.tppServiceId;
            this.tqTppServiceTable.tableName = row.tableName;
        },
        //分页
        paging(value){
            this.page = value;
            this.doQuery();
        },
        //重置搜索按钮
		queryReset(){
            this.tqTppServiceTable.tppServiceId = 0;
			this.doQuery();
		},
	    },
	mounted(){
		this.doQuery();
		this.doGetServerName();
		this.doGetTableName();
	}
})