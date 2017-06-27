package com.sstgroup.xabaapp;

import com.sstgroup.xabaapp.utils.ErrorUtils;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Note: This tests depends on retrofit2 and okhttp3 libraries
 * Created by julianlubenov on 6/23/17.
 */

public class ErrorUtilsUnitTest {

    private Response createErrorResponse(ResponseBody body) {
        return Response.error(body, new okhttp3.Response.Builder().code(401).body(body).message("").protocol(Protocol.HTTP_2).request(new Request.Builder().url(new HttpUrl.Builder().scheme("http").host("test").build()).build()).build());
    }

    private ResponseBody invalidJSONStructureResponse() {
        return ResponseBody.create(MediaType.parse("application/json"), "{\"status\":\"ERROR\",\"errors\":{\"pin\":[\"The input is not valid.\"]}}");
    }

    private ResponseBody invalidJSONResponse() {
        return ResponseBody.create(MediaType.parse("application/json"), "{\"status\":\"ERROR\",\"errors\":{pin\":[\"The input is not valid.\"]}}");
    }

    @Test
    public void parseLoginError_doesNotThrowExceptionOnInvalidJSONStructureBody() {
        try {
            ErrorUtils.parseLoginError(createErrorResponse(invalidJSONStructureResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("paserLoginError throws exception with different JSON structure, exception: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void parseLoginError_doesNotThrowExceptionOnInvalidJSONBody() {
        try {
            ErrorUtils.parseLoginError(createErrorResponse(invalidJSONResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("paserLoginError throws exception with invalid JSON, exception: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void pasrLoginError_parsesValidObjectIfJSONISValid() {
        try {
            ResponseBody body = ResponseBody.create(MediaType.parse("application/json"), "{\"status\":\"ERROR\",\"errors\":\"The input is not valid.\"}");
            ErrorUtils.parseLoginError(createErrorResponse(body));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("paserLoginError fails even the the JSON is valid, exception: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void parseErrorCodeMessage_doesNotThrowExceptionOnInvalidJSONBody() {
        try {
            ErrorUtils.parseErrorCodeMessage(createErrorResponse(invalidJSONResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("parseErrorCodeMessage throws exception with invalid JSON, exception: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void parseStatusAndError_doesNotThrowExceptionOnInvalidJSONStructureBody() {
        try {
            ErrorUtils.parseStatusAndError(createErrorResponse(invalidJSONStructureResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("parseStatusAndError throws exception with different JSON structure, exception: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void parseStatusAndError_doesNotThrowExceptionOnInvalidJSONBody() {
        try {
            ErrorUtils.parseStatusAndError(createErrorResponse(invalidJSONResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("parseStatusAndError throws exception with invalid JSON, exception: " + e.getMessage() + "\n");
        }
    }

    public void parseRegisterWorkerError_doesNotThrowExceptionOnInvalidJSONBody() {
        try {
            ErrorUtils.parseRegisterWorkerError(createErrorResponse(invalidJSONResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("parseRegisterWorkerError throws exception with invalid JSON, exception: " + e.getMessage() + "\n");
        }
    }

    public void parseErrorWithDictionary_doesNotThrowExceptionOnInvalidJSONBody() {
        try {
            ErrorUtils.parseErrorWithDictionary(createErrorResponse(invalidJSONResponse()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("parseErrorWithDictionary throws exception with invalid JSON, exception: " + e.getMessage() + "\n");
        }
    }

}
