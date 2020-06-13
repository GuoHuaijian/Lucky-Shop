package com.lucky.shop.common.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/14 1:30
 */
public class BeanUtil {

    /**
     * 记录每个修改字段的分隔符
     */
    public static final String SEPARATOR = ";;;";

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }


    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (StringUtil.isNullOrEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 缓存字段名和中文注释对应关系的map
     */
    private static Map<String, String> fieldMap = Maps.newHashMap();
    public static final Pattern COLUMN_DEFINITION_PATTERN = Pattern.compile("([A-Za-z]+)(?:\\(\\d+\\))?\\s*(?:(?:COMMENT|[Cc]omment)\\s+'(.*?)')?");

    /**
     * 从实体类的columDefinition中获取字段的中文注释，如果
     *
     * @param clazz
     * @param field
     * @return
     */
    public static String getFieldComment(Class clazz, Field field) {
        String key = clazz.getName() + field.getName();
        String comment = fieldMap.get(key);
        if (comment == null) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Column) {
                    Column columnAnno = (Column) annotation;
                    String columnDefinition = columnAnno.columnDefinition();
                    if (columnDefinition != null && !"".equals(columnDefinition.trim())) {
                        Matcher matcher = COLUMN_DEFINITION_PATTERN.matcher(columnDefinition.trim());
                        if (matcher.find()) {
                            comment = matcher.group(2);
                            fieldMap.put(key, comment);
                            break;
                        }
                    }
                }
            }
        }
        if (comment == null) {
            comment = field.getName();
            fieldMap.put(key, comment);
        }
        return comment;
    }

    /**
     * 比较两个对象pojo1和pojo2,并输出不一致信息
     *
     * @param key
     * @param pojo1
     * @param pojo2
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static String contrastObj(String key, Object pojo1, Map<String, String> pojo2) throws IllegalAccessException, InstantiationException {


        StringBuilder str = new StringBuilder();
        String headerName = key;
        String headerValue = pojo2.get(key);
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = "null";
                if (StringUtil.isNotNullOrEmpty(pojo2.get("id"))) {
                    o1 = getMethod.invoke(pojo1);
                }
                Object o2 = pojo2.get(StringUtil.firstCharToLowerCase(getMethod.getName().substring(3)));
                if (StringUtil.equals(key, field.getName())) {
                    headerName = getFieldComment(clazz, field);
                }
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (o1 instanceof Date) {
                    o1 = DateUtil.getDay((Date) o1);
                } else if (o1 instanceof Integer) {
                    o2 = Integer.parseInt(o2.toString());
                }
                if (!o1.toString().equals(o2.toString())) {
                    if (i != 1) {
                        str.append(SEPARATOR);
                    }
                    String fieldName = getFieldComment(clazz, field);
                    str.append(fieldName + ":" + o1 + "=>" + o2);
                    i++;
                }
            }
        } catch (Exception e) {
        }
        String header = headerName + "=" + headerValue + SEPARATOR;
        return header + str;
    }


    /**
     * 解析多个key(逗号隔开的)
     */
    public static String parseMutiKey(Map<String, String> requests) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : requests.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        return sb.toString();

    }
}
