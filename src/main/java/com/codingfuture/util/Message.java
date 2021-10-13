package com.codingfuture.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class Message {
    private Message() {
    }

    public static void viewMessage(String telephone, String params) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI4GKbUfyz4BfpwYgp2Kxt", "5W3iW2KlpiPE0i8nksS4fgTbw4kZWL");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "码上未来");
        request.putQueryParameter("TemplateCode", "SMS_213286326");
        request.putQueryParameter("TemplateParam", params);
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
