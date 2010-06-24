function include(filename)
{
	var head = document.getElementsByTagName('head')[0];
	
	script = document.createElement('script');
	script.src = filename;
	script.type = 'text/javascript';
	
	head.appendChild(script)
}

var $j = jQuery.noConflict();
function setCookie(c_name,value,expiredays)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate()+expiredays);
    document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toUTCString());
}
function populateWidget(constituent,table) {
    for (x in table.fields) {
	var fieldName = table.fields[x].customTableFieldName;
	if (fieldName != null) {
	    if (fieldName.indexOf(".") != -1) {
		//
		// this is a sub object like primaryEmail, etc...
		var obj = constituent[fieldName.substring(0,fieldName.indexOf("."))];
		$j("[name=" + table.fields[x].customTableFieldName + "]").val(obj[fieldName.substring(fieldName.indexOf(".") +1, fieldName.length)]);	    
	    } else { 
		$j("[name=" + table.fields[x].customTableFieldName + "]").val(constituent[table.fields[x].customTableFieldName]);
	    }
	}
    }
}

function populatePicklist(picklistitems,field) {
    for (x in picklistitems) {
    $j("<option value=" + picklistitems[x].itemName + ">" + picklistitems[x].defaultDisplayValue + "</option>").appendTo("select[name=" + field + "]");
    }
}

function handleReturn(table,widgetid,constituentid,guid) {

    $j("div#" + widgetid).show();
    $j("<div id='globalErrors' class='globalFormErrors'><h5>Please correct the following errors.</h5><ul id='errors'></ul></div></div>").appendTo("ul#" + widgetid);

    $j("<li class='side'><input type='hidden' name='GUID' id='GUID' value='" + guid + "'/></li>").appendTo("ul#" + widgetid);
     for (x in table.fields) {
	 if (table.fields[x].customTableFieldDatatype == "text" )
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + " </label><input type='text' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "section")
	     $j("<li class='side'><h4 class='formSectionHeader'>" + table.fields[x].customTableFieldDesc +" </li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "checkbox")
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><input type='checkbox' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "hidden")
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><input type='hidden' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "date")
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><input type='text' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "number")
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><input type='text' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	 if (table.fields[x].customTableFieldDatatype == "picklist") {
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><select name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
	     
	     var fieldname = table.fields[x].customTableFieldName;
	         var populatepicklistcallbackproxy = function(dataFromServer) {
		     populatePicklist(dataFromServer,fieldname);
		 };

	     var populatepicklistcallMetaData = { callback:populatepicklistcallbackproxy };

	     OrangeLeapWidget.getPickListItems(guid,table.fields[x].customTableFieldPicklistNameId,populatepicklistcallMetaData);
	 }
	 if (table.fields[x].customTableFieldDatatype == "comments")
	     $j("<li class='side'><label class='desc' for='" + table.fields[x].customTableFieldName + "' id='"+table.fields[x].customTableFieldName +"_label'>" + table.fields[x].customTableFieldDesc + "</label><input type='text' name='" + table.fields[x].customTableFieldName + "' id='" + table.fields[x].customTableFieldName + "' value='' class='text'/></li>").appendTo("ul#" + widgetid);
     }
     $j("<li class='side'><input type='submit' name='loginbtn' id='loginbtn' class='saveButton' value='Submit'/></li>").appendTo("ul#" + widgetid);
     $j("<li class='side'><img class='spinner' src='images/spinnger.gif'/></li>").appendTo("ul#" + widgetid);

     $j(".spinner").hide();
//     $j("ul#" + widgetid).wrapInner("<div class='column singleColumn'></div>");
     hideError();

    var getconstituentcallbackproxy = function(dataFromServer) {
	populateWidget(dataFromServer,table);
    };

    var getconstituentcallMetaData = { callback:getconstituentcallbackproxy };

    OrangeLeapWidget.getConstituent(guid,constituentid,getconstituentcallMetaData);
 }


function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=");
  if (c_start!=-1)
    {
    c_start=c_start + c_name.length+1;
    c_end=document.cookie.indexOf(";",c_start);
    if (c_end==-1) c_end=document.cookie.length;
    return unescape(document.cookie.substring(c_start,c_end));
    }
  }
return "";
}

function generateCustomEntityWidget(widgetid, guid,authenticate,loginurl) {
    var constituentid = getCookie("constituentId");
    
    if (authenticate == true && constituentid == "") {
	window.location = loginurl;
    }
    var callbackproxy = function(dataFromServer) {
	handleReturn(dataFromServer,widgetid,constituentid,guid);
    };
    var callMetaData = { callback:callbackproxy };

    OrangeLeapWidget.getCustomEntity(guid,callMetaData);

    OrangeLeapWidget.updateViewCount(guid,document.location.href);
}

function showError() {
	$j("div#globalErrors").show();
}

function hideError() {
	$j("div#globalErrors").hide();
}

function clearError() {
	$j("ul#errors").empty();
}

function handleError(str) {
	$j("<li>" + str + "</li>").appendTo('ul#errors');
}

function validateForm() {
    var isValid=1;

    if($j("#username").attr("value").length <= 0) {
	handleError("User Name is required!");
	isValid=0;
    }

    if($j("#password").attr("value").length <= 0) {
	handleError("Pass Word is required!");
	isValid=0;
    }

    if (isValid != 1) showError();

    return isValid;
}

