package com.duobei.common.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @类描述： 借款状态和账期状态 枚举
 * @author
 * @注意：本内容仅限于杭州蒲公英数据科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum BorrowAndBillStatus {
	//	// 借款状态【0:申请/未审核,1:已结清,2:打款中,3:打款失败,4:关闭,5:已经打款/待还款】
	//【APPLY:申请/未审核，WAITTRANSED:待打款，TRANSEDFAIL:打款失败,TRANSEDING:打款中 , TRANSED:已经打款/待还款,CLOSED:关闭,FINSH:已结清】',
	BORROW_APPLY(0, "APPLY"),
    BORROW_TRANSED(5,"TRANSED"),
    BORROW_CLOSED(4, "CLOSED"),
    BORROW_TRANSEDFAIL(3, "TRANSEDFAIL"),
    BORROW_TRANSEDING(2, "TRANSEDING"),
    BORROW_FINISH(1, "FINISH"),

    //0:未还款,1:已还款, 2:还款中,4:关闭(针对于退款成功账单)
    BILL_NO_REPAYMENT(0,"未还款"),
    BILL_REPAYMENT_SUCCESS(1,"已还款"),
    BILL_PART_REPAYMENT(3,"部分还款"),
    BILL_CLOSED(4,"账单关闭"),
    BILL_WAIT_REPAYMENT(2,"还款中")
    ;

    private Integer code;
    private String name;

    private static Map<Integer,BorrowAndBillStatus> codeRoleTypeMap = null;

    BorrowAndBillStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String findNameByCode(Integer code) {
        for (BorrowAndBillStatus roleType : BorrowAndBillStatus.values()) {
            if (roleType.getCode().intValue() == code.intValue()) {
                return roleType.getName();
            }
        }
        
        return null;
    }
    
    
    public static Integer findCodeByName(String name) {
        for (BorrowAndBillStatus roleType : BorrowAndBillStatus.values()) {
            if (roleType.getName().equals(name)) {
                return roleType.getCode();
            }
        }
        return null;
    }

    
    public static Map<Integer,BorrowAndBillStatus> getCodeRoleTypeMap(){
        if(codeRoleTypeMap != null && codeRoleTypeMap.size() > 0){
            return codeRoleTypeMap;
        }
        codeRoleTypeMap = new HashMap<Integer, BorrowAndBillStatus>();
        for(BorrowAndBillStatus item:BorrowAndBillStatus.values()){
            codeRoleTypeMap.put(item.getCode(), item);
        }
        return codeRoleTypeMap;
    }

	
    

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
