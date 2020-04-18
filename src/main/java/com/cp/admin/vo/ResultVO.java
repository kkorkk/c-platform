package com.cp.admin.vo;

import com.cp.admin.constant.SysConstant;
import lombok.Data;

import javax.xml.transform.Result;

@Data
public class ResultVO {

    private Integer resultCode;
    private String resultMsg;
    private Object data;

    public static ResultVO success(Integer code, String message, Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(code);
        resultVO.setResultMsg(message);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(String message, Object data){
        return success(SysConstant.RESULT_CODE_SUCCESS_DEFAULT, message, data);
    }

    public static ResultVO success(Object data){
        return success(null, data);
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String message, Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(code);
        resultVO.setResultMsg(message);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(String message, Object data){
        return error(SysConstant.RESULT_CODE_ERROR_DEFAULT, message, data);
    }

    public static ResultVO error(String message){
        return error(message, null);
    }

    public static ResultVO error(){
        return error(null);
    }


}
