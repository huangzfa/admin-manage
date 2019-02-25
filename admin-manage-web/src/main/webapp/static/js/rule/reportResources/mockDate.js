//规则报告
Mock.mock('/modules/api/report/rule/engine.htm', {
	"msg": "请求成功",
	"code": 200,
	"data": [{
		"id": 2,
		"orderNo": "36652112543",
		"ruleId": 1,
		"rule": "",
		"score": 11,
		"result": "10",
		"ruleCount": 0,
		"handlerCount": 0,
		"hitCount": 0,
		"ruleState": "10",
		"addTime": "2017-03-15 20:54:15",
		"ruleName": "注册-评分模式",
		"ruleType": "10",
		"details": [{
			"id": 4,
			"orderNo": "36652112543",
			"ruleId": 1,
			"score": 0,
			"rule": "年龄=18",
			"result": "N",
			"addTime": "2017-03-15 20:54:15"
		}, {
			"id": 5,
			"orderNo": "36652112543",
			"ruleId": 1,
			"score": 11,
			"rule": "年龄>18",
			"result": "Y",
			"addTime": "2017-03-15 20:54:15"
		}, {
			"id": 6,
			"orderNo": "36652112543",
			"ruleId": 1,
			"score": 0,
			"rule": "年龄<18",
			"result": "N",
			"addTime": "2017-03-15 20:54:15"
		}]
	}, {
		"id": 3,
		"orderNo": "36652112543",
		"ruleId": 2,
		"result": "30",
		"ruleCount": 0,
		"handlerCount": 0,
		"hitCount": 0,
		"ruleState": "10",
		"addTime": "2017-03-15 20:54:15",
		"ruleName": "注册-结果模式",
		"ruleType": "20",
		"details": [{
			"id": 7,
			"orderNo": "36652112543",
			"ruleId": 2,
			"score": 0,
			"rule": "年龄<=and<18,50",
			"result": "N",
			"resultType": "30",
			"addTime": "2017-03-15 20:54:15"
		}]
	}]
})

