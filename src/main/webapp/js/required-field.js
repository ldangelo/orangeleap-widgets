
Ext.ns('Extensive.components');

Ext.apply(Ext.layout.FormLayout.prototype, {
    originalRenderItem: Ext.layout.FormLayout.prototype.renderItem,
    renderItem: function(c, position, target){
        if (c && !c.rendered && c.isFormField && c.fieldLabel && !c.allowBlank) {
            c.fieldLabel = c.fieldLabel + " <span " +
            ((c.requiredFieldCls !== undefined) ? 'class="' + c.requiredFieldCls + '"' : 'style="color:red;"') +
            " ext:qtip=\"" +
            ((c.blankText !== undefined) ? c.blankText : "This field is required") +
            "\">*</span>";
        }
        this.originalRenderItem.apply(this, arguments);
    }
});
 
Extensive.components.RequiredFieldInfo = Ext.extend(Ext.form.Label, {
    constructor: function(config){
        Extensive.components.RequiredFieldInfo.superclass.constructor.call(this, Ext.apply({
            html: "<span " +
            ((this.requiredFieldCls !== undefined) ? 'class="' + this.requiredFieldCls + '"' : 'style="color:red;"') +
            '>*</span> ' +
            ((this.requiredFieldText !== undefined) ? this.requiredFieldText : 'Required field')
        }, config));
    }
});
Ext.reg('reqFieldInfo', Extensive.components.RequiredFieldInfo);
