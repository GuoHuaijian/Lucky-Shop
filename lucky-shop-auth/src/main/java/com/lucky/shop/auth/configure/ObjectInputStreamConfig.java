package com.lucky.shop.auth.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @Author Guo Huaijian
 * @Date 2020/7/3 13:45
 */
@Configuration
@Lazy
public class ObjectInputStreamConfig extends ObjectInputStream {
    public ObjectInputStreamConfig(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String name = desc.getName();
        try {
            if (name.startsWith("com.lucky.shop.auth.domain")) {
                // 监控项目包命替换
                name = name.replace("com.lucky.shop.auth.domain", "com.lucky.shop.admin.system.domain");
            }
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return super.resolveClass(desc);
    }
}
