package com.muscidae.parrot.common.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author muscidae
 * @date 2019/5/16 12:57
 * @description 系统通用常量类
 */
public interface CommonConst {

    Charset UTF_8 = StandardCharsets.UTF_8;

    String UTF8 = "UTF-8";

    String ACCESS_TOKEN = "AccessToken";

    String AUTHORIZATION = "Authorization";

    String CONTENT_TYPE = "Content-Type";

    String FORWARD = "forward:";

    String REDIRECT = "redirect:";

    byte ENABLE = 0;

    byte DISABLE = 1;

}
