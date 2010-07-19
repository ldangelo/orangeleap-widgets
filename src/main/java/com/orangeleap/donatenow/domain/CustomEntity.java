package com.orangeleap.donatenow.domain;

public class CustomEntity {
  /**
   * Describe name here.
   */
  private String name;

  /**
   * Describe guid here.
   */
  private String guid;
  /**
   * Describe type here.
   */
  private String type;
  /**
   * Describe header here.
   */

  private String header;
  /**
   * Describe searchable here.
   */

  private boolean searchable;
  /**
   * Describe hidden here.
   */
  private boolean hidden;

  /**
   * Describe value here.
   */
  private String value;
  /**
   * Describe picklistId here.
   */
  private String picklistId;
  /**
   * Describe required here.
   */
  private boolean required;
  /**
   * Describe expression here.
   */
  private String expression;

  /**
   * Get the <code>Expression</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getExpression() {
    return expression;
  }

  /**
   * Set the <code>Expression</code> value.
   *
   * @param expression The new Expression value.
   */
  public final void setExpression(final String expression) {
    this.expression = expression;
  }

  /**
   * Get the <code>Required</code> value.
   *
   * @return a <code>boolean</code> value
   */
  public final boolean isRequired() {
    return required;
  }

  /**
   * Set the <code>Required</code> value.
   *
   * @param required The new Required value.
   */
  public final void setRequired(final boolean required) {
    this.required = required;
  }

  /**
   * Get the <code>PicklistId</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getPicklistId() {
    return picklistId;
  }

  /**
   * Set the <code>PicklistId</code> value.
   *
   * @param picklistId The new PicklistId value.
   */
  public final void setPicklistId(final String picklistId) {
    this.picklistId = picklistId;
  }

  /**
   * Get the <code>Value</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getValue() {
    return value;
  }

  /**
   * Set the <code>Value</code> value.
   *
   * @param value The new Value value.
   */
  public final void setValue(final String value) {
    this.value = value;
  }

  /**
   * Get the <code>Hidden</code> value.
   *
   * @return a <code>boolean</code> value
   */
  public final boolean isHidden() {
    return hidden;
  }

  /**
   * Set the <code>Hidden</code> value.
   *
   * @param hidden The new Hidden value.
   */
  public final void setHidden(final boolean hidden) {
    this.hidden = hidden;
  }

  /**
   * Get the <code>Searchable</code> value.
   *
   * @return a <code>Boolean</code> value
   */
  public final boolean getSearchable() {
    return searchable;
  }

  /**
   * Set the <code>Searchable</code> value.
   *
   * @param searchable The new Searchable value.
   */
  public final void setSearchable(final boolean searchable) {
    this.searchable = searchable;
  }

  /**
   * Get the <code>Header</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getHeader() {
    return header;
  }

  /**
   * Set the <code>Header</code> value.
   *
   * @param header The new Header value.
   */
  public final void setHeader(final String header) {
    this.header = header;
  }

  /**
   * Get the <code>Type</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getType() {
    return type;
  }

  /**
   * Set the <code>Type</code> value.
   *
   * @param type The new Type value.
   */
  public final void setType(final String type) {
    this.type = type;
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
   * @param guid The new Guid value.
   */
  public final void setGuid(final String guid) {
    this.guid = guid;
  }

  /**
   * Get the <code>Name</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getName() {
    return name;
  }

  /**
   * Set the <code>Name</code> value.
   *
   * @param id The new Name value.
   */
  public final void setName(final String id) {
    this.name = id;
  }

  public CustomEntity() {
    name="";
    guid="";
    type="";
    header="";
    searchable=false;
    hidden=true;
    value="";
  }
}