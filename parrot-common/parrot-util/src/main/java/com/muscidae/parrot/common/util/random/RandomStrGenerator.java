package com.muscidae.parrot.common.util.random;

import java.util.Random;

/**
 * @author muscidae
 * @date 2019/5/27 16:36
 * @description 随机字符串构造器, 默认随机字符集合 A-Za-z0-9
 */
public final class RandomStrGenerator {

    private RandomStrGenerator(){ }

    public enum RandomScope {
        UpperLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        LowerLetter("abcdefghijklmnopqrstuvwxyz"),
        DigitLetter("0123456789"),
        UpperAndLowerLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
        UpperAndDigitLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"),
        LowerAndDigitLetter("abcdefghijklmnopqrstuvwxyz0123456789"),
        AllLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"),
        ;
        private String random;
        public String getRandom() {return random;}
        RandomScope(String random){this.random = random;}
    }

    /**
     * @author muscidae
     * @date 2019/5/27 17:56
     * @description 产生的随机字符串长度
     * @param len 产生的随机字符串长度
	 * @param randomScope 随机字符串类型
     * @return java.lang.String
     */
    public String generate(int len, RandomScope randomScope) {
        StringBuilder result = new StringBuilder(len);
        char[] chars = randomScope.getRandom().toCharArray();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            result.append(chars[random.nextInt(chars.length)]);
        }
        return result.toString();
    }

    /**
     * @author muscidae
     * @date 2019/5/27 17:54
     * @description 指定范围生成随机字符串
     * @param len 产生的随机字符串长度
	 * @param codeChars 指定随机生成字符范围
     * @return java.lang.String
     */
    public String generate(int len,String codeChars) {
        StringBuilder result = new StringBuilder(len);
        char[] chars = codeChars.toCharArray();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            result.append(chars[random.nextInt(chars.length)]);
        }
        return result.toString();
    }

    public enum Singleton{
        INSTANCE;
        private RandomStrGenerator randomStrGenerator;
        Singleton(){ randomStrGenerator = new RandomStrGenerator(); }
        public RandomStrGenerator newInstance(){ return randomStrGenerator; }
    }

}
