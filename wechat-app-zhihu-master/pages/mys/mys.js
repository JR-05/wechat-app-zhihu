// pages/mys/mys.js
const app = getApp();
Page({

     /**
      * 页面的初始数据
      */
     data: {
          userInfo: app.globalData.userInfo
     },

     /**
      * 生命周期函数--监听页面加载
      */
     onLoad: function() {
          this.setData({
               userInfo: app.globalData.userInfo
          });
     },

})