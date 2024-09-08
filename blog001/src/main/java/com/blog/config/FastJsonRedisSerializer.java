package com.blog.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T>
{


    private static final Filter autoTypeFilter;

    static {
        autoTypeFilter = JSONReader.autoTypeFilter(
                // 按需加上需要支持自动类型的类名前缀，范围越小越安全
                "com.",
                "org.",
                "java."
        );
    }
    /**
     * cls.
     */
    private final Class<T> clazz;

    /**
     * 序列化.
     *
     * @param t object to serialize. Can be {@literal null}.
     * @return es
     * @throws SerializationException e
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
//        Map.Entry<String, T> entity = new SimpleEntry<>(t.getClass().getName(), t);
        return JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(Charset.defaultCharset());
    }

    /**
     * 反序列化.
     *
     * @param bytes object binary representation. Can be {@literal null}.
     * @return es
     * @throws SerializationException e
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String str = new String(bytes, Charset.defaultCharset());

        return JSON.parseObject(
                str,
                clazz
                ,JSONReader.Feature.SupportAutoType);
    }

    /**
     * 构造器.
     *
     * @param clazz cls
     */
    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }
}
