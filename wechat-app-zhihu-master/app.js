//app.js
App({
     onLaunch: function() {
          //调用API从本地缓存中获取数据
          var logs = wx.getStorageSync('logs') || []
          logs.unshift(Date.now())
          wx.setStorageSync('logs', logs)
     },
     getUserInfo: function(cb) {
          var that = this
          if (this.globalData.userInfo) {
               typeof cb == "function" && cb(this.globalData.userInfo)
          } else {
               //调用登录接口
               wx.login({
                    success: function() {
                         wx.getUserInfo({
                              success: function(res) {
                                   that.globalData.userInfo = res.userInfo
                                   typeof cb == "function" && cb(that.globalData.userInfo)
                              }
                         })
                    }
               })
          }
     },
     globalData: {
          userInfo: null
          // userInfo: {
          //      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/PXe0QvTDKdMKTjYaTakSF5SDKoyicjroCgeZPNa3so8XcIauGGsM5ibEicEMGEuW3BiaqLSZjxU8CZO5hY3eHoKngA/132",
          //      brithDay: "1997-06-12",
          //      city: "Shanwei",
          //      country: "China",
          //      gender: 1,
          //      id: 44,
          //      imagePath: "http://localhost:8080/Favicon/JR1997-1543647809384-head_icon.png",
          //      language: "zh_CN",
          //      nickName: "锦荣",
          //      password: 123456,
          //      province: "Guangdong",
          //      userName: "JR1997",
          // }
     }
})