package com.dianwoba.rctamap;

import com.amap.api.maps2d.model.LatLng;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by lintong on 2016/11/22.
 */

public class UpdateUserLocationEvent extends Event<UpdateUserLocationEvent> {
    private final LatLng coordinate;

    public UpdateUserLocationEvent(int id, LatLng coordinate) {
        super(id);
        this.coordinate = coordinate;
    }

    @Override
    public String getEventName() {
        return "topUpdateUserLocation";
    }

    @Override
    public boolean canCoalesce() {
        return false;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {

        WritableMap event = new WritableNativeMap();
        event.putDouble("latitude", coordinate.latitude);
        event.putDouble("longitude", coordinate.longitude);
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), event);
    }
}

