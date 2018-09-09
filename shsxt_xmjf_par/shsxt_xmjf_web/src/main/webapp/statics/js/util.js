

function sendMsg(id,url){

    //获取短信验证码
        var phone = $("#phone").val();
        var imageCode = $("#code").val();
        var checkParams1 = checkPhone(phone);
        
        if (checkParams1 != false) {
            var checImageCode1 = checImageCode(imageCode);
            if (checImageCode1!=false){

                var _this = $("#"+id);
            //发送ajax到后台请求转发短信验证
                $.ajax({
                    type: 'post',
                    url:url,
                    data: {
                        mobile: phone,
                        imageCode: imageCode,
                        type: 2
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            time(_this);
                        } else {
                            layer.tips(data.msg, "#"+id);

                        }
                    }

                });
            }
        }



}

//验证码冷却
var wait = 180;

function time(o) {
    if (wait == 0) {
        o.removeAttr("disabled");
        o.val('获取验证码');
        o.css("color", '#ffffff');
        o.css("background", "#fcb22f");
        wait = 180;
    } else {
        o.attr("disabled", true);
        o.css("color", '#fff');
        o.css("background", '#ddd');
        o.val("重新发送(" + wait + "s)");
        wait--;
        setTimeout(function () {
            time(o)
        }, 1000)
    }
}


function checkPhone(phone) {
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


}
function checImageCode(imageCode) {
    if (isEmpty(imageCode)) {
        layer.tips("验证码不能为空!", ".validImg");
        return false;
    }
}