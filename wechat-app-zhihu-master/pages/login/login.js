// pages/login/login.js
const app = getApp();
Page({

     /**
      * 页面的初始数据
      */
     data: {
          userName: null,
     },

     /**
      * 生命周期函数--监听页面加载
      */
     onLoad: function(options) {
          //获取注册成功传入的用户名信息     
          this.setData({
               userName: options.userName
          });
     
          // 查看是否授权获取用户信息
          wx.getSetting({
               success(res) {
                    if (res.authSetting['scope.userInfo']) {
                         // 已经授权，可以直接调用 getUserInfo 获取头像昵称
                         wx.getUserInfo({
                              success: function(res) {
                                   app.globalData.userInfo = res.userInfo;
                              }
                         })
                    }
               }
          })
     },

     /**
      * 获取form表单信息
      */
     formSubmit: function(e) {
          var userName = e.detail.value.userName;
          var password = e.detail.value.password;
          this.login(userName, password);
     },

     /**
      * 登陆
      */
     login: function(userName, password) {
          wx.request({
               url: 'http://localhost:8080/user/login',
               data: {
                    'userName': userName,
                    'password': password
               },
               success: function(res) {
                    if (res.data.status == 0) {
                         //保存用户数据
                         var strData = JSON.stringify(res.data.data).slice(1);
                         var strUserInfo = JSON.stringify(app.globalData.userInfo).slice(0,-1);
                         //拼接JSON对象
                         app.globalData.userInfo = JSON.parse(strUserInfo+","+strData);
                         //跳转到首页
                         wx.switchTab({
                              url: '../index/index',
                         })
                    } else {
                         wx.showToast({
                              title: '账号或密码错误',
                              icon: 'none'
                         })
                    }
               },
               fail: function(res) {
                    wx.showToast({
                         title: '请检查网络是否异常',
                         icon: 'none'
                    })
               }
          })
     },

})