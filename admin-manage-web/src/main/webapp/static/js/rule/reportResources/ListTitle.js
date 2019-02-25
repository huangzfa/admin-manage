// 贷后帮报告[对应]
var listTitle = [
    {
    titleKey: "user_basic",
    titleText: "用户基本信息",
    dataList: {
        age: "年龄",
        gender: "性别",
        birthday: "生日日期",
        idcard_validate: "身份证是否是有效身份证",
        idcard_province: "身份证户籍省份",
        idcard_city: "身份证户籍城市",
        idcard_region: "身份证户籍地区",
        phone_operator: "手机运营商",
        phone_province: "手机归属地省份",
        phone_city: "手机归属地城市",
        record_idcard_days: "身份证号记录天数",
        record_phone_days: "手机号记录天数",
        last_appear_idcard: "身份证最近出现时间",
        last_appear_phone: "手机号最近出现时间",
        used_idcards_cnt: "关联身份证数量",
        used_phones_cnt: "关联手机号数量"
    }
}, {
    titleKey: "risk_social_network",
    titleText: "社交风险点",
    dataList: {
        sn_score: "葫芦分",
        sn_order1_contacts_cnt: "直接联系人",
        sn_order1_blacklist_contacts_cnt: "直接联系人在黑名单中数量(直接黑人)",
        sn_order2_blacklist_contacts_cnt: "间接联系人在黑名单中数量(间接黑人)",
        sn_order2_blacklist_routers_cnt: "认识间接黑人的直接联系人个数",
        sn_order2_blacklist_routers_pct: "认识间接黑人的直接联系人比例",
    }
}, {
    titleKey: "risk_blacklist",
    titleText: "黑名单",
    dataList: {
        idcard_in_blacklist: "身份证是否命中黑名单",
        phone_in_blacklist: "手机号是否命中黑名单",
        in_court_blacklist: "是否命中法院黑名单",
        in_p2p_blacklist: "是否命中网贷黑名单",
        in_bank_blacklist: "是否命中银行黑名单",
        last_appear_idcard_in_blacklist: "最近该身份证出现在黑名单中时间",
        last_appear_phone_in_blacklist: "最近该手机号出现在黑名单中时间",
    }
}, {
    titleKey: "history_org",
    titleText: "历史机构类型",
    dataList: {
        online_installment_cnt: "线上消费分期出现次数",
        offline_installment_cnt: "线下消费分期出现次数",
        credit_card_repayment_cnt: "信用卡代还出现次数",
        payday_loan_cnt: "小额快速贷出现次数",
        online_cash_loan_cnt: "线上现金贷出现次数",
        offline_cash_loan_cnt: "线下现金贷出现次数",
        others_cnt: "其他",
    }
}, {
    titleKey: "history_search",
    titleText: "历史查询",
    dataList: {
        search_cnt: "历史查询总次数",
        search_cnt_recent_7_days: "最近7天查询次数",
        search_cnt_recent_14_days: "最近14天查询次数",
        search_cnt_recent_30_days: "最近30天查询次数",
        search_cnt_recent_60_days: "最近60天查询次数",
        search_cnt_recent_90_days: "最近90天查询次数",
        search_cnt_recent_180_days: "最近180天查询次数",
        org_cnt: "历史查询总机构数",
        org_cnt_recent_7_days: "最近7天查询机构数",
        org_cnt_recent_14_days: "最近14天查询机构数",
        org_cnt_recent_30_days: "最近30天查询机构数",
        org_cnt_recent_60_days: "最近60天查询机构数",
        org_cnt_recent_90_days: "最近90天查询机构数",
        org_cnt_recent_180_days: "最近180天查询机构数",
    }
}, {
    titleKey: "binding_idcards",
    titleText: "绑定身份证情况",
    dataList: {
        other_idcard: "绑定其他身份证号码",
        other_names_cnt: "此号码绑定其他姓名个数",
        search_orgs_cnt: "查询此身份证的机构数",
        idcard_validate: "身份证是否是有效身份证",
        idcard_province: "身份证户籍省份",
        idcard_city: "身份证户籍城市",
        idcard_region: "身份证户籍地区",
        idcard_age: "年龄",
        idcard_gender: "性别",
        last_appear_idcard: "最近此身份证出现时间",
    }
}, {
    titleKey: "binding_phones",
    titleText: "绑定号码情况",
    dataList: {
        other_phone: "绑定其他手机号码",
        other_names_cnt: "此号码绑定其他姓名个数",
        search_orgs_cnt: "查询此手机号的机构数",
        phone_operator: "手机运营商",
        phone_province: "手机归属地省份",
        phone_city: "手机归属地城市",
        last_appear_phone: "最近此手机号出现时间",
    }
}]

