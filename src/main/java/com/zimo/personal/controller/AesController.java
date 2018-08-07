package com.zimo.personal.controller;

import com.zimo.personal.util.AesUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aes")
public class AesController {

    @PostMapping("decode")
    public String decode(String originBody, String transId) throws Exception {
        byte[] bytes = AesUtil.base64Decode(originBody);
        return AesUtil.aesDecryptByBytes(bytes, transId);
    }
}
