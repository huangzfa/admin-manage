var vue = new Vue({
	el : '#app',
	data : {
		userName : "",
		password : ""
	},
	methods : {
		doLogin : function() {
			if (!this.userName) {
				this.$Notice.error({
					title : '请输入账号',
					desc : ''
				});
				return;
			}
			if (!this.password) {
				this.$Notice.error({
					title : '请输入密码',
					desc : ''
				});
				return;
			}
			axios.get('/login', {
				params : {
					username: this.userName,
					password: this.password
				}
			}).then(function(response) {
				console.log(response.data);
				if(response.data.code == 1){
					window.location.href=response.data.mainUrl;
				}else{
					if( typeof(response.data.loginFirst)!="undefined" ){
                        modifyPwd(response.data.loginFirst.loginName,response.data.loginFirst.opId);
					}else{
                        vue.$Notice.error({
                            title : response.data.msg,
                            desc : ''
                        });
					}
				}
			});
		}
	}
})