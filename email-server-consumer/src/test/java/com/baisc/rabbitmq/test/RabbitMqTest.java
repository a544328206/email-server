package com.baisc.rabbitmq.test;

/**
 * Created by sdc on 2017/7/5.
 */

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sdc on 2017/7/5.
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @org.junit.Test
    public void  test() {
        try {
            int i = 1/0;
            System.out.println(i);
        }catch (Exception e) {
            logger.error("EmailServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
        }
    }

}
