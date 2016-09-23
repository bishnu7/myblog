package com.sd.dao;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoMapifier
{

    private Map<String, Object> params = new HashMap<String, Object>();

    public void addParam(String key, Object value)
    {
        this.params.put(key, value);
    }

    public String getQueryString()
    {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate("vm/sql.vm");
        VelocityContext context = new VelocityContext();
        for(Map.Entry<String, Object> entry : params.entrySet())
        {
            context.put(entry.getKey(), entry.getValue());
        }
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
