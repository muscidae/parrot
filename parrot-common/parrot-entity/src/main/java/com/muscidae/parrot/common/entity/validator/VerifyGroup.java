package com.muscidae.parrot.common.entity.validator;

import javax.validation.GroupSequence;

/**
 * @author muscidae
 * @date 2019/5/27 16:11
 * @description CRUD表单校验分组
 */
@GroupSequence({ VerifyGroup.Create.class, VerifyGroup.Update.class })
public class VerifyGroup {

    public interface Create{ }

    public interface Retrieve{ }

    public interface Update{ }

    public interface Delete{ }

}
