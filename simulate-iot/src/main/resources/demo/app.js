

var vue ;
$(function(){
    vue = new Vue({
            el: '#root_div',
            data:{
                name: 'alice',
                age: 19,
                pictures: []
             },
            methods:{
                send:function(){
                }
            }
    })
    console.log("-----------------------");
    initChart1()
    initChart2()
    initChart6()

});


function initChart1(){
        var myChart5 = echarts.init(document.getElementById('chart5'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:['正常','有异常','损坏']
            },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : ['50%', '70%'],
                    itemStyle : {
                        normal : {
                            label : {
                                show : false
                            },
                            labelLine : {
                                show : false
                            }
                        },
                        emphasis : {
                            label : {
                                show : true,
                                position : 'center',
                                textStyle : {
                                    fontSize : '30',
                                    fontWeight : 'bold'
                                }
                            }
                        }
                    },
                    data:[
                        {value:335, name:'正常'},
                        {value:135, name:'有异常'},
                        {value:12, name:'损坏'}
                    ]
                }
            ]
        };


        myChart5.setOption(option);

}

function initChart2(){
    var myChart2 = echarts.init(document.getElementById('chart2'));
    var myChart3 = echarts.init(document.getElementById('chart3'));
    var myChart4 = echarts.init(document.getElementById('chart4'));
    option = {
        title : {
            text: '能耗变化',
            subtext: ' '
        },
        tooltip : {
            trigger: 'axis'
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} '
                }
            }
        ],
        series : [
            {
                name:'最低气温',
                type:'line',
                data:[1, -2, 2, 5, 3, 2, 0],
                markPoint : {
                    data : [
                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };

    myChart2.setOption(option);
    myChart3.setOption(option);
    myChart4.setOption(option);

}
