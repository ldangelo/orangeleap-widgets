var $j = jQuery.noConflict();

var customentitylist = {
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
    
    handleReturn: function(table,widgetid,constituentid,guid) {
	
	$j(widgetid).show();
	$j("<div id='globalErrors' class='globalFormErrors'><h5>Please correct the following errors.</h5><ul id='errors'></ul></div></div>").appendTo("ul#" + widgetid);
	
	$j("<li class='side'><input type='hidden' name='GUID' id='GUID' value='" + guid + "'/></li>").appendTo("ul#" + widgetid);
	var model = new Array();
	var cols = new Array();
	var search = new Array();
	var title = table.customTableDesc;

	for (x in table.fields) {

	    if (table.fields[x].customTableFieldName != null && 	table.fields[x].customTableFieldDatatype != "section") {
		var col = new Ext.grid.Column();

		col.id = table.fields[x].customTableFieldName;
		col.width=100;
		col.header = table.fields[x].customTableFieldDesc;
		col.dataIndex = table.fields[x].customTableFieldName;
		
		cols.push(col);
		
		obj = new Ext.data.Field();
		obj.name=table.fields[x].customTableFieldName;
		obj.mapping = table.fields[x].customTableFieldName;
		model.push(obj);
		
		if (table.fields[x].customTableFieldSearchable) {
		    var searchobj = new Object();
		    searchobj.display = table.fields[x].customTableFieldDesc;
		    searchobj.name = table.fields[x].customTableFieldName;
		    
		    search.push(searchobj);
		}
	    }
	}

	
	var proxy = new Ext.data.HttpProxy( {url:'/donorwidgets/customEntityList.json?guid=' + guid});

	var reader=new Ext.data.JsonReader();

	var mydatastore = new Ext.data.Store( {
	    metaData:true,
	    reader:reader,
	    proxy:proxy,
listeners: {
        'metachange': function (store, meta) {
               var fields = meta.fields;
                    // has the right definition

                    var columns = [];
for (var f=0;f < fields.length; f++) {
//                    col = new Ext.grid.Column({
//                        align: 'left',
//                        header: fields[f].header,
//                        width: 100,
//                        sortable: true,
//		 grid:grid,
//                        dataIndex: fields[f].name
//                    });

                    columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true});

}
		 
                    grid.reconfigure(store,new Ext.grid.ColumnModel(columns));
},
'load': function(store, records, options) {
	grid.render("customentitywidget");
}}});





var paging_toolbar = new Ext.PagingToolbar({
pageSize: 20,
displayInfo: true,
emptyMsg: 'No data found',
store: mydatastore
});

	var grid = new Ext.grid.GridPanel({
	    store: mydatastore,
	    columns: [],
	    viewConfig: {
		forceFit: false,
		emptyText: 'Enter Search Terms'
	    },
	    stripeRows: true,
	    frame:true,
	    title: title,
	    height: 350,
	    width:655,
	    bbar: paging_toolbar
	});
        mydatastore.load();
	var myinfo = {
	    layout: 'column',
	    xtype:'fieldset',
	    checkboxToggle:false,
	    title:'Fieldset Title',
	    autoHeight:true,
	    collapsed: false,
	    items: [{
		layout: 'form',
		width: 450,
		items: [grid]
	    }]
	};
	
	var mypanel = new Ext.form.FormPanel({
	    frame:true,
	    bodySyle: 'padding:5px 5px 0',
	    xtype: 'form',
	    id:'asset-form',
	    layout:'hbox',
	    frame:true,
	    collapsed:false,
	    autoHeight:true,
	    width:1200,
	    items: [ myinfo ], 
               tbar: [new Ext.Toolbar.TextItem ("Search For:"),
                  {xtype:'textfield',
                    fieldLabel:'Search',
                    name: 'pattern',
                    id:'pattern',
                    listeners: {  
                 //listen for the ENTER key, same as if the user clicks the search button
                   'render': function(c) {
                      c.getEl().on('keypress', function(e) {
                      if(e.getKey() == e.ENTER && this.getValue().length > 0) {
                      mydatastore.baseParams = {
			  pattern: document.getElementById('pattern').value
                      };
                          mydatastore.load({
                              params:{
                                  start: 0,
				  limit: 20
                              }
                          });
                      }
                      },
				   Ext.QuickTips.register({
				       target: c.getEl(),
				       text: 'If searching for date,<br/>\n\
\n\ format must be mm/dd/yy'
                                   }),
                                   c);
                   }
			
                    }
                  },
		      
                      {xtype:'tbseparator'},new Ext.Toolbar.Button({
                          text: 'Search',
                          iconCls:'search-icon',
                      	  handler: function search_submit() {
                              mydatastore.baseParams = {
                                  pattern: document.getElementById('pattern').value
                              };
                              mydatastore.load({
                                  params:  {
                                      start: 0,
                                      limit: 20
                                  }
                              });
                          }
                      }),
                      {xtype:'tbseparator'},
                      new Ext.Toolbar.Button({
                          text: 'Reset Search',
                          iconCls:'reload-icon',
                      	  handler: function() {
                              var s = Ext.getCmp('pattern');
                              s.setValue('');
                              mydatastore.baseParams = {
                                  pattern: document.getElementById('pattern').value
                              };
                              mydatastore.load({
                                  params:  {
                                      start: 0,
                                      limit: 20
                                  }
                              });
                          }
                      })
		      
                     ]
        });
	grid.render(widgetid);
//	mypanel.render(widgetid);	

	$j(".spinner").hide();

	customentitylist.hideError();
	
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
    
    generateWidget: function(widgetid, guid,authenticate,loginurl) {
	var constituentid = customentitylist.getCookie("constituentId");
	
	if (authenticate == true && constituentid == "") {
	    window.location = loginurl;
	    return;
	}
	var callbackproxy = function(dataFromServer) {
	    customentitylist.handleReturn(dataFromServer,widgetid,constituentid,guid);
	};
	var callMetaData = { callback:callbackproxy };
	
	OrangeLeapWidget.getCustomEntity(guid,callMetaData);
	
	OrangeLeapWidget.updateViewCount(guid,document.location.href);
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