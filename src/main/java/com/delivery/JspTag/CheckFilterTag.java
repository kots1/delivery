package com.delivery.JspTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Arrays;
import java.util.List;

public class CheckFilterTag extends TagSupport {
    private String var;
    private List<Object> items;

    public void setVar(String var) {
        this.var = var;
    }

    public void setItems(String[] items) {
            this.items = items ==null?null: Arrays.asList(items);

    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

    try {
            if (items == null){
                return SKIP_BODY;
            }
            if (items.contains(var)) {
                out.print("checked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
