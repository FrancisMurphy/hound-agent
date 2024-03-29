package com.frank.hound.agent.transformer;

import com.alibaba.ttl.internal.javassist.CannotCompileException;
import com.alibaba.ttl.internal.javassist.CtClass;
import com.alibaba.ttl.internal.javassist.CtMethod;
import com.alibaba.ttl.internal.javassist.NotFoundException;
import com.alibaba.ttl.threadpool.agent.internal.transformlet.ClassInfo;
import com.frank.hound.agent.transformlet.HoundJavassistTransformlet;

import java.io.IOException;

/**
 * @author frank
 */
public class HoundHttpClientTransformer implements HoundJavassistTransformlet
{

    private String HTTP_CLIENT_BUILDER_CLASS_NAME = "org.apache.http.impl.client.HttpClientBuilder";

    @Override
    public void doTransform(ClassInfo classInfo)
            throws IOException, CannotCompileException
    {
        final CtClass clazz = classInfo.getCtClass();

        try
        {
            CtMethod buildMethod = clazz
                    .getDeclaredMethod(HTTP_CLIENT_BUILDER_CLASS_NAME);

            //com.frank.hound.consumer.httpclient.interceptor.HoundHttpClientInterceptor
            String code = "this.addInterceptorFirst(new com.frank.hound.consumer.httpclient.interceptor.HoundHttpClientInterceptor());";
            buildMethod.insertBefore(code);

        }
        catch (NotFoundException e)
        {
            // clazz does not override build method, do nothing.
        }
    }
}
