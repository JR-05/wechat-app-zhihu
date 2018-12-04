//获取应用实例
var app = getApp()
var utils = require('../../utils/util.js')
Page({
     data: {
          sortType: ['热门排序', '时间排序'],
          currenSortType: '热门排序',
          questionList: [],
          offset: 0,
          duration: 2000,
          indicatorDots: true,
          autoplay: true,
          interval: 3000,
          loading: false,
          plain: false,
          circular: true,

     },

     //进入新闻详细页面
     goToDetail(e) {
          wx.navigateTo({
               url: '../detail/detail?id=' + e.target.dataset.id
          })
     },
     //点击排序
     clickToSort(e) {
          this.setData({
               sortType: [this.data.sortType[1], this.data.sortType[0]],
               currenSortType: this.data.sortType[1]
          })
          this.sort(this.data.currenSortType);
     },
     //数据排序
     sort(type) {
          var questions = this.data.questionList;
          //按照热门排序
          if (type == '热门排序') {
               for (var n = 0; n < questions.length; n++) {
                    var currenQuestion = questions[n];
                    var index = n;
                    for (var m = n + 1; m < questions.length; m++) {
                         var indexQuestion = questions[m];
                         if (indexQuestion.voteupAount > currenQuestion.voteupAount) {
                              questions[index] = indexQuestion;
                              questions[m] = currenQuestion;
                              index = m;
                         }
                    }
               }
          }
          // 按照最近时间排序
          else {
               for (var n = 0; n < questions.length ; n++) {
                    var currenQuestion = questions[n];
                    var index = n;
                    for (var m = n + 1; m < questions.length; m++) {
                         var indexQuestion = questions[m];
                         var currenTime = new Date(currenQuestion.createdTime).getTime();
                         var indexTime = new Date(indexQuestion.createdTime).getTime();
                         if (indexTime > currenTime) {
                              questions[index] = indexQuestion;
                              questions[m] = currenQuestion;
                              index = m;
                         }
                    }
               }
          }
          this.setData({
               questionList: questions
          })
     },
     //初始化页面进行数据请求加载
     onLoad(options) {
          var that = this

          //获取新闻数据
          wx.request({
               url: 'http://news-at.zhihu.com/api/4/news/latest',
               headers: {
                    'Content-Type': 'application/json'
               },
               success(res) {
                    that.setData({
                         banner: res.data.top_stories
                    })
               }
          })
          //请求焦点数据
          this.loadQuestionData();
     },
     onReachBottom: function() {
          this.loadQuestionData();
     },

     //获取焦点数据
     loadQuestionData: function() {
          var that = this
          wx.request({
               url: 'http://localhost:8080/question/' + that.data.offset,
               headers: {
                    'Content-Type': 'application/json'
               },
               success(res) {
                    if (res.data.status == 0) {
                         that.setData({
                              questionList: that.data.questionList.concat(res.data.data),
                              offset: that.data.offset + 1
                         })
                         that.sort(that.data.currenSortType);
                    }
               }
          })
     }
})