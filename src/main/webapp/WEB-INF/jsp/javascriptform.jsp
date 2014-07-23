<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Javascript - <spring:message code="appName"/></title>
		<style type="text/css">
			.dwRow {
				display: table-row;
			}
			.dwCell {
				display: table-cell;
				width: 300px;
				padding-left: 20px;
			}
			.dwCell img {
				width: 250px;
			}
			.dwExample {
				font-size: .85rem;
				word-wrap: break-word;
			}
			.dwCell h4 {
				font-size: 1rem;
				font-family: helvetica, arial;
				font-weight: bold;
				color: #000;
				margin-bottom: 7px;
			}
			.dwCell div {
				margin-top: 5px;
				white-space: pre-wrap;       /* css-3 */
				white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
				white-space: -pre-wrap;      /* Opera 4-6 */
				white-space: -o-pre-wrap;    /* Opera 7 */
				word-wrap: break-word;       /* Internet Explorer 5.5+ */
				font-family: courier;
				font-size: .7rem;
                height: 300px;
                overflow-y: auto;
			}
			.toggleExample {
				font-family: helvetica, arial;
                text-decoration: underline;
                color: blue;
                cursor: pointer;
                display: block;
                margin-bottom: 15px;
                font-size: .7rem;
			}
			.toggleExample:hover, .toggleExample:focus {
				color: navy;
            }
            #javascriptform-div {
                margin-top: 15px;
            }
            .dwCell strong {
                font-weight: bold;
                font-family: sans-serif;
            }
		</style>
		<pack:script enabled='true'>
			<src>/js/editJavascript.js</src>
		</pack:script>
    </head>
    <body>
		<h3 class="heading">Javascript Properties</h3>
		<a class="toggleExample" id="showExample">Show Example Javascript</a>
		<a class="toggleExample" id="hideExample" style="display:none">Hide Example Javascript</a>
		<div class='dwRow' style="display:none" id="examples">
			<div class="dwCell">
				<h4>Default Look</h4>
	            <img src="images/exampleDefaultScreen.png" title="This is the default look and feel"/>
			</div>
			<div id="javascripts-div" class="dwCell dwExample constrainText">
				<h4>Example Javascript</h4>
				<div>
// afterWidgetDataLoaded is a function that will be called by the
// widgets after the data has been loaded
var afterWidgetDataLoaded = function () {
  // Hide the first and last name fields and replace them
  // with read only text
  var firstNameField = jQuery('#constituent_firstName');
  firstNameField.parent().parent().children().hide();
  var span = jQuery('<span id="label_first_name"/>').html(
    'First Name : ' + firstNameField.val());
  firstNameField.parent().parent().append(span);

  var lastNameField = jQuery('#constituent_lastName');
  lastNameField.parent().parent().children().hide();
  span = jQuery('<span id="label_first_name"/>').html(
    'Last Name : ' + lastNameField.val());
  lastNameField.parent().parent().append(span);

  // Hide the billing phone section
  jQuery('#billing_phone_section').hide()

  // Default the amount to 20.00
  var amountField =
    jQuery('#transaction_firstDistributionLineAmount')
  amountField.val("20.00")
};
	            </div>
			</div>
			<div class="dwCell">
				<h4>Example Customized Look</h4>
	            <img src="images/exampleCustomJavascriptScreen.png" title="This is the customized look and feel based on the example javascript"/>
			</div>
		</div>
		<div id="javascriptform-div"></div>
    </body>
</page:applyDecorator>

