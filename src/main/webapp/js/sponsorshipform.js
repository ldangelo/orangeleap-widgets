var $j = jQuery.noConflict();

Ext.ns("sponshorshipform");

var msgBox = null;
var fldidx = 0;
var mydatastore = null;
var form = null;
var searchform = null;
var wname = null;
var win = null;
var swin = null;
var    pageSize = 10;
var    pageStart = 0;
var pattern = "sponsorship_status=Available;";

var sponsorshipform =  {
    sponsorableurl:null,
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
	if (fldidx > 0) {
	    fldidx = fldidx - 1;
	    var metaData = mydatastore.reader.meta.fields;
	    var records = mydatastore.data.items;
	    for (var m=0; m < metaData.length; m++) {
		var value = records[fldidx].get(metaData[m].name);
		form.getForm().findField(metaData[m].name).setValue(value);
	    }
	} else {
	    //
	    // we need to backup the data
	    if (pageStart > 0) {
		msgBox = Ext.MessageBox.wait("Loading...","Loading....");
		sponsorshipform.clearForm();
		pageStart = pageStart - pageSize ;
		fldidx = pageSize -1;

		mydatastore.load({params:{start:pageStart,limit:pageSize,pattern:pattern}});	    		
	    } else {
		// we are back at the beginning of the data
	    }
	}

    },
    onSearch:function() {
	sponsorshipform.clearForm();
	pattern = "sponsorship_status=Available;";
	var field = searchform.getForm().findField('searchcountry');
	if (field.value != null)
	    pattern=pattern+ "country=" + field.value + ';';

	field = searchform.getForm().findField('searchage');
	if (field.value != null)
	    pattern=pattern+ "age=" + field.value + ';';
	
	field = searchform.getForm().findField('searchgender');
	if (field.value != null)
	    pattern=pattern+ "gender=" + field.value + ';';

	fldidx = 0;
	msgBox = Ext.MessageBox.wait("Searching...","Searching Sponships");

	mydatastore.load({params:{start:0,limit:pageSize,pattern:pattern}});
    },
    onRecurringGift: function() {
    },
    onSponsor: function() {
	var sponsorform = new Ext.FormPanel({
	    id: 'sponsor',
	    border:false,
	    frame:true,
	    labelWidth:150,
	    width:500,
	    autoHeight:true,
	    items:[
		{
		    xtype:'fieldset',
		    title:'Contact Information',
		    collapsible: true,
		    autoHeight: true,
		    defaultType:'textfield',
		    items: [
			{
			    xtype:'textfield',
			    fieldLabel:'First Name',
			    name:'firstName',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Last Name',
			    name:'lastName',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Email Address',
			    name:'emailAddress',
			    allowBlank:false
			}]},
		{
		    xtype:'fieldset',
		    title:'Registration Information',
		    collapsible:true,
		    autoHeight:true,
		    defaultType:'textfield',
		    items: [
			{
			    xtype:'textfield',
			    fieldLabel:'User Name',
			    name:'userName',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Password',
			    name:'password',
			    inputType:'password',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Verify Password',
			    name:'verifypassword',
			    inputType:'password',
			    allowBlank:false
			}
		    ]},
		{
		    xtype:'fieldset',
		    title:'Billing Address',
		    collapsible:true,
		    autoHeight:true,
		    defaultType:'textfield',
		    items: [
			{
			    xtype:'textfield',
			    fieldLabel:'Address Line 1',
			    name:'addressLine1',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Address Line 2',
			    name:'addressLine2'
			},
			{
			    xtype:'textfield',
			    fieldLabel:'City',
			    name:'city',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'State',
			    name:'state',
			    allowBlank:false
			},
			{
			    xtype:'textfield',
			    fieldLabel:'Postal Code',
			    name:'postalcode',
			    allowBlank:false
			}]},
		{xtype:'fieldset',
		 title:'Payment Information',
		 collapsible:true,
		 autoHeight:true,
		 defaultType:'textfield',
		 items: [
		     {
			 xtype:'textfield',
			 fieldLabel:'Credit Card Type',
			 name:'cctype',
			 allowBlank:false
		     },
		     {
			 xtype:'textfield',
			 fieldLabel:'Credit Card Number',
			 name:'ccnumber',
			 allowBlank:false
		     },
		     {
			 xtype:'textfield',
			 fieldLabel:'Expiration Month',
			 name:'expMonth',
			 allowBlank:false
		     },
		     {
			 xtype:'textfield',
			 fieldLabel:'Expiration Month',
			 name:'expYear',
			 allowBlank:false
		     }]}
],
	    buttons: [
		{text: 'Sponsor',
		 handler:sponsorshipform.onRecurringGift,
		 align:'center'
		}
	    ]
	});
	swin = new Ext.Window({
//	    el:'win-req-in',
	    id: 'sponsorshipform-win',
	    title:'Sponsor',
 	    modal:true,
    	    layout:'fit',
	    width:400,
	    autoHeight:true,
	    closable:true,
	    border:false,
	    plain:true,
	    items:[sponsorform]
	});
	swin.show();
    },
    clearForm:function() {
	var clearfield = function(f) {
	    if (f.isFormField) {
		f.setValue("");
	    }
	}

	form.getForm().items.each(clearfield);
    },
    onNext: function() {

	if (fldidx == mydatastore.totalLength-1) {
	    if (mydatastore.totalLength == pageSize) {
		msgBox = Ext.MessageBox.wait("Loading...","Loading Sponships");
		//
		// we need to load the next page
		sponsorshipform.clearForm();
		pageStart = pageStart + pageSize ;
		fldidx = 0;
		mydatastore.load({params:{start:pageStart,limit:pageSize,pattern:pattern}});	    
		} else {
		    //
		    // there is no more data...
		}
	} else {
	    fldidx = fldidx + 1;
	    var metaData = mydatastore.reader.meta.fields;
	    var records = mydatastore.data.items;
	    for (var m=0; m < metaData.length; m++) {
		var value = records[fldidx].get(metaData[m].name);
		form.getForm().findField(metaData[m].name).setValue(value);
	    }
	}
    },
    generateWidget: function(widgetname,guid,authenticate, redirecturl,sponsorshipformurl) {
	sponsorshipform = sponsorshipformurl;
	wname = widgetname;
	var constituentId = this.getCookie("constituentId");

	if (authenticate == true &&  constituentId == "") {
	    window.location=redirecturl;
	    return;
	}
	OrangeLeapWidget.updateViewCount(guid,document.location.href);

	var proxy = new Ext.data.HttpProxy( {url:'/donatenow/customEntityList.json?guid=' + guid});

	var reader=new Ext.data.JsonReader();

	mydatastore = new Ext.data.Store( {
	    metaData:true,
	    reader:reader,
	    proxy:proxy,
	    listeners: {
		'metachange': function (store, meta) {

		    if (form.items.length > 0) return;  // we are paging through data and the fields already exist
		    var fields = meta.fields;
		    var col1 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]});
		    var col2 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]})
		    var panel = new Ext.Panel({ columnWidth:0.5,layout:'column', bodyStyle:'padding:0 18px 0 0',items:[col1,col2]});
		    
		    for (var f=0;f < fields.length; f++) {
			if (fields[f].hidden == true) {
			    var field = new Ext.form.Hidden();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = mydatastore;
			    field.mode = 'local';
			    field.readOnly = true;
			    field.border = false;

			    if (f > fields.length/2)
				col2.add(field);
			    else
				col1.add(field);

            } else if (fields[f].type == 'text' || fields[f].type == 'date' || fields[f].type == 'integer') {
		var field = new Ext.form.TextField();
		field.id = fields[f].name;
		field.name = fields[f].name;
		field.fieldLabel = fields[f].header;
		field.store = mydatastore;
		field.mode = 'local';
		field.readOnly = true;
		field.border = false;
			    if (f > fields.length/2)
				col2.add(field);
			    else
				col1.add(field);

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
			    if (f > fields.length/2)
				col2.add(field);
			    else
				col1.add(field);

	    } else if (fields[f].type == 'picklist') {
		var field = new Ext.form.TextField();
		field.id = fields[f].name;
		field.name = fields[f].name;
		field.fieldLabel = fields[f].header;
		field.store = mydatastore;
		field.mode = 'local';
		field.readOnly = true;
		field.border = false;
			    if (f > fields.length/2)
				col2.add(field);
			    else
				col1.add(field);

	    } else if (fields[f].type == 'multi-picklist') {
		var field = new Ext.form.TextField();
		field.id = fields[f].name;
		field.name = fields[f].name;
		field.fieldLabel = fields[f].header;
		field.store = mydatastore;
		field.mode = 'local';
		field.readOnly = true;
		field.border = false;
			    if (f > fields.length/2)
				col2.add(field);
			    else
				col1.add(field);

	    }
		    }
			form.add(panel);
		},
		'load': function(store,records,options) {
		    var metaData = store.reader.meta.fields;
		    var value = null;
		    if (records.length > 0) 
		    for (var m=0; m < metaData.length; m++) {

			    value = records[fldidx].get(metaData[m].name);

			form.getForm().findField(metaData[m].name).setValue(value);
		    }

		    
		    //
		    // only do this for the first page of data...
		    if (pageStart == 0 && fldidx == 0) {
			win.render(widgetname);		    
			var loading = Ext.get('loading');
			if (loading != null) {
			    loading.remove();
			    Ext.get('loading-mask').fadeOut({remove:true});
			}
		    }

		    if (msgBox != null) {
			msgBox.hide();
			msgBox = null;
		    }
		}}});

	var countryComboConfig = {
	    xtype:'combo',
	    id:'searchcountry',
	    valueField:'Name',
	    triggerAction:'all',
	    hiddenName:'Name',
	    displayField:'Description',
	    forceSelection:true,
	    allowBlank:false,
	    emptyText: 'Select location...',
	    store:new Ext.data.JsonStore({
		id:'Name',
		root:'rows',
		totalProperty:'totalRows',
		fields: [
		    {name:'Name',type:'string'},
		    {name:'Description',type:'string'}
		],
		url:'picklistItems.json',
		baseParams: {
		    guid: guid,
		    picklistname: 'country'
		}
	    }),
	    fieldLabel:'Country'
	};

	var genderStore = new Ext.data.ArrayStore({
	    fields: ['gender'],
	    data :[['Male'],['Female'],['Unspecified']]
	    
	});
	var genderCombo = new Ext.form.ComboBox({
	    id: 'searchgender',
	    store: genderStore,
	    displayField: 'gender',
	    typeAhead:true,
	    mode:'local',
	    forceSelection:true,
	    triggerAction:'all',
	    emptyText: 'Select Gender...',
	    selectOnFocus:true
	});

	var ageStore = new Ext.data.ArrayStore({
	    fields: ['age'],
	    data :[['<1'],['1'],['2'],['3'],['4'],['5'],['6'],['7'],['8'],['9'],['10'],['11'],['12'],['13'],['14']]
	    
	});
	var ageCombo = new Ext.form.ComboBox({
	    id:'searchage',
	    store: ageStore,
	    displayField: 'age',
	    typeAhead:true,
	    mode:'local',
	    forceSelection:true,
	    triggerAction:'all',
	    emptyText: 'Select age...',
	    selectOnFocus:true
	});

	searchform = new Ext.FormPanel({
	    id: 'searchform',
//	    region: 'botton',
	    border:false,
	    layout: 'column',
	    frame:true,
	    labelWidth:100,
	    monitorValid:true,
	    width:600,
	    items:[
		countryComboConfig,genderCombo,ageCombo
	    ],
	    buttons: [{
		text: 'Search',
		handler: sponsorshipform.onSearch,
		align:'center'
	    }]
	});
	form = new Ext.FormPanel({
	    id: 'form',
//	    region: 'top',
	    border: false,
	    frame: true,
	    labelWidth: 100,
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

	win = new Ext.Panel({
	    id: 'sponsorshipform-panel',
//	    title:'Sponsor',
//	    layout:'border',
//	    width:650,
//	    height:500,
	    closable:false,
	    border:false,
	    items:[form,searchform]
	});

	msgBox = Ext.MessageBox.wait("Loading...","Loading Sponships");
	mydatastore.load({params:{start:pageStart,limit:pageSize,pattern:pattern}});
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