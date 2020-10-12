package com.hg.knowledgebase.module.act.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 表单bean
 * @Author: 况克冬
 * @CreateDate: 2020年5月12日下午2:37:04
 * @Version: 1.0.0
 */
public class FormDataBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -7931387125563365256L;

    /**
     * 字段集合
     */
    private List<FieldBean> fields;

    /**
     * 表单是否是外部表单
     */
    private boolean formKey;

    public List<FieldBean> getFields()
    {
        return fields;
    }

    public void setFields(List<FieldBean> fields)
    {
        this.fields = fields;
    }

    public boolean isFormKey()
    {
        return formKey;
    }

    public void setFormKey(boolean formKey)
    {
        this.formKey = formKey;
    }

    @Override
    public String toString()
    {
        return "FormDataBean [fields=" + fields + ", formKey=" + formKey + "]";
    }
}
