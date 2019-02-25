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
			title : '表名',
			key : 'tbName',
			width : 200
		}, {
			title : '表注释',
			key : 'tbComment',
			width : 250
		}, {
			title : '字段详情',
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
        totalCnt : 0
	},
	methods : {
		rowClassName (row, index) {
            if (row.state != '10') {
                return 'rule-table-disable';
            }else{
            	return 'rule-table-able';
            }
        },
		doQuery : function(){
			this.$Loading.start();
			this.tableLoading = true;
			var params = new URLSearchParams();
			params.append('tableName', this.queryTableName);
			params.append('tableState', this.queryTableState == '00' ? '' : this.queryTableState);
			params.append('page', this.page);
			params.append('size', this.size);
			var url = "/a/tcolumn/tableColumn/list";
			axios.post(url, params).then(function (response) {
				tcolumn.tableLoading = false;
				if(response.data.code == '1'){
					tcolumn.totalCnt = response.data.page.total;
					tcolumn.tColumnDatas = response.data.data;
					tcolumn.$Loading.finish();
				}else{
					tcolumn.$Loading.error();
				}
			}).catch(function (error) {
				tcolumn.$Message.error(error);
			});
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
			var url = "/a/tcolumn/tableColumn/saveColumn";
			axios.post(url, params).then(function (response) {
				if(response.data.code == '1'){
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
		}
	},
	mounted(){
		this.doQuery();
		this.getAllTable();
	}
})