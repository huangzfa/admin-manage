$(function(jQuery){
	jQuery.validator.addMethod("isPhone", function(value,element) {
		var length = value.length;
		var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
		var tel = /^\d{3,4}-?\d{7,9}$/;
		return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));
	}, "无效的电话号码");
	jQuery.validator.addMethod("alnum", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	}, "只能包括英文字母和数字");
	jQuery.validator.addMethod("alnumAndCn", function(value, element) {
		var reg =  /^[0-9a-zA-Z\u4E00-\u9FA5_]+$/;
		return this.optional(element) || reg.test(value);
	}, "只能包括中文字、英文字母、数字和下划线");
	// 身份证号码验证
	jQuery.validator.addMethod("isIdCardNo", function (value, element) {
		var idCard = /^\d{15}(\d{2}[0-9xX])?$/;
		return this.optional(element) || (idCard.test(value));
	}, "无效的身份证号码");
});