// 同盾报告
Mock.mock('/modules/api/report/tongdun.htm', {
	"message": "请求成功",
	"res": {
		"success": true,
		"risk_items": {
			"message": "请求成功",
			"res": {
				"success": true,
				"risk_items": [{
					"risk_level": "high",
					"item_id": 743348,
					"item_name": "身份证格式校验错误",
					"group": "个人基本信息核查"
				}, {
					"risk_level": "medium",
					"item_detail": {
						"discredit_times": 1,
						"overdue_details": [{
							"overdue_amount": 23232,
							"overdue_day": 100,
							"overdue_count": 5
						}],
						"type": "discredit_count"
					},
					"item_id": 743410,
					"item_name": "手机号命中信贷逾期名单",
					"group": "不良信息扫描"
				}, {
					"risk_level": "low",
					"item_detail": {
						"frequency_detail_list": [{
							"data": [
								"杭州市余※※※※※※※※※※※※※※※※※※※※-211"
							],
							"detail": "3个月身份证关联家庭地址数：1"
						}, {
							"data": [
								"12※※51@163.com",
								"te※※17@163.com",
								"te※※47@126.com",
								"ce※※i4@gmail.com",
								"te※※24@126.com",
								"te※※37@126.com",
								"te※※39@126.com",
								"te※※※66@foxmail.com",
								"ce※※※21@gmail.com",
								"te※※36@126.com",
								"t※※※7@qq.com",
								"ce※※※18@gmail.com",
								"te※※35@126.com",
								"ex※※※le@qq.com",
								"ce※※i6@gmail.com",
								"ce※※i1@163.com",
								"te※※25@126.com",
								"te※※22@126.com",
								"te※※※22@126.com",
								"ce※※※17@gmail.com"
							],
							"detail": "3个月身份证关联邮箱数：20"
						}, {
							"data": [
								"13122446688",
								"131※※※※8493",
								"138※※※※1635",
								"138※※※※3858",
								"186※※※※※※555",
								"139※※※※8493",
								"138※※※※0321",
								"186※※※※5555",
								"152※※※※8222"
							],
							"detail": "3个月身份证关联手机号数：9"
						}],
						"type": "frequency_detail"
					},
					"item_id": 743464,
					"item_name": "3个月内身份证关联多个申请信息",
					"group": "客户行为检测"
				}, {
					"risk_level": "low",
					"item_detail": {
						"frequency_detail_list": [{
							"data": [
								"330100190001010001",
								"4114※※※※※※※※※※2345",
								"3704※※※※※※※※※※1915",
								"5325※※※※※※※※※※9692",
								"2131※※※※※※※※※※※※3213",
								"1422※※※※※※※※※※1827",
								"6402※※※※※※※※※※6519",
								"4452※※※※※※※※※※4577",
								"4290※※※※※※※※※※0069",
								"5101※※※※※※※※※※6483",
								"5001※※※※※※※※※※※0135",
								"3501※※※※※※※※※※1773"
							],
							"detail": "3个月手机号关联身份证数：12"
						}],
						"type": "frequency_detail"
					},
					"item_id": 743466,
					"item_name": "3个月内申请信息关联多个身份证",
					"group": "客户行为检测"
				}, {
					"risk_level": "high",
					"item_detail": {
						"platform_detail_dimension": [{
							"count": 6,
							"detail": [
								"互联网金融门户:1",
								"小额贷款公司:2",
								"P2P网贷:1",
								"银行小微贷款:1",
								"信息中介:1"
							],
							"dimension": "借款人手机详情"
						}, {
							"count": 6,
							"detail": [
								"互联网金融门户:1",
								"小额贷款公司:2",
								"P2P网贷:1",
								"银行小微贷款:1",
								"信息中介:1"
							],
							"dimension": "借款人身份证详情"
						}],
						"platform_detail": [
							"互联网金融门户:1",
							"小额贷款公司:2",
							"P2P网贷:1",
							"银行小微贷款:1",
							"信息中介:1"
						],
						"platform_count": 6,
						"type": "platform_detail"
					},
					"item_id": 743498,
					"item_name": "7天内申请人在多个平台申请借款",
					"group": "多平台借贷申请检测"
				}, {
					"risk_level": "medium",
					"item_detail": {
						"platform_detail_dimension": [{
							"count": 15,
							"detail": [
								"一般消费分期平台:1",
								"互联网金融门户:1",
								"融资租赁:3",
								"小额贷款公司:4",
								"P2P网贷:4",
								"银行小微贷款:1",
								"信息中介:1"
							],
							"dimension": "借款人手机详情"
						}, {
							"count": 15,
							"detail": [
								"一般消费分期平台:1",
								"互联网金融门户:1",
								"融资租赁:3",
								"小额贷款公司:4",
								"P2P网贷:4",
								"银行小微贷款:1",
								"信息中介:1"
							],
							"dimension": "借款人身份证详情"
						}],
						"platform_detail": [
							"一般消费分期平台:1",
							"互联网金融门户:1",
							"融资租赁:3",
							"小额贷款公司:5",
							"P2P网贷:4",
							"银行小微贷款:1",
							"信息中介:1"
						],
						"platform_count": 16,
						"type": "platform_detail"
					},
					"item_id": 743500,
					"item_name": "1个月内申请人在多个平台申请借款",
					"group": "多平台借贷申请检测"
				}, {
					"risk_level": "medium",
					"item_detail": {
						"platform_detail_dimension": [{
							"count": 23,
							"detail": [
								"互联网金融门户:2",
								"一般消费分期平台:2",
								"融资租赁:3",
								"小额贷款公司:6",
								"P2P网贷:5",
								"大型消费金融公司:1",
								"银行小微贷款:1",
								"厂商汽车金融:2",
								"信息中介:1"
							],
							"dimension": "借款人手机详情"
						}, {
							"count": 22,
							"detail": [
								"互联网金融门户:2",
								"一般消费分期平台:4",
								"融资租赁:3",
								"小额贷款公司:4",
								"P2P网贷:4",
								"大型消费金融公司:1",
								"银行小微贷款:1",
								"厂商汽车金融:2",
								"信息中介:1"
							],
							"dimension": "借款人身份证详情"
						}],
						"platform_detail": [
							"互联网金融门户:2",
							"一般消费分期平台:4",
							"融资租赁:3",
							"小额贷款公司:7",
							"P2P网贷:5",
							"大型消费金融公司:1",
							"银行小微贷款:1",
							"厂商汽车金融:2",
							"信息中介:1"
						],
						"platform_count": 26,
						"type": "platform_detail"
					},
					"item_id": 743502,
					"item_name": "3个月内申请人在多个平台申请借款",
					"group": "多平台借贷申请检测"
				}],
				"address_detect": {
					"mobile_address": "上海市",
					"id_card_address": "浙江省杭州市"
				},
				"report_id": "ER2017032915122415226132",
				"final_score": 100,
				"final_decision": "Reject",
				"report_time": 1490771545000,
				"application_id": "17032915122448995D05A6DDB5C4C384",
				"apply_time": 1490771544000
			},
			"orderNo": "201703200442278121",
			"code": 200
		},
		"address_detect": {
			"mobile_address": "上海市",
			"id_card_address": "浙江省杭州市"
		},
		"report_id": "ER2017032915122415226132",
		"final_score": 100,
		"final_decision": "Reject",
		"report_time": 1490771545000,
		"application_id": "17032915122448995D05A6DDB5C4C384",
		"apply_time": 1490771544000
	},
	"orderNo": "201703200442278121",
	"code": 200
})

