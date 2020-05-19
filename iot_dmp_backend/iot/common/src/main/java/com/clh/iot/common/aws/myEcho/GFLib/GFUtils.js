var GFUtils={
    client_id:'amzn1.application-oa2-client.8e9b5e9d84994ffca0d25e13164aa8ca',  //Alexa Client id
    client_secret:'15204a2317e6ffae4b6f69ec88c301550c573d41dcb047896ded8e00d9af31e4',  //Alexa Client secret
    gateway_region:'https://api.amazonalexa.com/v3/events', //该用户所在的区域lambda。这里是北美
    logFun:function (messModel, messName, messDetail) {
        console.log(messModel + messName + messDetail);
    },
    postDataFun:function(){

    },
    optionsFun:function(hostname,port,path,method){
        var options={
            hostname:hostname,
            port:port,
            path:path,
            method:method,
            headers:{
                'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
            }
        };
        return options;
    }

};

module.exports=GFUtils;