// 上树[对应]
var shangshuTitle = [
    {
    titleKey: "shuli_operator_basic",
    titleText: "基础信息",
    dataList: {
        "bizNo": "业务编号",
        "basicUserName": "姓名",
        "extendPhoneAge": "网龄",
        "gmtModified": "修改时间",
        "basicExpenditure": "当月消费(单位为分)",
        "gmtCreate": "创建时间",
        "extendJoinDt": "入网时间",
        "basicAllBonus": "累计积分（可以为0）",
        "extendCertifedStatus": "实名认证状态",
        "basicBalance": "余额（单位为分）",
        "basicPhoneNum": "号码",
        "extendBelongto": "归属地",
        "extendContactAddr": "联系地址"
    }
}, {
    titleKey: "shuli_operator_voice",
    titleText: "通话记录",
    dataList: {
        "gmtModified": "修改时间",
        "phoneNum": "号码",
        "voicePlace": "通话地",
        "gmtCreate": "创建时间",
        "voiceDuration": "通话时长（单位为秒）",
        "month": "语音账单月份",
        "voiceType": "通话类型",
        "voiceToNumber": "对方号码",
        "voiceDate": "时间",
        "voiceStatus": "通话状态",
        "bizNo": "业务编号"
    }
}, {
    titleKey: "shuli_operator_bill",
    titleText: "月账单信息",
    dataList: {
        "gmtModified": "修改时间",
        "billMonthOtherAmt": "其他费用（单位为分）",
        "gmtCreate": "创建时间",
        "billMonthAddedAmt": "增值业务费（单位为分）",
        "billMonthSmsAmt": "短彩信费（单位为分）",
        "billMonthAmt": "本月费用总额（单位为分）",
        "billMonthDate": "计费周期",
        "billMonthNetAmt": "上网费（单位为分）",
        "billMonthRepAmt": "代收费（单位为分）",
        "phoneNum": "号码",
        "month": "语音账单月份",
        "billMonthVocAmt": "语音通信费（单位为分）",
        "bizNo": "业务编号",
        "billMonthFixedAmt": "固定费用（单位为分）"
    }
}]
// 魔蝎[对应]
var moxieTitle = [
    {
        titleKey: "shuli_operator_basic",
        titleText: "魔蝎基础信息",
        dataList: {
            "bizNo": "魔蝎编号",
            "basicUserName": "魔蝎姓名",
            "extendPhoneAge": "魔蝎网龄",
            "gmtModified": "修改时间",
            "basicExpenditure": "当月消费(单位为分)",
            "gmtCreate": "魔蝎创建时间",
            "extendJoinDt": "入网时间",
            "basicAllBonus": "累计积分（可以为0）",
            "extendCertifedStatus": "实名认证状态",
            "basicBalance": "余额（单位为分）",
            "basicPhoneNum": "号码",
            "extendBelongto": "归属地",
            "extendContactAddr": "联系地址"
        }
    }, {
        titleKey: "shuli_operator_voice",
        titleText: "魔蝎记录",
        dataList: {
            "gmtModified": "修改时间",
            "phoneNum": "号码",
            "voicePlace": "通话地",
            "gmtCreate": "创建时间",
            "voiceDuration": "通话时长（单位为秒）",
            "month": "语音账单月份",
            "voiceType": "通话类型",
            "voiceToNumber": "对方号码",
            "voiceDate": "时间",
            "voiceStatus": "通话状态",
            "bizNo": "业务编号"
        }
    }, {
        titleKey: "shuli_operator_bill",
        titleText: "魔蝎账单信息",
        dataList: {
            "gmtModified": "修改时间",
            "billMonthOtherAmt": "其他费用（单位为分）",
            "gmtCreate": "创建时间",
            "billMonthAddedAmt": "增值业务费（单位为分）",
            "billMonthSmsAmt": "短彩信费（单位为分）",
            "billMonthAmt": "本月费用总额（单位为分）",
            "billMonthDate": "计费周期",
            "billMonthNetAmt": "上网费（单位为分）",
            "billMonthRepAmt": "代收费（单位为分）",
            "phoneNum": "号码",
            "month": "语音账单月份",
            "billMonthVocAmt": "语音通信费（单位为分）",
            "bizNo": "业务编号",
            "billMonthFixedAmt": "固定费用（单位为分）"
        }
    }]
