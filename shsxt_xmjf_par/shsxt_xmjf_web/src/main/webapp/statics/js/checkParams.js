function checkParams(phone,imageCode) {
    if (isEmpty(phone)){
        layer.tips("手机号不能为空!", "#phone");
        return false;
    }
    var rag=/^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\d{8})$/;
    var flag=rag.test(phone);
    if (!flag){
        layer.tips("手机号错误!", "#phone");
        return false;
    }

    if (isEmpty(imageCode)){
        layer.tips("验证码不能为空!", ".validImg");
        return false;
    }
}