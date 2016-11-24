package com.dianwoba.rctamap.search;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lintong on 2016/11/23.
 */

public class MyRouteSearch extends AMapSearch implements RouteSearch.OnRouteSearchListener {
    public RouteSearch routeSearch;

    public MyRouteSearch(ReactContext context, String requestId) {
        routeSearch = new RouteSearch(context);
        routeSearch.setRouteSearchListener(this);
        this.reactContext = context;
        this.setRequestId(requestId);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int resultId) {
        if (1000 != resultId) {
            this.sendEventWithError("request regeocode error");
            return;
        }
        WritableArray array = Arguments.createArray();
        WritableMap map = Arguments.createMap();
//        RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
//
//        map.putString("formattedAddress", address.getFormatAddress());
//        map.putString("province",address.getProvince());
//        map.putString("city",address.getCity());
//        map.putString("township",address.getTownship());
//        map.putString("neighborhood",address.getNeighborhood());
//        map.putString("building",address.getBuilding());
//        map.putString("district", address.getDistrict());
//
        array.pushMap(map);

        this.sendEventWithData(array);
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int resultId) {
        if (1000 != resultId) {
            this.sendEventWithError("request regeocode error");
            return;
        }
        WritableArray array = Arguments.createArray();
        WritableMap map = Arguments.createMap();
//        RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
//
//        map.putString("formattedAddress", address.getFormatAddress());
//        map.putString("province",address.getProvince());
//        map.putString("city",address.getCity());
//        map.putString("township",address.getTownship());
//        map.putString("neighborhood",address.getNeighborhood());
//        map.putString("building",address.getBuilding());
//        map.putString("district", address.getDistrict());

        array.pushMap(map);

        this.sendEventWithData(array);
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int resultId) {
        if (1000 != resultId) {
            this.sendEventWithError("request geocode error");
            return;
        }

        WritableArray array = Arguments.createArray();
        WalkPath walkPath = walkRouteResult.getPaths().get(0);

        WritableMap map = Arguments.createMap();
        map.putDouble("distance", walkPath.getDistance());
        map.putDouble("duration",walkPath.getDuration());
//        List<LatLonPoint> list  = new ArrayList();
        List<String> strList = new ArrayList();
        for (WalkStep walkStep : walkPath.getSteps()) {
//            list.addAll(walkStep.getPolyline());
            for(LatLonPoint point:walkStep.getPolyline()){
                String str = "{" +"\"latitude\":" + point.getLatitude()+"," +
                        "\"longitude\":" +point.getLongitude() + "}";
                strList.add(str);
            }
        }
        map.putString("steps",strList.toString());
        array.pushMap(map);
        this.sendEventWithData(array);
    }
}
