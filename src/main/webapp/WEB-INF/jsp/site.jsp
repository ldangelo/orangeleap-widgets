<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Settings - <spring:message code="appName"/></title>
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
            #siteform-div {
                margin-top: 15px;
            }
            .dwCell strong {
                font-weight: bold;
                font-family: sans-serif;
            }
		</style>
		<pack:script enabled='true'>
			<src>/js/editSite.js</src>
		</pack:script>
    </head>
    <body>
		<h3 class="heading">Settings</h3>
		<div id="siteform-div"></div>
    </body>
</page:applyDecorator>