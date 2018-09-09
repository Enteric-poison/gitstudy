//页面加载完成之后给图片绑定点击事件

$(function () {
    $(".validImg").click(function () {
        $(this).attr("src", ctx + "/image?time=" + new Date())
    });

    //获取短信验证码
    $("#clickMes").click(function () {
        var phone = $("#phone").val();
        var imageCode = $("#code").val();
        checkPhone(phone)
        if (checkParams1 != false) {
            var checImageCode1 = checImageCode(imageCode);
            if (checImageCode1!=false) {
                var _this = $("#clickMes");
                // time(_this);
                //发送ajax到后台请求转发短信验证
                $.ajax({
                    type: 'post',
                    url: ctx + '/sendMsg',
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
                            layer.tips(data.msg, "#clickMes");

                        }
                    }

                });
            }
        }
    });


    //手机号注册验证
    $("#register").click(function () {

        var phone = $("#phone").val();
        var imageCode = $("#code").val();
        var checkParams1 = util(phone);
        var checImageCode1 = checImageCode(imageCode);
        if (checkParams1 != false && checImageCode1!=false) {


            var password = $("#password").val();

            var verification = $("#verification").val();

            if (isEmpty(verification)) {
                layer.tips("手机验证码不能为空!", "#clickMes");
                return;
            }
            if (isEmpty(password)) {
                layer.tips("密码码不能为空!", "#password");
                return;
            }

        }
        $.ajax({
            type:'post',
            url:ctx+'/user/doRegister',
            data:{
                mobile:phone,
                password:password,
                code:verification
            },
            dataType:'json',
            success:function (data) {
                if (data.code==200){
                    //成功
                    layer.alert(data.msg, {icon: 6});
                    //清空
                    $(".box-input input").val("");
                    //跳转到登录页面
                    window.location.href=ctx+"/login";
                }else {
                    //失败
                    layer.alert(data.msg, {icon: 5});
                }
            }

        });

    });

});



