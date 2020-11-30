package com.sp.spproj;

import java.util.Stack;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-11-02 17:13
 * @version:
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }

    public static String simplifyPath(String path) {

        if ("".equals(path)) {
            return "";
        }

        String tempStr = path.replace("//","/");

        Stack<String> stack = new Stack<>();

        for (String s : tempStr.substring(1).split("/")) {
            switch (s){
                case "":
                case ".":break;
                case "..":
                    if (!stack.empty()) {
                        stack.pop();
                    }
                    break;
                default:stack.push(s);break;
            }
        }

        String reuslt = "";
        while (true){
            if (stack.empty()){
                break;
            }
            reuslt = stack.pop()+"/"+reuslt;
        }

        if ("".equals(reuslt)){
            return "/";
        }
        return "/"+reuslt.substring(0, reuslt.length()-1);
    }
}
