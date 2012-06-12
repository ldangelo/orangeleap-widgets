<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Style - <spring:message code="appName"/></title>
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
            #styleform-div {
                margin-top: 15px;
            }
            .dwCell strong {
                font-weight: bold;
                font-family: sans-serif;
            }
		</style>
		<pack:script enabled='true'>
			<src>/js/editStyle.js</src>
		</pack:script>
    </head>
    <body>
		<h3 class="heading">Style Properties</h3>
		<a class="toggleExample" id="showExample">Show Example Stylesheet</a>
		<a class="toggleExample" id="hideExample" style="display:none">Hide Example Stylesheet</a>
		<div class='dwRow' style="display:none" id="examples">
			<div class="dwCell">
				<h4>Default Look</h4>
	            <img src="images/exampleDefaultScreen.png" title="This is the default look and feel"/>
			</div>
			<div id="styles-div" class="dwCell dwExample constrainText">
				<h4>Example Stylesheet</h4>
				<div>
body {
    font-family: Arial,
        Helvetica,
        sans-serif;
    font-size: 1rem;
    background-color: yellow;
<strong>/* Change the font size, family and background color */</strong>
}
.x-panel {
    border-color: transparent;
<strong>/* Hide the gray border around the entire widget */</strong>
}
.x-panel-ml,
	.x-panel-mc,
	.x-panel-bl,
	.x-panel-br,
	.x-panel-bc,
	.x-panel-tl,
	.x-panel-tr {
        background-color: #fff;
<strong>/* Change the gray background to white */</strong>
}
.x-panel-tl,
	.x-panel-tr {
        background-image: none;
<strong>/* Hide the blue background in the title */</strong>
}
.x-panel-tl .x-panel-header {
    background-image: url("http://www.orangeleap.com/images/logo_orangeleap2.gif");
    background-position: left top;
    background-repeat: no-repeat;
    color: #333333;
    font-size: 18px;
    padding-left: 10px;
    padding-top: 100px;
    text-align: left;
<strong>/* Add the Orange Leap Icon at the top left and make "Online Donation" a large dark font */</strong>
}
.widgetForm {
    box-shadow: 0 0 0;
<strong>/* Remove the box shadow around the widget */</strong>
}
#widgetContainer {
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 7px;
    height: auto;
    margin-bottom: 7px;
    margin-top: 7px;
<strong>/* Change the body background, border, and height of the widget */</strong>
}
.x-form-item label {
    font-size: 14px;
<strong>/* Change the font size of the left field labels */</strong>
}
.x-fieldset legend {
    display: none;
<strong>/* Hide the white box around the grouped fields (the fieldset) */</strong>
}
.olLink {
    display: none;
<strong>/* Hide the "Powered By Orange Leap" bottom link */</strong>
}
.x-column-inner {
    display: table;
<strong>/* Force one group of fields to be displayed as a table */</strong>
}
.x-column {
    display: table-row;
<strong>/* Force a column of fields to be displayed as a table-row */</strong>
}
	            </div>
			</div>
			<div class="dwCell">
				<h4>Example Customized Look</h4>
	            <img src="images/exampleCustomScreen.png" title="This is the customized look and feel based on the example stylesheet"/>
			</div>
		</div>
		<div id="styleform-div"></div>
    </body>
</page:applyDecorator>

