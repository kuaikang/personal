package com.zimo.personal.controller;

import com.zimo.personal.entity.AesEntity;
import com.zimo.personal.util.AesUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aes")
public class AesController {

    @PostMapping("decode")
    public String decode(@RequestBody AesEntity aesEntity) throws Exception {
        byte[] bytes = AesUtil.base64Decode(aesEntity.getOriginBody());
        return AesUtil.aesDecryptByBytes(bytes, aesEntity.getTransId());
    }
}
