$(function () {
    $("#login").click(function () {
        var mobile=$("#phone").val();
        var password=$("#password").val();
       //验证手机号是否合法
        var checkParams1 = checkPhone(mobile);
        if (checkParams1!=false){
            if (isEmpty(password)){
                layer.tips("密码不能为空","#password");
                return;
            }
        }

        $.ajax({
           type:'post',
           url:ctx+"/user/login",
           data:{
               mobile:mobile,
               password:password
           } ,
            dataType:'json',
            success:function (data) {
                if (data.code==200){
                    window.location.href=ctx+"/index";
                } else {
                    layer.tips(data.msg,"#login");
                }
            }
        });


    });
});