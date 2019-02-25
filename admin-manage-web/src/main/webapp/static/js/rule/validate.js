function isvalidUsername(str) {
    const valid_map = ['superAdmin', 'editor', 'admin']
    return valid_map.indexOf(str.trim()) >= 0
}

/* 合法uri*/
function validateURL(textval) {
    const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
    return urlregex.test(textval)
}

/* 小写字母*/
function validateLowerCase(str) {
    const reg = /^[a-z]+$/
    return reg.test(str)
}

function validataUserName(str) {
    const reg = /^\s*$/
    return reg.test(str)
}

/* 大写字母*/
function validateUpperCase(str) {
    const reg = /^[A-Z]+$/
    return reg.test(str)
}

/* 大小写字母*/
function validatAlphabets(str) {
    const reg = /^[A-Za-z]+$/
    return reg.test(str)
}

/* 大写字母A到Z自增长
 * str1 为1级组合编号
 * str2 为2级组合编号
 * */
function fromCharCode(str1, str2) {
    if (str2 < 10) {
        return String.fromCharCode(65 + str1) + '0' + str2
    } else {
        return String.fromCharCode(65 + str1) + str2
    }
}

/**
 *判断字母大小写，小写返回0，大写返回对应数字，A-1, B-2......
 * **/
function charCodeAt(str) {
    if (str.charCodeAt(0) > 96) {
        return 0
    } else if (str.charCodeAt(0) < 90) {
        return str.charCodeAt(0) - 64
    }
}

function isCharCodeAt(str) {
    if (str.charCodeAt(0) > 96) {
        return '简单规则'
    } else if (str.charCodeAt(0) < 90) {
        return str.charCodeAt(0) - 64 + '级组合'
    }
}
//将拿到的数据转为想要的
function getInfoList(data) { //点击编辑时，将数据传入此方法
    var ruleConfig = data
    let getSimpleAndScoreRulesRuleConfig = []
    for (let i = 0; i < ruleConfig.length; i++) {
        var ruleId = ruleConfig[i].level - 1
        if (!getSimpleAndScoreRulesRuleConfig[ruleId]) {
            var arr = []
            arr.push(ruleConfig[i])
            getSimpleAndScoreRulesRuleConfig[ruleId] = arr
        } else {
            getSimpleAndScoreRulesRuleConfig[ruleId].push(ruleConfig[i])
        }
    }
    var getConfig1 = []
    for (let i = 0; i < getSimpleAndScoreRulesRuleConfig.length; i++) {
        let data1 = getSimpleAndScoreRulesRuleConfig[i]
        let arr = {
            level: i + 1
        }
        let config = []
        let list = []
        for (let j = 0; j < data1.length; j++) {
            let data2 = data1[j]
            if (data2.ruleList.length > 0) {
                let ruleList = data2.ruleList.split(',')
                let list1 = []
                for (let i = 0; i < ruleList.length; i++) {
                    let list2 = {
                        info: [],
                        label: isCharCodeAt(ruleList[i]),
                        level: charCodeAt(ruleList[i]),
                        value: {
                            val: ruleList[i]
                        }
                    }
                    list1.push(list2)
                }
                list = list1
            }
            let data3 = {
                id:data2.id,
                groupRuleId: data2.ruleGroupCode,
                itemRuleId: data2.ruleGroupCode,
                level: data2.level,
                list: list,
                relation: String(data2.relation)
            }
            config.push(data3)
        }
        for (let n = 0; n < ruleConfig.length; n++) {
            var arr1 = []
            for (let m = 0; m <= ruleConfig[i].level - 1; m++) {
                let newData = {
                    level: m
                }
                arr1.push(newData)
            }
        }
        arr.ruleConfig = config
        arr.ruleNameLevel = arr1
        getConfig1.push(arr)
    }
    return getConfig1
}
//向服务器发送复杂规则的数据时
function sendData(setData) {
    var data = setData

    let configList =  []

    var ruleConfig1 = []
    for (let i = 0; i < data.length; i++) {
        var arr1 = data[i].ruleConfig
        for (let j = 0; j < arr1.length; j++) {
            ruleConfig1.push(arr1[j])
        }
    }
    var ruleConfig2 = []
    for (let i = 0; i < ruleConfig1.length; i++) {
        var data = ruleConfig1[i]
        var list = ruleConfig1[i].list
        var arr = {
            groupRuleId: data.groupRuleId,
            itemRuleId: data.itemRuleId,
            level: data.level,
            id:data.id?data.id:'',
            relation: data.relation
        }
        var arr1 = []
        for (let j = 0; j < list.length; j++) {
            var value = list[j].value.val
            arr1.push(value)
        }
        arr.ruleList = arr1.join(',')

        ruleConfig2.push(arr)
    }
    for (let i = 0; i < ruleConfig2.length; i++) {
        configList.push(ruleConfig2[i])
    }
    return configList
}

function tbOrcol(tab,list){ //循环取表字段
    for(let i =0;i<tab.length;i++){
        let data = tab[i]
        data.type = '10'
        data.tbName = data.ctables[0]
        data.colName = data.ctables[1]
        list.forEach((item, index) =>{
            if(data.tbName == item.value){
                data.tbComment = item.label
                item.children.forEach((items, indexs)=>{
                    if(data.colName == items.value){
                        data.colComment = items.label
                        data.colType = items.type
                    }
                })
            }
        })
    }
}