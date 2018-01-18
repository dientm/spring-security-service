package com.ssoserver.model.response;

import com.ssoserver.utils.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseGenerator {
    private Response _createSSOResponse(APIStatus apiStatus, Object data) {
        return new Response(apiStatus, data);
    }

    // base method
    public ResponseEntity<Response> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
        return new ResponseEntity(_createSSOResponse(apiStatus, data), httpStatus);
    }

    public ResponseEntity<Response> buildResponse(Object data) {
        return buildResponse(APIStatus.OK, data, HttpStatus.OK);
    }

    public ResponseEntity<Response> badRequestResponse(List<Constant.ParamError> errors) {

        Map<String, String> errMap = null;

        if (errors != null) {

            errMap = new HashMap<>();
            for (Constant.ParamError error : errors) {
                errMap.put(error.getName(), error.getDesc());
            }
        }

        return buildResponse(APIStatus.ERR_BAD_REQUEST, errMap, HttpStatus.BAD_REQUEST);
    }
}
