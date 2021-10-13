/**

 @Name：layuiAdmin 公共业务
 
 
 @License：LPPL
    
 */


layui.define(function (exports) {
  var $ = layui.$
    , layer = layui.layer
    , laytpl = layui.laytpl
    , setter = layui.setter
    , view = layui.view
    , admin = layui.admin

  //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  //……

  // admin.events.change_pwd = function () {
  //   $('#change_pwd').click(function () {
  //     layer.open({
  //       title: "修改密码",
  //       type: 1,
  //       move: false,
  //       area: '500px',
  //       content: $('#change_pwdmb').html()
  //     });
  //   })
  // }
  // function changePwd() {
  //   var data = layui.form.val("change_pwd");
  //   console.log(data);
  //   // layui.admin.req({
  //   //   url: layui.setter.baseUrl + 'supplier/add?t.type=2',
  //   //   type: 'post',
  //   //   data: data,
  //   //   done: function (res) {
  //   //     layer.closeAll();
  //   //     layer.open({
  //   //       title: '新增'
  //   //       , content: res.msg
  //   //     });
  //   //     layui.table.reload('customer-table-parseData');
  //   //   }
  //   // })
  // }

  // function closeLayer() {
  //   layer.closeAll();
  // }
  //退出
  admin.events.logout = function () {
    // 执行退出接口
    admin.req({
      url: layui.setter.baseUrl + '/login_loginOut'
      , type: 'get'
      , done: function (res) { //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行
        admin.exit();
      }
    });

  };


  //对外暴露的接口
  exports('common', {});
});