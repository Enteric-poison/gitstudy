$(function () {
    // 页面加载完毕 给图片节点绑定点击事件
    $(".validImg").click(function () {
        // 切换图片内容
        $(this).attr("src", ctx + "/image?time=" + new Date());
    });

    /*$("#clickMes02").click(function () {
        sendMsg(clickMes02,ctx+"/image")
    });*/
    //获取短信验证码
    $("#clickMes02").click(function () {
        var phone = $("#phone").val();
        var imageCode = $("#code").val();
        var checkParams1 = checkPhone(phone);
        if (checkParams1 != false) {
            var checImageCode1 = checImageCode(imageCode);
            if (checImageCode1 != false) {
                var _this = $("#clickMes02");
                // time(_this);
                //发送ajax到后台请求转发短信验证
                $.ajax({
                    type: 'post',
                    url: ctx + '/sendMsg',
                    data: {
                        mobile: phone,
                        imageCode: imageCode,
                        type: 1
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            time(_this);
                        } else {
                            layer.tips(data.msg, "#clickMes02");

                        }
                    }

                });
            }
        }
    });



    //登录按钮
    $("#login").click(function () {
        var mobile=$("#phone").val();
        var imageCode = $("#code").val();
        var code=$("#verification").val();
        //验证手机号是否合法
        var checkParams1 = checkPhone(mobile);
        if (checkParams1!=false) {
            var checImageCode1 = checImageCode(imageCode);
            if (checImageCode1!=false) {
                $.ajax({
                    type: 'post',
                    url: ctx + "/doquickLogin",
                    data: {
                        mobile: mobile,
                        code:code
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            window.location.href = ctx + "/index";
                        } else {
                            layer.tips(data.msg, "#login");
                        }
                    }
                });
            }
        }




    });

});