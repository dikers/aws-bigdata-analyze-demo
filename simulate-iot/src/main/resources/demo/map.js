function initChart6(){

      var dom = document.getElementById("chart6");
      var myChart = echarts.init(dom);
      var app = {};
      option = null;
      var data = [
           {name: '上海', value: 25},
           {name: '广州', value: 38},
           {name: '北京', value: 79},
           {name: '深圳', value: 41},
           {name: '珠海', value: 42},
           {name: '杭州', value: 84},
           {name: '大庆', value: 279}
      ];
      var geoCoordMap = {
          '上海':[121.48,31.22],
          '广州':[113.23,23.16],
          '北京':[116.46,39.92],
          '深圳':[114.07,22.62],
          '珠海':[113.52,22.3],
          '杭州':[120.19,30.26],
          '大庆':[125.03,46.58]
      };

      var convertData = function (data) {
          var res = [];
          for (var i = 0; i < data.length; i++) {
              var geoCoord = geoCoordMap[data[i].name];
              if (geoCoord) {
                  res.push({
                      name: data[i].name,
                      value: geoCoord.concat(data[i].value)
                  });
              }
          }
          return res;
      };

      option = {
          title: {
              text: '各地区能耗分析',
              subtext: ' 示例数据 ',
              sublink: 'http://www.pm25.in',
              left: 'center'
          },
          tooltip : {
              trigger: 'item'
          },
          bmap: {
              center: [112.114129, 37.850339],
              zoom: 5,
              roam: true,
              mapStyle: {
                  styleJson: [{
                      'featureType': 'water',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#d1d1d1'
                      }
                  }, {
                      'featureType': 'land',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#f3f3f3'
                      }
                  }, {
                      'featureType': 'railway',
                      'elementType': 'all',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'highway',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#fdfdfd'
                      }
                  }, {
                      'featureType': 'highway',
                      'elementType': 'labels',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'arterial',
                      'elementType': 'geometry',
                      'stylers': {
                          'color': '#fefefe'
                      }
                  }, {
                      'featureType': 'arterial',
                      'elementType': 'geometry.fill',
                      'stylers': {
                          'color': '#fefefe'
                      }
                  }, {
                      'featureType': 'poi',
                      'elementType': 'all',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'green',
                      'elementType': 'all',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'subway',
                      'elementType': 'all',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'manmade',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#d1d1d1'
                      }
                  }, {
                      'featureType': 'local',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#d1d1d1'
                      }
                  }, {
                      'featureType': 'arterial',
                      'elementType': 'labels',
                      'stylers': {
                          'visibility': 'off'
                      }
                  }, {
                      'featureType': 'boundary',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#fefefe'
                      }
                  }, {
                      'featureType': 'building',
                      'elementType': 'all',
                      'stylers': {
                          'color': '#d1d1d1'
                      }
                  }, {
                      'featureType': 'label',
                      'elementType': 'labels.text.fill',
                      'stylers': {
                          'color': '#999999'
                      }
                  }]
              }
          },
          series : [
              {
                  name: 'pm2.5',
                  type: 'scatter',
                  coordinateSystem: 'bmap',
                  data: convertData(data),
                  symbolSize: function (val) {
                      return val[2] / 10;
                  },
                  label: {
                      normal: {
                          formatter: '{b}',
                          position: 'right',
                          show: false
                      },
                      emphasis: {
                          show: true
                      }
                  },
                  itemStyle: {
                      normal: {
                          color: 'purple'
                      }
                  }
              },
              {
                  name: 'Top 5',
                  type: 'effectScatter',
                  coordinateSystem: 'bmap',
                  data: convertData(data.sort(function (a, b) {
                      return b.value - a.value;
                  }).slice(0, 6)),
                  symbolSize: function (val) {
                      return val[2] / 10;
                  },
                  showEffectOn: 'render',
                  rippleEffect: {
                      brushType: 'stroke'
                  },
                  hoverAnimation: true,
                  label: {
                      normal: {
                          formatter: '{b}',
                          position: 'right',
                          show: true
                      }
                  },
                  itemStyle: {
                      normal: {
                          color: 'purple',
                          shadowBlur: 10,
                          shadowColor: '#333'
                      }
                  },
                  zlevel: 1
              }
          ]
      };;
      if (option && typeof option === "object") {
          myChart.setOption(option, true);
      }
  }