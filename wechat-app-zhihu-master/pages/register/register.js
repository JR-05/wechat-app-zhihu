// pages/register/register.js
const app = getApp()
Page({

     /**
      * 页面的初始数据
      */
     data: {
          tempImagePath: '/images/head_icon.png',
          userName: null,
          brithDay: null,
          password: null,
          passwordConfirm: null,
          nitifyColor: '#808080',
          notifyMessage: '确认密码'
     },

     /**
      * 选择本地图片
      */
     chooseImage: function() {
          var _this = this;
          wx.chooseImage({
               count: 1, // 默认9
               sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
               sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
               success: function(res) {
                    // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
                    _this.setData({
                         tempImagePath: res.tempFilePaths[0]
                    })
               }
          })
     },


     /**
      * 监听输入信息
      */
     inputListener: function(e) {
          switch (e.currentTarget.id) {
               case 'username':
                    {
                         this.setData({
                              userName: e.detail.value
                         });
                         break;
                    }
               case 'brithday':
                    {
                         this.setData({
                              brithDay: e.detail.value
                         });
                         break;
                    }
               case 'password':
                    {
                         this.setData({
                              password: e.detail.value
                         });
                         break;
                    }
               case 'passwordConfirm':
                    {
                         this.setData({
                              passwordConfirm: e.detail.value
                         });
                         break;
                    }
          }
     },


     /**
      * 点击注册
      */
     register: function() {
          if (!this.checkData()) {
               wx.showToast({
                    title: '请检查注册信息!!!',
                    icon: 'none'
               })
               return;
          }
          var filePath = this.data.tempImagePath;
          var userName = this.data.userName;
          var brithDay = this.data.brithDay;
          var password = this.data.password;
          wx.uploadFile({
               url: 'http://localhost:8080/user/register', //仅为示例，非真实的接口地址
               filePath: filePath,
               name: 'file',
               formData: {
                    'userName': userName,
                    'brithDay': brithDay,
                    'password': password
               },
               success: function(res) {
                    var data = JSON.parse(res.data);
                    if (data.status == 0) {
                         //提示信息
                         wx.showToast({
                              title: '注册成功',
                              duration: 1500,
                              complete: function() {
                                   wx.redirectTo({
                                        url: '../login/login?userName=' + data.data.userName,
                                   })
                              }
                         });
                    } else {
                         wx.showToast({
                              title: data.msg,
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

     /**
      *检查输入信息 
      */
     checkData: function() {
          if (this.data.userName == null | this.data.brithDay == null) {
               return false;
          }
          if (this.data.password != this.data.passwordConfirm) {
               this.setData({
                    nitifyColor: '#ff0000',
                    notifyMessage: '两次密码不一致',
                    passwordConfirm: null
               })
               return false;
          }
          return true;
     },
     //日期选择
     bindDateChange: function(e) {
          this.setData({
               brithDay: e.detail.value
          })
     },
})