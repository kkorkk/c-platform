package com.kk.cplatform.utils;

import com.kk.cplatform.model.vo.ResultVO;

public class ResultVOUtil {

    public static final String RESULT_CODE_SUCCESS = "0";

    public static final String RESULT_CODE_ERROR = "1";

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(RESULT_CODE_SUCCESS);
        resultVO.setData(data);
        return resultVO;
    }

    public ResultVO success(){
        return ResultVOUtil.success(null);
    }

    public ResultVO error(String resultMsg){
        ResultVO resultVO = new ResultVO();
        resultVO.setResultCode(RESULT_CODE_ERROR);
        resultVO.setResultMsg(resultMsg);
        return resultVO;
    }

}