// 法院报告[对应]
// 裁判文书(CPWS)
// 执行公告(ZXGG)
// 开庭公告(KTGG)
// 失信公告(SXGG)
// 法院公告(FYGG)
// 网贷黑名单(WDHMD)
// 案件流程信息(AJLC)
// 曝光台(BGT)
var courtTitle = [
    {
    titleKey: "cpwsArray",
    titleText: "裁判文书",
    dataList: {
        "caseNO": "案号",
        "id": "ID",
        "dataType": "类别",
        "title": "标题",
        "court": "法院名称",
        "litigants": "诉讼当事人集合",
        "recordTime": "立案时间",
        "content": "内容",
    }
}, {
    titleKey: "zxggArray",
    titleText: "执行公告",
    dataList: {
        "caseNO": "案号",
        "id": "ID",
        "dataType": "类别",
        "identificationNO": "身份证/组织机构代码",
        "title": "标题",
        "caseStatus": "1",
        "name": "范例",
        "court": "法院名称",
        "recordTime": "立案时间",
        "executionTarget": "执行标的",
        "content": "内容",
    }
}, {
    titleKey: "ktggArray",
    titleText: "开庭公告",
    dataList: {
        caseNO: "案号",
        id: "ID",
        recordTime: "开庭时间",
        content: "内容",
        court: "法院名称",
        title: "标题",
        caseCause: "案由",
        dataType: "类别",
    }
}, {
    titleKey: "sxggArray",
    titleText: "失信公告",
    dataList: {
        "caseNO": "案号",
        "court": "法院名称",
        "implementationStatus": "履行情况",
        "recordTime": "立案时间",
        "obligations": "生效法律文书确定的义务",
        "content": "内容",
        "dataType": "类别",
        "identificationNO": "身份证/组织机构代码",
        "evidenceCode": "依据案号",
        "specificCircumstances": "失信被执行人行为具体情形",
        "executableUnit": "做出执行依据单位",
        "name": "被执行人姓名",
        "age": "年龄",
        "province": "省份",
        "gender": "性别",
        "postTime": "发布时间"
    }
}, {
    titleKey: "fyggArray",
    titleText: "法院公告",
    dataList: {
        recordTime: "发布时间",
        announcementType: "公告类型",
        content: "公告内容",
        name: "当事人",
        court: "法院名称",
        dataType: "类别"
    }
}, {
    titleKey: "wdhmdArray",
    titleText: "网贷黑名单",
    dataList: {
        content: "内容",
        recordTime: "贷款时间",
        name: "姓名",
        dataType: "类别"
    }
}, {
    titleKey: "ajlcArray",
    titleText: "案件流程信息",
    dataList: {
        "caseNO": "案号",
        "court": "法院名称",
        "implementationStatus": "履行情况",
        "recordTime": "立案时间",
        "obligations": "生效法律文书确定的义务",
        "content": "内容",
        "dataType": "类别",
        "identificationNO": "身份证/组织机构代码",
        "evidenceCode": "依据案号",
        "specificCircumstances": "失信被执行人行为具体情形",
        "executableUnit": "做出执行依据单位",
        "name": "被执行人姓名",
        "age": "年龄",
        "province": "省份",
        "gender": "性别",
        "postTime": "发布时间"
    }
}, {
    titleKey: "bgtArray",
    titleText: "曝光台",
    dataList: {
        "content": "内容",
        "id": "ID",
        "dataType": "类别",
        "caseNO": "案号",
        "name": "范例",
        "court": "法院名称",
        "recordTime": "立案时间",
    }
}]

