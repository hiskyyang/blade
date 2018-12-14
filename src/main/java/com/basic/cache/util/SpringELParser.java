package com.basic.cache.util;

import com.user.vo.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpringELParser {

    private static ExpressionParser parser = new SpelExpressionParser();

    /**
     * 根据操作对象构造缓存查询的Key
     *
     * @param arg
     * @return
     */
    public static String constructKey(Object arg) {
        String key = "";
        try {
            Class clazz = arg.getClass();
            String name = clazz.getName();
            String clazzShortName = name.substring(name.lastIndexOf(".") + 1);

            String idAttributeName = constructIdAttributeName(clazzShortName);

            Object value = getValue(arg, idAttributeName);

            String pattern = constructKeyPattern(clazzShortName, name, idAttributeName);

            key = constructKey(pattern, "", new String[]{idAttributeName}, new Object[]{value});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 构建对象的主键属性名称
     *
     * @param shortName
     * @return
     */
    private static String constructIdAttributeName(String shortName) {
        return shortName + "Id";
    }

    /**
     * 获取对象的主键的值
     *
     * @param arg
     * @param id
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static Object getValue(Object arg, String id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String methodName = constructMethodName(id);
        Class clazz = arg.getClass();
        Method method = clazz.getMethod(methodName);
        return method.invoke(arg);
    }

    private static String constructMethodName(String attribute) {
        return "get" + attribute;
    }

    /**
     * 构建缓存的Key的模式
     * <p>
     * 此处的模式为：'User_'#UserId， 此处的#UseId在下一步将被替换为具体的值
     *
     * @param clazzShortName
     * @param idAttributeName
     * @return
     */
    private static String constructKeyPattern(String clazzShortName, String clazzName, String idAttributeName) {
        return String.format("'%s_'+#%s", clazzShortName, idAttributeName);
    }

    /**
     * 根据定义的pattern，构建缓存的key
     * <p>
     * pattern = "'User_'+#UserId";
     * userId = 100;
     * 生成的key = User_100
     *
     * @param pattern
     * @param condition
     * @param paramNames
     * @param arguments
     * @return
     */
    private static String constructKey(String pattern, String condition, String[] paramNames, Object[] arguments) {
        try {
            if (!checkCondition(condition, paramNames, arguments)) {
                return null;
            }
            Expression expression = parser.parseExpression(pattern);
            EvaluationContext context = new StandardEvaluationContext();
            int length = paramNames.length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    context.setVariable(paramNames[i], arguments[i]);
                }
            }
            return expression.getValue(context, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean checkCondition(String condition, String[] paramNames, Object[] arguments) {
        if (condition.length() < 1) {
            return true;
        }
        Expression expression = parser.parseExpression(condition);
        EvaluationContext context = new StandardEvaluationContext();
        int length = paramNames.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                context.setVariable(paramNames[i], arguments[i]);
            }
        }
        return expression.getValue(context, boolean.class);
    }

    public static void main(String[] args) {
        try {
            User user = new User();
            user.setUserId(123);

            String key = constructKey(user);

            System.out.println(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