// 贷后帮报告
Mock.mock('/modules/api/report/hulu.htm', {
	"message": "请求成功",
	"res": {
		"data": {
			"user_basic": {
				"last_appear_phone": "",
				"birthday": "1990-03-27",
				"phone_operator": "中国移动",
				"last_appear_idcard": "2016-06-03",
				"gender": "男",
				"record_phone_days": 0,
				"idcard_city": "邵阳市",
				"idcard_region": "武冈市",
				"used_phones_cnt": 2,
				"idcard_province": "湖南省",
				"phone_province": "上海市",
				"idcard_validate": 1,
				"used_idcards_cnt": 1,
				"phone_city": "上海市",
				"record_idcard_days": 291,
				"age": 27
			},
			"risk_social_network": {
				"sn_order2_blacklist_routers_cnt": "",
				"sn_order2_blacklist_contacts_cnt": "",
				"sn_order1_blacklist_contacts_cnt": "",
				"sn_order1_contacts_cnt": "",
				"sn_order2_blacklist_routers_pct": "",
				"sn_score": ""
			},
			"binding_idcards": [],
			"update_time": 1489996565163,
			"binding_phones": [{
				"last_appear_phone": "2016-06-03",
				"phone_province": "河北省",
				"phone_operator": "中国移动",
				"other_names_cnt": 0,
				"search_orgs_cnt": 1,
				"phone_city": "邯郸市",
				"other_phone": "151****0000"
			}],
			"user_name": "邓昭荣",
			"risk_blacklist": {
				"last_appear_idcard_in_blacklist": "",
				"in_court_blacklist": false,
				"in_p2p_blacklist": false,
				"idcard_in_blacklist": false,
				"phone_in_blacklist": false,
				"in_bank_blacklist": false,
				"last_appear_phone_in_blacklist": ""
			},
			"user_phone": "18817240593",
			"history_org": {
				"offline_cash_loan_cnt": 0,
				"online_cash_loan_cnt": 0,
				"online_installment_cnt": 0,
				"payday_loan_cnt": 0,
				"credit_card_repayment_cnt": 0,
				"offline_installment_cnt": 0,
				"others_cnt": 0
			},
			"user_idcard": "430581199003271778",
			"history_search": {
				"search_cnt_recent_180_days": 0,
				"search_cnt_recent_7_days": 0,
				"org_cnt_recent_180_days": 0,
				"search_cnt_recent_90_days": 0,
				"org_cnt_recent_60_days": 0,
				"search_cnt_recent_30_days": 0,
				"search_cnt_recent_14_days": 0,
				"org_cnt_recent_14_days": 0,
				"org_cnt_recent_30_days": 0,
				"org_cnt_recent_7_days": 0,
				"org_cnt_recent_90_days": 0,
				"org_cnt": 0,
				"search_cnt_recent_60_days": 0,
				"search_cnt": 0
			},
			"srid": "ab560034-0d42-11e7-a838-525400bb256f-850"
		}
	},
	"orderNo": "201703212088674019",
	"code": 200
});