//上树统计信息
var shangshuDetailList = [
    {
    titleKey: "data",
    titleText: "",//上树统计信息 标题为空
    dataList: {
        /*"id": "主键",*/
        "orderNo": "订单号",
        "addTime": "存入时间",
        "phone": "手机号码",
        "realValid": "实名校验结果",
        "extendPhoneAge": "在网时长（单位为天）",
        "proportion1": "通话时长为整分的通话数/总通话数比例",
        "proportion2": "通话时长相同的最大记录数/总通话数比例",
        "proportion3": "通话时间段在晚上11:00至凌晨6:00的通话数/总通话数比例",
        "proportion4": "通话时长少于2分钟的通话数/总通话数比例",
        "proportion5": "通话时长相同的记录总数/总通话数比例",
        "proportion6": "主叫通话时长相同最大记录数/总通话数",
        "proportion7": "主叫通话时长相同总记录数/总通话数",
        "proportion8": "被叫通话时长相同最大记录数/总通话数",
        "proportion9": "被叫通话时长相同总记录数/总通话数",
        "noCallDayThirty": "无通话天数（30天）",
        "scaleNoCallThirty": "无通话天数占比（30天）",
        "noCallingDayThirty": "无呼出天数（30天）",
        "scaleNoCallingThirty": "无呼出占比（30天）",
        "callsCountThirty": "通话次数（30天）",
        "callsNumThirty": "通话号码数量（30天）",
        "callDurationThirty": "通话总时长，单位为秒（30天）",
        "callAvgDurationThirty": "通话平均时长，单位为秒（30天）",
        "callMatchNumThirty": "通话记录与通讯录名单匹配数量（30天）",
        "calledNumThirty": "被叫（呼入）号码总数（30天）",
        "calledCountThirty": "被叫（呼入）总次数（30天）",
        "calledDurationThirty": "被叫（呼入）总时长，单位为秒（30天）",
        "calledAvgDurationThirty": "被叫（呼入）平均时长，单位为秒（30天）",
        "calledMatchNumThirty": "被叫（呼入）记录与通讯录名单匹配数量（30天）",
        "callingNumThirty": "主叫（呼出）号码总数（30天）",
        "callingCountThirty": "主叫（呼出）总次数（30天）",
        "callingDurationThirty": "主叫（呼出）总时长，单位为秒（30天）",
        "callingAvgDurationThirty": "主叫（呼出）平均时长，单位为秒（30天）",
        "callingMatchNumThirty": "主叫（呼出）记录与通讯录名单匹配数量（30天）",
        "countDivThirty": "主叫（呼出）总次数/被叫（呼入）总次数（30天）",
        "durationDivThirty": "主叫（呼出）总时长/被叫（呼入）总时长（30天）",
        "realAmtThirty": "消费金额（30天）",
        "noCallDayNinety": "无通话天数（90天）",
        "scaleNoCallNinety": "无通话天数占比（90天）",
        "noCallingDayNinety": "无呼出天数（90天）",
        "scaleNoCallingNinety": "无呼出占比（90天）",
        "callsCountNinety": "通话次数（90天）",
        "callsNumNinety": "通话号码数量（90天）",
        "callDurationNinety": "通话总时长，单位为秒（90天）",
        "callAvgDurationNinety": "通话平均时长，单位为秒（90天）",
        "callMatchNumNinety": "通话记录与通讯录名单匹配数量（90天）",
        "calledNumNinety": "被叫（呼入）号码总数（90天）",
        "calledCountNinety": "被叫（呼入）总次数（90天）",
        "calledDurationNinety": "被叫（呼入）总时长，单位为秒（90天）",
        "calledAvgDurationNinety": "被叫（呼入）平均时长，单位为秒（90天）",
        "calledMatchNumNinety": "被叫（呼入）记录与通讯录名单匹配数量（90天）",
        "callingNumNinety": "主叫（呼出）号码总数（90天）",
        "callingCountNinety": "主叫（呼出）总次数（90天）",
        "callingDurationNinety": "主叫（呼出）总时长，单位为秒（90天）",
        "callingAvgDurationNinety": "主叫（呼出）平均时长，单位为秒（90天）",
        "callingMatchNumNinety": "主叫（呼出）记录与通讯录名单匹配数量（90天）",
        "countDivNinety": "主叫（呼出）总次数/被叫（呼入）总次数（90天）",
        "durationDivNinety": "主叫（呼出）总时长/被叫（呼入）总时长（90天）",
        "realAmtNinety": "消费金额（90天）",
        "noCallDayOneEighty": "无通话天数（180天）",
        "scaleNoCallOneEighty": "无通话天数占比（180天）",
        "noCallingDayOneEighty": "无呼出天数（180天）",
        "scaleNoCallingOneEighty": "无呼出占比（180天）",
        "callsCountOneEighty": "通话次数（180天）",
        "callsNumOneEighty": "通话号码数量（180天）",
        "callDurationOneEighty": "通话总时长，单位为秒（180天）",
        "callAvgDurationOneEighty": "通话平均时长，单位为秒（180天）",
        "callMatchNumOneEighty": "通话记录与通讯录名单匹配数量（180天）",
        "calledNumOneEighty": "被叫（呼入）号码总数（180天）",
        "calledCountOneEighty": "被叫（呼入）总次数（180天）",
        "calledDurationOneEighty": "被叫（呼入）总时长，单位为秒（180天）",
        "calledAvgDurationOneEighty": "被叫（呼入）平均时长，单位为秒（180天）",
        "calledMatchNumOneEighty": "被叫（呼入）记录与通讯录名单匹配数量（180天）",
        "callingNumOneEighty": "主叫（呼出）号码总数（180天）",
        "callingCountOneEighty": "主叫（呼出）总次数（180天）",
        "callingDurationOneEighty": "主叫（呼出）总时长，单位为秒（180天）",
        "callingAvgDurationOneEighty": "主叫（呼出）平均时长，单位为秒（180天）",
        "callingMatchNumOneEighty": "主叫（呼出）记录与通讯录名单匹配数量（180天）",
        "countDivOneEighty": "主叫（呼出）总次数/被叫（呼入）总次数（180天）",
        "durationDivOneEighty": "主叫（呼出）总时长/被叫（呼入）总时长（180天）",
        "realAmtOneEighty": "消费金额（180天）"
        /*"state": "状态",*/
       
    }
}]

// 同盾报告[对应]
// var tongdunTitle = [{
//     titleKey: "address_detect",
//     titleText: "归属地解析",
//     dataList: {
//         "mobile_address": "手机所属地",
//         "id_card_address": "身份证所属地"
//     }
// }, {
//     titleKey: "risk_items",
//     titleText: "贷前风险情况",
//     dataList: {
//         "risk_level": "风险等级",
//         "item_id": "检查项编号",
//         "item_name": "检查项名称",
//         "group": "检查项分组"
//     }
// }, ]