var GFUtils=require('./GFUtils');
var querystring=require("querystring");

var events={

    handleAuthorization:function(request,context){

        var payload={

        };
        //  console.log(request);
        //  console.log(request.directive.payload.grant);
        //  console.log(request.directive.payload.grantee);
        //携带 用户toekn、code 请求echo server
        var header = request.directive.header;
        header.name= 'AcceptGrant.Response';
        var code = request.directive.payload.grant.code;
        var adminToken=request.directive.payload.grantee.token;
        var postData=querystring.stringify({
            "code":code,
            "adminToken":adminToken,
            "gatewayRegion":GFUtils.gateway_region
        });
        var http=require('https');
        var options=GFUtils.optionsFun('ss.wiwacam.com',8443,'/Echo/bindWiwacamSkillToEchoServer','POST');

        var req=http.request(options, function(res) {
            res.setEncoding('utf-8');
            res.on('data',function(data){
                // console.log("data:"+data);
                context.succeed({ event: { header: header, payload: payload } }); //授权认证返回，此处正常才会提示link成功
            });

        });
        req.on('error',function(err){
            console.error(err);
        });
        req.write(postData);
        req.end();
    },
    handleDiscovery:function(request, context) {
        var payload = {
            "endpoints":
                [
                    {
                        "endpointId": "32",
                        "manufacturerName": "<the manufacturer name of the endpoint>",
                        "description": "<a description that is shown in the Alexa app>",
                        "friendlyName": "yyy_doorbell",
                        "displayCategories": ["DOORBELL"],
                        "cookie": {},
                        "capabilities": [
                            {
                                "type": "AlexaInterface",
                                "interface": "Alexa.DoorbellEventSource",
                                "version": "3",
                                "proactivelyReported" : true
                            }
                        ]
                    }
                ]
        };
        var header = request.directive.header;
        header.name = "Discover.Response";
        context.succeed({ event: { header: header, payload: payload } });
    }
};
module.exports=events;