var $j = jQuery.noConflict();
var fldidx = 0;
var mydatastore = null;
var form = null;
var wname = null;

var sponsorshipform =  {

    include: function(filename)
    {
	var head = document.getElementsByTagName('head')[0];

	script = document.createElement('script');
	script.src = filename;
	script.type = 'text/javascript';

	head.appendChild(script)
    },


    setCookie: function(c_name,value,expiredays)
    {
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	document.cookie=c_name+ "=" +escape(value)+
	    ((expiredays==null) ? "" : ";expires="+exdate.toUTCString());
    },

    populateWidget: function(constituent,table) {
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
    },

    populatePicklist: function(picklistitems,field) {
	for (x in picklistitems) {
	    $j("<option value=" + picklistitems[x].itemName + ">" + picklistitems[x].defaultDisplayValue + "</option>").appendTo("select[name=" + field + "]");
	}
    },

    handleReturn: function(constituentid,widetid,redirecturl) {
	if (constituentid == -1) {
	    this.handlerError("Authentication Failed!");
	} else {
	    this.setCookie("constituentId",constituentid);
	    document.location = redirecturl;
	}
    },


    getCookie: function(c_name)
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
    },
    onPrevious: function() {
	fldidx = fldidx - 1;
	var metaData = mydatastore.reader.meta.fields;
	var records = mydatastore.data.items;
	for (var m=0; m < metaData.length; m++) {
	    var value = records[fldidx].get(metaData[m].name);
	    form.get(metaData[m].name).setValue(value);
	}

    },
    onSponsor: function() {
    },
    onNext: function() {
	fldidx = fldidx + 1;
	var metaData = mydatastore.reader.meta.fields;
	var records = mydatastore.data.items;
	for (var m=0; m < metaData.length; m++) {
	    var value = records[fldidx].get(metaData[m].name);
	    form.get(metaData[m].name).setValue(value);
	}

//	form.load();
    },
    generateWidget: function(widgetname,guid,authenticate, redirecturl) {
	wname = widgetname;
	var constituentId = this.getCookie("constituentId");

	if (authenticate == true) 
	    if (constituentId == "") window.location=redirecturl;
	var proxy = new Ext.data.HttpProxy( {url:'/donatenow/customEntityList.json?guid=' + guid});

	var reader=new Ext.data.JsonReader();

	mydatastore = new Ext.data.Store( {
	    metaData:true,
	    reader:reader,
	    proxy:proxy,
	    listeners: {
		'metachange': function (store, meta) {
		    var fields = meta.fields;
		    
		    var searchable = [];

		    for (var f=0;f < fields.length; f++) {
			if (fields[f].type == 'text' || fields[f].type == 'date' || fields[f].type == 'picklist') {
			var field = new Ext.form.TextField();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = mydatastore;
			    field.mode = 'local';
			    field.readOnly = true;
			    field.border = false;
			} else if (fields[f].type == 'comments') {
			    var field = new Ext.form.TextArea();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = mydatastore;
			    field.mode = 'local';
			    field.readOnly = true;			    
			    field.border = false;
			    field.width = 350;
			    field.height = 150;
			}
			form.items.add(field);
		    }
		},
		'load': function(store,records,options) {
		    var metaData = store.reader.meta.fields;
		    for (var m=0; m < metaData.length; m++) {
			var value = records[fldidx].get(metaData[m].name);
			form.get(metaData[m].name).setValue(value);
		    }

		    form.render(widgetname);		    
		}}});
	
	form = new Ext.FormPanel({
	    border: false,
	    frame: true,
	    labelWidth: 200,
	    monitorValid:true,
	    width: 600,
	    items: [],
	    buttons: [{
		text: 'Previous',
		handler: sponsorshipform.onPrevious,
		align: 'center'
	    },{
		text: 'Sponsor',
		handler: sponsorshipform.onSponsor,
		align: 'center'
	    },{
		text: 'Next',
		handler: sponsorshipform.onNext,
		align: 'center'
	    }
	    ]
	});


	mydatastore.load();
    },    

    showError: function() {
	$j("div#globalErrors").show();
    },

    hideError: function() {
	$j("div#globalErrors").hide();
    },

    clearError: function() {
	$j("ul#errors").empty();
    },

    handleError: function(str) {
	$j("<li>" + str + "</li>").appendTo('ul#errors');
    },

    validateForm: function() {
	var isValid=1;

	return isValid;
    }

}