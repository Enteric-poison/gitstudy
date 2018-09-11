$(function () {
    //标签选择加载
    loadInvestListDataInfo();
    $(".tab").click(function () {
       $(this).addClass("list_active");//添加被选中状态
        $(".tab").not(this).removeClass("list_active");
        var itemCycle=$(this).index();
        console.log(itemCycle);
        var isHistory=0;// 可投项目
        if (itemCycle==4){
            isHistory=1;// 历史项目
        }
        var itemType=$("#itemType").val();

        loadInvestListDataInfo(itemCycle,isHistory,itemType);
    });


});


/**
 * 加载投资列表方法
 * @param itemCycle  项目期限
 * @param isHistory  是否为历史项目  1-历史项目  0-可投项目
 * @param itemType  项目类别
 * @param pageNum   页码
 * @param pageSize   每页大小
 *
 */
function loadInvestListDataInfo(itemCycle,isHistory,itemType,pageNum,pageSize) {
    var params={};
    params.isHistory=0;//默认为可投项目
    params.pageNum=1;
    params.pageSize=10;
    
    if (!isEmpty(itemCycle)){
        params.itemCycle=itemCycle;
    }
    if (!isEmpty(isHistory)) {
        params.isHistory=isHistory;
    }
    if (!isEmpty(itemType)) {
        params.itemType=itemType;
    }
    if (!isEmpty(pageNum)) {
        params.pageNum=pageNum;
    }
    if (!isEmpty(pageSize)) {
        params.pageSize=pageSize;
    }

    $.ajax({
       type:'post',
       url:ctx+'/item/list',
       data:params,
        success:function (data) {
            var page=data.paginator;
            var list=data.list;
            $("#pages").html("");
            if(page.total>0){


                initTrHtml(list);
                // 渲染投资进度
                initInvestJd();
                // 拼接页码html
                initPageHtml(page);
            }else{
                $("#pages").html("<img style='margin-left: -70px;padding:40px;' " +
                    "src='/statics/img/zanwushuju.png'>");

                $("#pcItemList").html("");
            }
        }
    });
    
}


//加载数据格式
function initTrHtml(list) {
    if (list.length>0) {
        var trs="";
        for(var i=0;i<list.length;i++){
            var temp= list[i];
            trs=trs+"<tr>";
                // 年化率
                trs=trs+"<td>";
                     trs=trs+'<strong>'+temp.item_rate+'</strong><span>%'
                        if (temp.item_add_rate>0) {
                            trs=trs+"+"+temp.item_add_rate+"%";
                        }
                    trs=trs+"</span>";
                trs=trs+"</td>";
                // 期限
                trs=trs+'<td>'+temp.item_cycle+'天</td>';
                //产品
                trs=trs+"<td>"+temp.item_name;
                //判断项目标记类型
                if (temp.item_isnew==1){
                    trs=trs+"<strong class='colorful' new>NEW</strong>";
                }
                if (temp.item_isnew=0 && item.move_vip==1){
                    trs=trs+"<strong class='colorful' app>APP</strong>";
                }
                if (temp.item_isnew=0 && item.move_vip==1 && item.item_isrecommend==1){
                    trs=trs+"<strong class='colorful' hot>HOT</strong>";
                }
                if (temp.item_isnew=0 && item.move_vip==1 && item.item_isrecommend==0 && !(isEmpty(item.password))){
                    trs=trs+"<strong class='colorful' psw>LOCK</strong>";
                }
                trs=trs+"</td>";

            // 信用等级
            trs=trs+"<td class='trust_range'>";
                if (temp.total>=90){
                    trs=trs+"A+";
                }else if (temp.total>=85 && temp.total<90){
                    trs=trs+"A";
                }else if (temp.total>=75 && temp.total<85){
                    trs=trs+"A-";
                }else if (temp.total>=65 && temp.total<75){
                    trs=trs+"B";
                }
            trs=trs+"</td>";

            // 担保机构
            trs=trs+"<td><img src="+ctx+"/statics/img/logo.png></td>";
            // 投资进度
            trs=trs+"<td class='data-val' data-val='"+temp.item_scale+"'></td>";
            // 操作项

            trs=trs+"<td>";
            if(temp.item_status==1){
                trs=trs+"<p><a ><input class='countdownButton' valid type='button' value='即将开标'></a></p>";
            }
            if (temp.item_status==10){
                trs=trs+"<p class='left_money'>可投金额"+temp.syAmount+"</p><p><a ><input valid type='button' value='立即投资'></a></p>"
            }
            if (temp.item_status==20) {
                trs=trs+"<p><a ><input not_valid type='button' value='已抢完'></a></p>";
            }
            if (temp.item_status==30||temp.item_status==31) {
                trs=trs+"<p><a ><input not_valid type='button' value='还款中'></a></p>";
            }
            if (temp.item_status==23) {
                trs=trs+"<p><a><input not_valid type='button' value='已满标'/></a></p>"
            }
            if(temp.item_status==32){
                trs=trs+"<p style='position: relative'><a  class='yihuankuan'>已还款</a><div class='not_valid_pay'></div></p>";
            }

            trs=trs+"</td>";

            trs=trs+"</tr>";
        }
        $("#pcItemList").html(trs);
    }
}

//进度渲染
function initInvestJd() {
    $(".data-val").each(function () {
            $(this).radialIndicator({
                radius:35, //定义圆形指示器的内部的圆的半径。
                //barBgColor:"orange", //定义圆形指示器的刻度条的背景颜色。
                barColor:"orange", //指示器数值样式
                //percentage:true, //设置为true显示圆形指示器的百分比数值。
                //initValue:20, //圆形指示器初始化的值。
                minValue:0, //圆形指示器的最小值。
                maxValue:100,
                format:"####%"
            });
            $(this).data("radialIndicator").animate($(this).attr("data-val"));
    });
}

//类别切换响应
function initItemData(itemType) {
    var itemCycle;
    var isHistory=0;
    $(".tab").each(function () {
        if ($(this).hasClass('list_active')) {
            itemCycle=$(this).index();
        }

    });
    if (itemCycle==4){
        isHistory=1;
    }
    loadInvestListDataInfo(itemCycle,isHistory,itemType);
}
//页码拼接
function initPageHtml(page) {
    var pageArry=page.navigatePages;

    if (pageArry.length>0){
        var lis='';
        for (var i=0;i<pageArry.length;i++){
            var p=pageArry[i];
           var href='javascript:toPageDataInfo('+p+')';
           if (page.pageNum==p){
               lis=lis+"<li class='active'><a href='"+href+"' title='第'“+p+”页>"+p+"</a>";
           }else {
               lis=lis+"<li ><a href='"+href+"' title='第'“+p+”页>"+p+"</a>";
           }
        }
        $("#pages").html(lis);
    }
}

//局部页面刷新技术
function toPageDataInfo(pageNum) {
    var itemCycle;
    var isHistory=0;//可投资
    $(".tab").each(function () {
      if ($(this).hasClass("list_active")) {
          itemCycle=$(this).index();
      }
    });
    if (itemCycle==4){
        isHistory=1;
    }
    var itemType= $("#itemType").val();
    loadInvestListDataInfo(itemCycle,isHistory,itemType,pageNum);
}
