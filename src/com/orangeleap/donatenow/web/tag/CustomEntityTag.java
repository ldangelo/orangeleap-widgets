package com.orangeleap.donatenow.web.tag;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.ListCustomTablesRequest;
import com.orangeleap.client.ListCustomTablesResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.Picklist;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.WSClient;
import java.io.IOException;
import java.lang.String;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomEntityTag extends TagSupport
{
  private static final Log logger = LogFactory.getLog(CustomEntityTag.class);
  private String guid;

    public CustomEntityTag() {
    super();
  }

  private void startForm() throws java.io.IOException {
    pageContext.getOut().write("<div class='column singleColumn'>");

    pageContext.getOut().write("<ul class='formFields width385'>");
  }

  private void endForm() throws java.io.IOException {
    pageContext.getOut().write("</ul>");
    pageContext.getOut().write("</div>");
  }

  private void renderFormField(CustomTableField field) throws java.io.IOException {
    String name = field.getCustomTableFieldName();

    pageContext.getOut().write("<input class='text' type='text' name='" + name + "' id='" + name + "' value=''/>");
  }

  private void renderLabel(CustomTableField field) throws java.io.IOException {
    String name = field.getCustomTableFieldName();
    String desc = field.getCustomTableFieldDesc();

    pageContext.getOut().write("<label class='desc' for='" + name + "' id='" + name + "label'>"+ desc +"</label>");
  }

  private void renderPicklistField(CustomTableField field) throws java.io.IOException {
    String userName = "nolan@company1";
    String password = "ryan";
    	WSClient wsClient = new WSClient();
		OrangeLeap oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),userName, password);
		GetPickListByNameRequest request = new GetPickListByNameRequest();
		GetPickListByNameResponse response = null;
		
		request.setName(field.getCustomTableFieldPicklistNameId());

		try {
		    response = oleap.getPickListByName(request);		    
		} catch ( Exception ex) {
		    logger.error(ex.getMessage());
		}
    
        Picklist picklist = response.getPicklist();

        String name = picklist.getPicklistNameId();
        String desc = picklist.getPicklistDesc();
        List<PicklistItem> lpicklistItem = picklist.getPicklistItems();
        Iterator<PicklistItem> it = lpicklistItem.iterator();

        pageContext.getOut().write("<li class='side'>");
        renderLabel(field);
        pageContext.getOut().write("<select id='" + name + "' name='" + name + "'>");
        while (it.hasNext()) {
          PicklistItem item = it.next();
          String itemName = item.getItemName();
          String itemDesc = item.getDefaultDisplayValue();
          pageContext.getOut().write("<option value='" + itemName + "'>" + itemDesc + "</option>" );
        }
        pageContext.getOut().write("</select>");
        pageContext.getOut().write("</li>");
  }

  private void renderSectionHeading(CustomTableField field) throws java.io.IOException {
    String name = field.getCustomTableFieldName();
    String desc = field.getCustomTableFieldDesc();

    pageContext.getOut().write("<h4 class='formSectionHeader'>"+ desc +"</h4>");
  }

  private void renderField(CustomTableField field) throws java.io.IOException {
    pageContext.getOut().write("<li class='side'>");
    renderLabel(field);
    renderFormField(field);
    pageContext.getOut().write("</li>");
  }

  private void renderCustomTable(CustomTable table) throws java.io.IOException
  {

    List<CustomTableField> fieldlist =  table.getFields();

    Iterator<CustomTableField> it = fieldlist.iterator();
    startForm();
    while (it.hasNext()) {
      CustomTableField field = it.next();

      if (field.isCustomTableFieldActive()) {
        if (field.getCustomTableFieldDatatype().equals("text") || field.getCustomTableFieldDatatype().equals("number")) {
          renderField(field);
        } else if (field.getCustomTableFieldDatatype().equals("section")) {
          renderSectionHeading(field);
        } else if (field.getCustomTableFieldDatatype().equals("picklist")) {
          renderPicklistField(field);
        }
      }
    }
    endForm();
  }




  public int doEndTag() throws javax.servlet.jsp.JspTagException
  {
    try {
      //
      // connect to orangeleap and get the entity
      WSClient client = new WSClient();
      OrangeLeap oleap = client.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),"nolan@company1","ryan");

      ListCustomTablesRequest request = new ListCustomTablesRequest();
      ListCustomTablesResponse response = null;


      response = oleap.listCustomTables(request);

      renderCustomTable(response.getCustomTable().get(0));


    } catch (java.io.IOException e) {
      throw new JspTagException("IO Error: " + e.getMessage());
    }
    return EVAL_PAGE;
}

  /**
   * Get the <code>Guid</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getGuid() {
    return guid;
  }

  /**
   * Set the <code>Guid</code> value.
   *
   * @param newGuid The new Guid value.
   */
  public final void setGuid(final String newGuid) {
    this.guid = newGuid;
  }
}