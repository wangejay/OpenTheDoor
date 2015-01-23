package com.luffy.openthedoor.action;

import retrofit.client.Response;
import retrofit.http.GET;

/**
 * the interface of Yun
 */
public interface YunInterface {

    //FIXME: Change the default pin here
    @GET("/arduino/digital/2/0")
    Response triggerDoorOpen();
}
