'use strict';
function log(title, msg) {
    console.log(`[${title}] ${msg}`);
}


function safelyParseJSON(json) {
    let parsed = null;

    try {
        return parsed = JSON.parse(json);
    } catch (e) {
        return null;
    }
}


function generateMessageID() {
    const uuidv4 = require('./nodeModules/uuid/v4');
    return uuidv4();
}


function generateResponse(name, payload) {
    return {
        header: {
            messageId: generateMessageID(),
            name: name,
            namespace: 'Alexa.ConnectedHome.Control',
            payloadVersion: '2',
        },
        payload: payload,
    };
}


function isValidToken() {

    return true;
}

function isDeviceOnline(applianceId) {
    log('DEBUG', `isDeviceOnline (applianceId: ${applianceId})`);
    return true;
}


function handleDiscovery(request, callback) {
    log('DEBUG', `Discovery Request: ${JSON.stringify(request)}`);
    const userAccessToken = request.directive.payload.scope.token.trim();

    if (!userAccessToken || !isValidToken(userAccessToken)) {
        const errorMessage = `Discovery Request [${request.header.messageId}] failed. Invalid access token: ${userAccessToken}`;
        log('ERROR', errorMessage);
        callback(new Error(errorMessage));
    }

    const header = {
        messageId: generateMessageID(),
        name: 'Discover.Response',
        namespace: 'Alexa.Discovery',
        payloadVersion: '3'
    }


    var http=require('https');
    var querystring=require('querystring');
    var postData=querystring.stringify({
    });
    var options={
        hostname:'ss.wiwacam.com',
        port:8443,
        path:'/Echo/deviceList?token='+userAccessToken,
        method:'GET',
        headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
    }
    var req=http.request(options, function(res) {
        console.log('Status:',res.statusCode);
        console.log('headers:',JSON.stringify(res.headers));
        res.setEncoding('utf-8');
        res.on('data',function(chun){
            var data = safelyParseJSON(chun);
            var endpoints=data.cameras;

            const response = {
                event: {
                    header,
                    payload: {endpoints}
                }
            };

            log('DEBUG', `Discovery Response: ${JSON.stringify(response)}`);

            callback(null, response);

        });
        res.on('end',function(){
            console.log('No more data in response.********');
        });
    });
    req.on('error',function(err){
        console.error(err);
    });
    req.write(postData);
    req.end();

}

function handleControl(request, callback) {
    log('DEBUG', `Control Request: ${JSON.stringify(request)}`);

    const userAccessToken = request.directive.endpoint.scope.token.trim();

    if (!userAccessToken || !isValidToken(userAccessToken)) {
        log('ERROR', `Discovery Request [${request.header.messageId}] failed. Invalid access token: ${userAccessToken}`);
        callback(null, generateResponse('InvalidAccessTokenError', {}));
        return;
    }

    const applianceId = request.directive.endpoint.endpointId;

    if (!applianceId) {
        log('ERROR', 'No applianceId provided in request');
        const payload = { faultingParameter: `applianceId: ${applianceId}` };
        callback(null, generateResponse('UnexpectedInformationReceivedError', payload));
        return;
    }

    if (!isDeviceOnline(applianceId, userAccessToken)) {
        log('ERROR', `Device offline: ${applianceId}`);
        callback(null, generateResponse('TargetOfflineError', {}));
        return;
    }

    const correlation_Token = request.directive.header.correlationToken;

    const _width = request.directive.payload.cameraStreams[0].resolution.width;
    const _height = request.directive.payload.cameraStreams[0].resolution.height;

    const header = {
        correlationToken: correlation_Token,
        messageId: generateMessageID(),
        name: 'Response',
        namespace: 'Alexa.CameraStreamController',
        payloadVersion: '3'
    }

    const fs = require('fs');
    const camerasJSON = fs.readFileSync('./cameras.json');



    var http=require('https');
    var querystring=require('querystring');
    var postData=querystring.stringify({
    });
    var options={
        hostname:'ss.wiwacam.com',
        port:8443,
        path:'/Echo/deviceControl?token='+userAccessToken+"&endpoint="+applianceId,
        method:'GET',
        headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
    }
    var req=http.request(options, function(res) {
        console.log('Status:',res.statusCode);
        console.log('headers:',JSON.stringify(res.headers));
        res.setEncoding('utf-8');
        res.on('data',function(chun){
            console.log('body分隔线---------------------------------\r\n');
            const camerasObj = safelyParseJSON(chun);
            console.info('沈涛'+camerasObj.cameras.length);
            const cameraIdx = parseInt(applianceId) ;
            const _uri = camerasObj.cameras[cameraIdx].uri;

            const payload = {
                cameraStreams: [
                    {
                        uri: _uri,
                        resolution: {'width': _width, 'height': _height}
                    }]
            }

            const response = {
                event: {
                    header,
                    payload
                }
            };

            log('DEBUG', `Control Confirmation: ${JSON.stringify(response)}`);

            callback(null, response);

        });
        res.on('end',function(){
            console.log('No more data in response.********');
        });
    });
    req.on('error',function(err){
        console.error(err);
    });
    req.write(postData);
    req.end();






}





exports.handler = (request, context, callback) => {
    var GFEvents=require('./GFLib/GFEvents');
    switch (request.directive.header.namespace) {
        case 'Alexa.Discovery':
            handleDiscovery(request, callback);
            break;
        case 'Alexa.CameraStreamController':
            handleControl(request, callback);
            break;
        case 'Alexa.Authorization':
            GFEvents.handleAuthorization(request,context);
            break;

        default: {
            const errorMessage = `No supported namespace: ${request.header.namespace}`;
            log('ERROR', errorMessage);
            callback(new Error(errorMessage));
        }
    }
};
