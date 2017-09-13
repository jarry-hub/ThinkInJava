package com.spring.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
	
	 /** 
     * 从request中获得参数Map，并返回可读的Map 
     * map<String, String[]> 变量只能是int,boolean,string和数组等，不能是对象
     *  
     * @param request 
     * @return 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public static Map<String, String> getParameterMap(HttpServletRequest request) {  
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();  
        // 返回值Map  
        Map<String, String> returnMap = new HashMap();  
        Iterator entries = properties.entrySet().iterator();  
        Entry entry;  
        String name = "";  
        String value = "";  
        while (entries.hasNext()) {  
            entry = (Entry) entries.next();  
            name = (String) entry.getKey();  
            Object valueObj = entry.getValue();  
            if(null == valueObj){  
                value = "";  
            }else if(valueObj instanceof String[]){  
                String[] values = (String[])valueObj;  
                for(int i=0;i<values.length;i++){  
                    value = values[i] + ",";  
                }  
                value = value.substring(0, value.length()-1);  
            }else{  
                value = valueObj.toString();  
            }  
            returnMap.put(name, value);  
        }  
        return returnMap;  
    }  

}

