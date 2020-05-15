



exports.handler = function (request, context) {
    var GFEvents=require('./GFLib/GFEvents');

    if (request.directive.header.namespace === 'Alexa.Discovery' && request.directive.header.name === 'Discover') {

        //处理发现设备业务逻辑
        GFEvents.handleDiscovery(request, context, "");
    }else if(request.directive.header.namespace ==='Alexa.Authorization'){

        //处理Alexa 云网关 授权认证业务逻辑
        GFEvents.handleAuthorization(request,context);
    }










};