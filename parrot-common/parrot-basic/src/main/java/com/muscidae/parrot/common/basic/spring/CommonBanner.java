//package com.muscidae.parrot.common.basic.spring;
//
//import org.springframework.boot.Banner;
//import org.springframework.boot.SpringBootVersion;
//import org.springframework.boot.ansi.AnsiColor;
//import org.springframework.boot.ansi.AnsiOutput;
//import org.springframework.boot.ansi.AnsiStyle;
//import org.springframework.core.env.Environment;
//
//import java.io.PrintStream;
//
///**
// * @author muscidae
// * @date 2019/1/22 0:16
// * @description Banner
// */
//@Deprecated
//public class CommonBanner implements Banner {
//
//    private static final String[] BANNER = new String[]{
//            "",
//            "  .                             _      _              __ _ _ ",
//            " /\\\\  __  __  _   _  ___   ___ (_)  __| |  ____   ___ \\ \\ \\ \\",
//            "( ( )|  \\/  || | | |/ __| / __|| | / _` | / _` | / _ \\ \\ \\ \\ \\",
//            " \\\\/ | |\\/| || |_| |\\__ \\| (__ | || (_| || (_| ||  __/  ) ) ) )",
//            "  '  | |  | | \\__,_||___/ \\___||_| \\__,_| \\__,_| \\___| / / / /",
//            " ====|_|  |_|=========================================/_/_/_/"};
//
//    private static final String SPRING_BOOT = " :: Spring Boot :: ";
//    private static final String MUSCIDAE = " :: Muscidae Plus :: ";
//
//    protected CommonBanner(){ }
//
//    @Override
//    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//        String[] var4 = BANNER;
//        int var5 = var4.length;
//
//        for(int var6 = 0; var6 < var5; ++var6) {
//            String line = var4[var6];
//            out.println(line);
//        }
//
//        String version = SpringBootVersion.getVersion();
//        version = version != null ? " (v" + version + ")" : "";
//        StringBuilder padding = new StringBuilder();
//
//        while(padding.length() < 42 - (version.length() + " :: Spring Boot :: ".length())) {
//            padding.append(" ");
//        }
//        out.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, MUSCIDAE, AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version}));
//        out.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, SPRING_BOOT , AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version}));
//        out.println();
//    }
//
//
//}