// 上树报告
Mock.mock('/modules/api/report/shangshu.htm', {
	"code": "200",
	"message": "查询成功！",
	"res": {
		"call_top": [{
			"times": 19,
			"callNumber": "13606657781"
		}, {
			"times": 15,
			"callNumber": "13515867170"
		}, {
			"times": 6,
			"callNumber": "10086"
		}, {
			"times": 5,
			"callNumber": "13757623983"
		}, {
			"times": 4,
			"callNumber": "13003605836"
		}, {
			"times": 4,
			"callNumber": "13516829581"
		}, {
			"times": 4,
			"callNumber": "15372015912"
		}, {
			"times": 3,
			"callNumber": "95508"
		}, {
			"times": 3,
			"callNumber": "17757452100"
		}, {
			"times": 2,
			"callNumber": "17858835166"
		}],
		"shuli_operator_bill": [{
			"gmtModified": "2017-03-16 16:03:55",
			"billMonthOtherAmt": 0,
			"gmtCreate": "2017-03-16 16:03:55",
			"billMonthAddedAmt": 0,
			"billMonthSmsAmt": 110,
			"billMonthAmt": 2635,
			"billMonthDate": "2017-03-01-2017-03-31",
			"billMonthNetAmt": 0,
			"billMonthRepAmt": 0,
			"phoneNum": "15858268791",
			"month": "2017-03-01 00:00:00",
			"billMonthVocAmt": 0,
			"bizNo": "201703162011900880",
			"billMonthFixedAmt": 2525
		}, {
			"gmtModified": "2017-03-16 16:03:55",
			"billMonthOtherAmt": 0,
			"gmtCreate": "2017-03-16 16:03:55",
			"billMonthAddedAmt": 0,
			"billMonthSmsAmt": 170,
			"billMonthAmt": 4970,
			"billMonthDate": "2016-10-01-2016-10-31",
			"billMonthNetAmt": 0,
			"billMonthRepAmt": 0,
			"phoneNum": "15858268791",
			"month": "2016-10-01 00:00:00",
			"billMonthVocAmt": 0,
			"bizNo": "201703162011900880",
			"billMonthFixedAmt": 4800
		}],
		"authcoll_msg": [{
			"errorMessage": "",
			"dataSource": "OPERATOR",
			"status": "NORMAL",
			"orgCode": "BD20161103184010",
			"data": [{
				"gmtModified": "2017-03-16 16:03:55",
				"basicExpenditure": 2635,
				"gmtCreate": "2017-03-16 16:03:55",
				"extendJoinDt": "2015-01-02 13:48:28",
				"basicAllBonus": 931,
				"extendCertifedStatus": "已登记",
				"basicBalance": 30526,
				"basicPhoneNum": "15858268791",
				"extendBelongto": "浙江杭州",
				"extendContactAddr": "浙江省宁波市首南街道",
				"extendPhoneAge": "2年2个月",
				"bizNo": "201703162011900880",
				"basicUserName": "**青"
			}],
			"bizType": "BIZ_TYPE",
			"bizNo": "201703162011900880"
		}],
		"shuli_operator_voice": [{
			"gmtModified": "2017-03-16 16:03:55",
			"phoneNum": "15858268791",
			"voicePlace": "杭州",
			"gmtCreate": "2017-03-16 16:03:55",
			"voiceDuration": 111,
			"month": "2017-03-01 00:00:00",
			"voiceType": "被叫",
			"voiceToNumber": "13003605836",
			"voiceDate": "2017-03-02 19:51:08",
			"voiceStatus": "本地",
			"bizNo": "201703162011900880"
		}, {
			"gmtModified": "2017-03-16 16:03:55",
			"phoneNum": "15858268791",
			"voicePlace": "杭州",
			"gmtCreate": "2017-03-16 16:03:55",
			"voiceDuration": 184,
			"month": "2017-03-01 00:00:00",
			"voiceType": "被叫",
			"voiceToNumber": "13003605836",
			"voiceDate": "2017-03-02 19:53:17",
			"voiceStatus": "本地",
			"bizNo": "201703162011900880"
		}],
		"shuli_operator_basic": [{
			"gmtModified": "2017-03-16 16:03:55",
			"basicExpenditure": 2635,
			"gmtCreate": "2017-03-16 16:03:55",
			"extendJoinDt": "2015-01-02 13:48:28",
			"basicAllBonus": 931,
			"extendCertifedStatus": "已登记",
			"basicBalance": 30526,
			"basicPhoneNum": "15858268791",
			"extendBelongto": "浙江杭州",
			"extendContactAddr": "浙江省宁波市首南街道",
			"extendPhoneAge": "2年2个月",
			"bizNo": "201703162011900880",
			"basicUserName": "**青"
		}]
	},
	"orderNo": "201703292064618248",
	"sign": "04b4de486ef990e863a04fdbaeb257764fd082e0fb1be12f87ce4d5745c4e525",
	"timestamp": "1490783280069"
})

