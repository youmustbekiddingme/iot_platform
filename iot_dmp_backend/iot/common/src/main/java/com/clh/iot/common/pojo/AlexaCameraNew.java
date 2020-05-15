package com.clh.iot.common.pojo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlexaCameraNew {

    private String endpointId;
    private String manufacturerName;
    private String modelName;
    private String friendlyName;
    private String description;
    private List displayCategories;
    private Map cookie;
    private List capabilities;

    public static void main(String[] args) {
        Gson gson = new Gson();
        AlexaCameraNew alexaCameraNew = new AlexaCameraNew("111","clh");
        System.out.println(gson.toJson(alexaCameraNew));
    }

    public AlexaCameraNew(String endpointId, String friendlyName) {
        this.endpointId = endpointId;
        this.friendlyName = friendlyName;


        this.manufacturerName="Hikam";
        this.modelName="Hikam Camera";
        this.description="Smart Security Camera By Hikam";


        this.displayCategories=new ArrayList();
        this.displayCategories.add("CAMERA");
        this.cookie=new HashMap();

        this.capabilities=new ArrayList();

        Map capabilitiesMap = new HashMap();
        this.capabilities.add(capabilitiesMap);

        capabilitiesMap.put("type","AlexaInterface");
        capabilitiesMap.put("interface","Alexa.CameraStreamController");
        capabilitiesMap.put("version","3");

        List cameraStreamConfigurationsList = new ArrayList();
        Map cameraStreamConfigurationsMap = new HashMap();

        List protocolsList= new ArrayList();protocolsList.add("RTSP");
        cameraStreamConfigurationsMap.put("protocols",protocolsList);
        List authorizationTypesList = new ArrayList();authorizationTypesList.add("NONE");
        cameraStreamConfigurationsMap.put("authorizationTypes",authorizationTypesList);
        List videoCodecsList = new ArrayList();videoCodecsList.add("H264");
        cameraStreamConfigurationsMap.put("videoCodecs",videoCodecsList);
        List audioCodecsList = new ArrayList();audioCodecsList.add("G711");
        cameraStreamConfigurationsMap.put("audioCodecs",audioCodecsList);

        List resolutionsList = new ArrayList();
        Map resolutionsMap = new HashMap();
        resolutionsMap.put("width",1080);resolutionsMap.put("height",960);
        resolutionsList.add(resolutionsMap);
        cameraStreamConfigurationsMap.put("resolutions",resolutionsList);

        cameraStreamConfigurationsList.add(cameraStreamConfigurationsMap);

        capabilitiesMap.put("cameraStreamConfigurations",cameraStreamConfigurationsList);
















    }
}
