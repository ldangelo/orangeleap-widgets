package com.orangeleap.donatenow.domain;

public class Placement {
    private Long id;
    private Long widgetid;
    private String placementURL;
    private Long   giftCount;
    private Long   errorCount;
    private Long viewCount;
    private String site;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWidgetid() {
		return widgetid;
	}

	public void setWidgetid(Long widgetid) {
		this.widgetid = widgetid;
	}

	public String getPlacementURL() {
		return placementURL;
	}

	public void setPlacementURL(String placementURL) {
		this.placementURL = placementURL;
	}

	public Long getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Long giftCount) {
		this.giftCount = giftCount;
	}

	public Long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}

	public Placement() {
		
	}

	public void setViewCount(Long l) {
		this.viewCount = l;
	}
	
	public Long getViewCount() {
		return this.viewCount;
	}
}
