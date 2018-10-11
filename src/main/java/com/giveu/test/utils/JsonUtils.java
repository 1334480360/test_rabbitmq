//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.giveu.test.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

public class JsonUtils {
    public JsonUtils() {
    }

    public static String beanToJson(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;

        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException var7) {
            ;
        }

        if (props != null) {
            for(int i = 0; i < props.length; ++i) {
                try {
                    String name = objectToJson(props[i].getName());
                    String value = objectToJson(props[i].getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception var6) {
                    ;
                }
            }

            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }

        return json.toString();
    }

    public static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            Iterator i$ = list.iterator();

            while(i$.hasNext()) {
                Object obj = i$.next();
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    public static String arrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            Object[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    public static String mapToJson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            Iterator i$ = map.keySet().iterator();

            while(i$.hasNext()) {
                Object key = i$.next();
                json.append(objectToJson(key));
                json.append(":");
                json.append(objectToJson(map.get(key)));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }

        return json.toString();
    }

    public static String setToJson(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            Iterator i$ = set.iterator();

            while(i$.hasNext()) {
                Object obj = i$.next();
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    private static String numberToJson(Number number) {
        return number.toString();
    }

    private static String booleanToJson(Boolean bool) {
        return bool.toString();
    }

    private static String nullToJson() {
        return "";
    }

    private static String stringToJson(String s) {
        if (s == null) {
            return nullToJson();
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                switch(ch) {
                case '\b':
                    sb.append("\\b");
                    continue;
                case '\t':
                    sb.append("\\t");
                    continue;
                case '\n':
                    sb.append("\\n");
                    continue;
                case '\f':
                    sb.append("\\f");
                    continue;
                case '\r':
                    sb.append("\\r");
                    continue;
                case '"':
                    sb.append("\\\"");
                    continue;
                case '/':
                    sb.append("\\/");
                    continue;
                case '\\':
                    sb.append("\\\\");
                    continue;
                }

                if (ch >= 0 && ch <= 31) {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");

                    for(int k = 0; k < 4 - ss.length(); ++k) {
                        sb.append('0');
                    }

                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }

            return sb.toString();
        }
    }

    private static String objectToJson(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof Number) {
            json.append(numberToJson((Number)obj));
        } else if (obj instanceof Boolean) {
            json.append(booleanToJson((Boolean)obj));
        } else if (obj instanceof String) {
            json.append("\"").append(stringToJson(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(arrayToJson((Object[])((Object[])obj)));
        } else if (obj instanceof List) {
            json.append(listToJson((List)obj));
        } else if (obj instanceof Map) {
            json.append(mapToJson((Map)obj));
        } else if (obj instanceof Set) {
            json.append(setToJson((Set)obj));
        } else {
            json.append(beanToJson(obj));
        }

        return json.toString();
    }

    public static Object json2Object(String jsonString, Class pojoCalss) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Object pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    public static Map<String, Object> json2Map(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        HashMap valueMap = new HashMap();

        while(keyIter.hasNext()) {
            String key = (String)keyIter.next();
            Object value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    public static Object[] json2ObjectArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }

    public static List json2List(String jsonString, Class pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        List list = new ArrayList();

        for(int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }

        return list;
    }

    public static String[] json2StringArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            stringArray[i] = jsonArray.getString(i);
        }

        return stringArray;
    }

    public static Long[] json2LongArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            longArray[i] = jsonArray.getLong(i);
        }

        return longArray;
    }

    public static Integer[] json2IntegerArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            integerArray[i] = jsonArray.getInt(i);
        }

        return integerArray;
    }

    public static Date[] json2DateArray(String jsonString, String DataFormat) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            jsonArray.getString(i);
        }

        return dateArray;
    }

    public static Double[] json2DoubleArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            doubleArray[i] = jsonArray.getDouble(i);
        }

        return doubleArray;
    }
}