// 法院报告
Mock.mock('/modules/api/report/court.htm', {
	"message": "请求成功",
	"res": {
		"fyggArray": [],
		"zxggResultSize": 4,
		"ktggResultSize": 0,
		"sxggArray": [{
			"caseNO": "(2016)浙0604执888号",
			"court": "上虞市人民法院",
			"implementationStatus": "全部未履行",
			"recordTime": "2016年02月02日",
			"obligations": "支付款项110000元",
			"content": "...10000元 37 范例...",
			"id": "",
			"dataType": "SXGG",
			"identificationNO": "330682********1234",
			"evidenceCode": "(2015)绍虞商初字第00279号",
			"specificCircumstances": "其他有履行能力而拒不履行生效法律文书确定义务",
			"executableUnit": "绍兴上虞法院",
			"name": "范例",
			"age": 37,
			"province": "上海",
			"gender": "女",
			"postTime": "2016年08月07日"
		}, {
			"caseNO": "(2015)绍虞执民字第01921号",
			"court": "上虞市人民法院",
			"implementationStatus": "全部未履行",
			"recordTime": "2015年07月09日",
			"obligations": "　支付款项90000元",
			"content": "...90000元 36 范例...",
			"id": "",
			"dataType": "SXGG",
			"identificationNO": "330682********1234",
			"evidenceCode": "(2015)浙绍商终字第00691号",
			"specificCircumstances": "他有履行能力而拒不履行生效法律文书确定义务",
			"executableUnit": "绍兴上虞法院",
			"name": "范例",
			"age": 36,
			"province": "上海",
			"gender": "女",
			"postTime": "2015年12月23日"
		}, {
			"caseNO": "(2010)绍虞执民字第01471号",
			"court": "上虞市人民法院",
			"implementationStatus": "全部未履行",
			"recordTime": "2010年04月18日",
			"obligations": "　要求执行借款和诉讼费共936990元及逾期债务利息",
			"content": "...逾期债务利息 35 范例...",
			"id": "",
			"dataType": "SXGG",
			"identificationNO": "330682********1234",
			"evidenceCode": "（2010）绍虞商初字第155号",
			"specificCircumstances": "其他有履行能力而拒不履行生效法律文书确定义务",
			"executableUnit": "绍兴市上虞市人民法院",
			"name": "范例",
			"age": 35,
			"province": "上海",
			"gender": "女",
			"postTime": "2014年03月06日"
		}],
		"ktggArray": [],
		"identityCard": "330682197909271028",
		"fyggResultSize": 0,
		"bgtResultSize": 1,
		"cpwsResultSize": 1,
		"wdhmdArray": [],
		"ajlcArray": [{
			"content": "...0****1234 范例...",
			"id": "",
			"dataType": "AJLC",
			"status": "审理中",
			"caseNO": "（2015）浙绍商终字第00691号",
			"name": "范例",
			"court": "绍兴市中级人民法院",
			"recordTime": "2015年05月13日"
		}],
		"zxggArray": [{
			"content": "...0****1234 范例...",
			"id": "",
			"dataType": "ZXGG",
			"identificationNO": "330682********1234",
			"title": "范例",
			"caseStatus": "1",
			"caseNO": "(2016)浙0604执****号",
			"name": "范例",
			"court": "上虞市人民法院",
			"recordTime": "2016年02月02日",
			"executionTarget": 110000
		}, {
			"content": "...330682********1234...",
			"id": "",
			"dataType": "ZXGG",
			"identificationNO": "330682********1234",
			"title": "范例",
			"caseStatus": "0",
			"caseNO": "(2016)浙0604执****号",
			"name": "范例",
			"court": "上虞市人民法院",
			"recordTime": "2016年02月02日",
			"executionTarget": 110000
		}, {
			"content": "...330682********1234...",
			"id": "",
			"dataType": "ZXGG",
			"identificationNO": "范例",
			"title": "范例",
			"caseStatus": "1",
			"caseNO": "(2015)绍虞执民字第****号",
			"name": "范例",
			"court": "上虞市人民法院",
			"recordTime": "2015年07月09日",
			"executionTarget": 90000
		}, {
			"content": "...0****1234 范例...",
			"id": "",
			"dataType": "ZXGG",
			"identificationNO": "330682********1234",
			"title": "范例",
			"caseStatus": "1",
			"caseNO": "(2010)绍虞执民字第****号",
			"name": "范例",
			"court": "上虞市人民法院",
			"recordTime": "2010年04月18日",
			"executionTarget": 936990
		}],
		"checkStatus": "查询成功",
		"bgtArray": [{
			"content": "姓名：范例 证件号码：3...",
			"id": "",
			"dataType": "BGT",
			"caseNO": "（2010）绍虞执民字第****号",
			"name": "范例",
			"court": "绍兴市上虞区人民法院",
			"recordTime": "2010年04月18日"
		}],
		"name": "范例",
		"pageInfo": {
			"total": "10",
			"pages": "1",
			"pageSize": "20",
			"pageNum": "1"
		},
		"wdhmdResultSize": 0,
		"sxggResultSize": 3,
		"ajlcResultSize": 1,
		"cpwsArray": [{
			"content": "...兴市上虞区人民法院 范例 330682********1234...",
			"id": "",
			"dataType": "CPWS",
			"title": "范例",
			"caseNO": "（2015）绍虞执民字第****-2号",
			"court": "绍兴市上虞区人民法院",
			"litigants": [],
			"recordTime": "2015年12月10日"
		}]
	},
	"orderNo": "201703170932918905",
	"code": 200
})